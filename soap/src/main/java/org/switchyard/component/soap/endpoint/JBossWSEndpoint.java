/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
 
package org.switchyard.component.soap.endpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.jboss.wsf.spi.classloading.ClassLoaderProvider;
import org.jboss.wsf.spi.deployment.Endpoint;
import org.jboss.wsf.spi.metadata.webservices.PortComponentMetaData;
import org.jboss.wsf.spi.metadata.webservices.WebserviceDescriptionMetaData;
import org.jboss.wsf.spi.metadata.webservices.WebservicesMetaData;
import org.jboss.wsf.spi.publish.Context;
import org.jboss.wsf.spi.publish.EndpointPublisher;
import org.jboss.wsf.spi.publish.EndpointPublisherFactory;
import org.switchyard.component.soap.InboundHandler;
import org.switchyard.component.soap.WebServicePublishException;

/**
 * Wrapper for JBossWS endpoints.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2012 Red Hat Inc.
 */
public class JBossWSEndpoint implements WSEndpoint {

    private static final Logger LOG = Logger.getLogger(JBossWSEndpoint.class);
    private static final String HOST = "default-host";
    private static final EndpointPublisherFactory FACTORY;
    private static Map<String, JBossWSEndpoint> PUBLISHEDCONTEXTS = new ConcurrentHashMap<String, JBossWSEndpoint>();

    private EndpointPublisher _publisher;
    private Context _context;
    private WebservicesMetaData _metadata;
    private Map<String, String> _urlPatternToClassNameMap;

    /**
     * Construct a JBossWS endpoint on default host.
     * @throws Exception If a publisher could not be created
     */
    public JBossWSEndpoint() throws Exception {
        _publisher = FACTORY.newEndpointPublisher(HOST);
    }

    /**
     * Construct a JBossWS endpoint on specified host.
     * @param host The host on which the pubhlisher should created
     * @throws Exception if a publisher could not be created
     */
    public JBossWSEndpoint(String host) throws Exception {
        _publisher = FACTORY.newEndpointPublisher(host);
    }

    /**
     * Reset the endpoint publisher to a new one.
     * @return the endpoint publisher
     */
    public EndpointPublisher getPublisher() {
        return _publisher;
    }

    /**
     * Reset the endpoint publisher to a new one.
     * @param publisher the endpoint publisher
     */
    public void setPublisher(EndpointPublisher publisher) {
        _publisher = publisher;
    }

    /**
     * Get the endpoint context.
     * @return the endpoint context
     */
    public Context getContext() {
        return _context;
    }

    /**
     * Reset the endpoint context to a new one.
     * @param context the endpoint context
     */
    public void setContext(Context context) {
        _context = context;
    }

    /**
     * Get the endpoint metadata.
     * @return the endpoint metadata
     */
    public WebservicesMetaData getMetaData() {
        return _metadata;
    }

    /**
     * Get the url pattern map.
     * @return the url pattern map
     */
    public Map<String, String> getUrlPatternToClassNameMap() {
        return _urlPatternToClassNameMap;
    }

    /**
     * {@inheritDoc}
     */
    public void publish(String contextRoot, Map<String, String> urlPatternToClassNameMap, WebservicesMetaData metadata, InboundHandler handler) throws Exception {
        _metadata = metadata;
        _urlPatternToClassNameMap = urlPatternToClassNameMap;
        _context = _publisher.publish(contextRoot, Thread.currentThread().getContextClassLoader(), urlPatternToClassNameMap, metadata);
        PUBLISHEDCONTEXTS.put(contextRoot, this);
        for (Endpoint ep : _context.getEndpoints()) {
            BaseWebService wsProvider = (BaseWebService) ep.getInstanceProvider().getInstance(BaseWebService.class.getName()).getValue();
            wsProvider.setInvocationClassLoader(Thread.currentThread().getContextClassLoader());
            // Hook the handler
            wsProvider.setConsumer(handler);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void republish(String contextRoot, Map<String, String> urlPatternToClassNameMap, WebservicesMetaData metadata, InboundHandler handler) throws Exception {
        JBossWSEndpoint oldEndpoint = PUBLISHEDCONTEXTS.remove(contextRoot);
        Map<String, EndpointInfo> oldEndpointInfos = getEndpointInfos(oldEndpoint.getContext().getEndpoints());
        for (WebserviceDescriptionMetaData descriptionMetData : oldEndpoint.getMetaData().getWebserviceDescriptions()) {
            WebserviceDescriptionMetaData webserviceDescription = new WebserviceDescriptionMetaData(metadata);
            webserviceDescription.setWsdlFile(descriptionMetData.getWsdlFile());
            for (PortComponentMetaData oldPortComponent : descriptionMetData.getPortComponents()) {
                PortComponentMetaData portComponent = new PortComponentMetaData(webserviceDescription);
                portComponent.setPortComponentName(oldPortComponent.getPortComponentName()); //unique ID
                portComponent.setServiceEndpointInterface(oldPortComponent.getServiceEndpointInterface());
                portComponent.setWsdlPort(oldPortComponent.getWsdlPort());
                portComponent.setWsdlService(oldPortComponent.getWsdlService());
                webserviceDescription.addPortComponent(portComponent);
            }
            metadata.addWebserviceDescription(webserviceDescription);
        }
        urlPatternToClassNameMap.putAll(oldEndpoint.getUrlPatternToClassNameMap());
        // Destroy the old context, now the oldEndpoint's stop() will become a NOOP
        oldEndpoint.stop();

        _metadata = metadata;
        _urlPatternToClassNameMap = urlPatternToClassNameMap;
        PUBLISHEDCONTEXTS.put(contextRoot, this);

        // Create a new context, but don't save in the old endpoint, because the stop() operation will be a NOOP
        // We will end up with Phantom JBossWSEndpoints just holding a publiser, urlmap and metadata. TODO clean them up!
        _context = _publisher.publish(contextRoot, Thread.currentThread().getContextClassLoader(), urlPatternToClassNameMap, metadata);

        for (Endpoint ep : _context.getEndpoints()) {
            BaseWebService wsProvider = (BaseWebService) ep.getInstanceProvider().getInstance(BaseWebService.class.getName()).getValue();
            EndpointInfo oldEndpointInfo = oldEndpointInfos.get(ep.getShortName());
            if (oldEndpointInfo != null) {
                wsProvider.setInvocationClassLoader(oldEndpointInfo.getInvocationClassLoader());
                // Hook the old handler
                wsProvider.setConsumer(oldEndpointInfo.getConsumer());
            } else {
                wsProvider.setInvocationClassLoader(Thread.currentThread().getContextClassLoader());
                // Hook the new handler
                wsProvider.setConsumer(handler);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void stop() {
        if (_context != null && _publisher != null) {
            try {
                String contextRoot = _context.getContext();
                //undeploy endpoints
                _publisher.destroy(_context);
                PUBLISHEDCONTEXTS.remove(contextRoot);
            } catch (Exception e) {
                LOG.error(e);
            }
        }
    }

    /**
     * Check if the context is already published.
     * @param contextRoot the context to check
     * @return true if it is, false other wise
     */
    public static Boolean isPublished(String contextRoot) {
        return PUBLISHEDCONTEXTS.containsKey(contextRoot);
    }

    private static Map<String, EndpointInfo> getEndpointInfos(final List<Endpoint> endpoints) {
        Map<String, EndpointInfo> endpointInfos = new HashMap<String, EndpointInfo>();
        for (Endpoint ep : endpoints) {
            EndpointInfo info = new EndpointInfo();
            BaseWebService wsProvider = (BaseWebService) ep.getInstanceProvider().getInstance(BaseWebService.class.getName()).getValue();
            info.setShortName(ep.getShortName());
            info.setInvocationClassLoader(wsProvider.getInvocationClassLoader());
            // Hook the handler
            info.setConsumer(wsProvider.getConsumer());
            endpointInfos.put(info.getShortName(), info);
        }
        return endpointInfos;
    }

    private static class EndpointInfo {
        private String _shortName;
        private ClassLoader _invocationClassLoader;
        private InboundHandler _serviceConsumer;

        /**
         * Get the endpoints short name.
         * @return the short name.
         */
        public String getShortName() {
            return _shortName;
        }

        /**
         * Set the endpoints short name.
         * @param shortName the short name.
         */
        public void setShortName(String shortName) {
            _shortName = shortName;
        }

        /**
         * Get the service handler.
         * @return the service handler.
         */
        public InboundHandler getConsumer() {
            return _serviceConsumer;
        }

        /**
         * Sets the service handler.
         * @param serviceConsumer the service handler.
         */
        public void setConsumer(final InboundHandler serviceConsumer) {
            _serviceConsumer = serviceConsumer;
        }

        /**
         * Get the Invocation TCCL.
         * @return the classloader to be set.
         */
        public ClassLoader getInvocationClassLoader() {
            return _invocationClassLoader;
        }

        /**
         * Sets the Invocation TCCL.
         * @param classLoader the classloader to be set.
         */
        public void setInvocationClassLoader(final ClassLoader classLoader) {
            _invocationClassLoader = classLoader;
        }
    }

    static {
        try {
            ClassLoader loader = ClassLoaderProvider.getDefaultProvider().getWebServiceSubsystemClassLoader();
            FACTORY = ServiceLoader.load(EndpointPublisherFactory.class, loader).iterator().next();
        } catch (Exception e) {
            throw new WebServicePublishException(e);
        }
    }
}
