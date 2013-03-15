/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.switchyard.component.soap.util;

import java.io.IOException;
import java.util.List;
//import java.util.ResourceBundle;

import org.apache.cxf.frontend.ServerFactoryBean;
import org.jboss.ws.api.annotation.EndpointConfig;
import org.jboss.ws.api.util.BundleUtils;
import org.jboss.wsf.spi.SPIProvider;
import org.jboss.wsf.spi.SPIProviderResolver;
import org.jboss.wsf.spi.classloading.ClassLoaderProvider;
import org.jboss.wsf.spi.deployment.Endpoint;
import org.jboss.wsf.spi.deployment.UnifiedVirtualFile;
import org.jboss.wsf.spi.management.ServerConfig;
import org.jboss.wsf.spi.management.ServerConfigFactory;
import org.jboss.wsf.spi.metadata.config.ConfigMetaDataParser;
import org.jboss.wsf.spi.metadata.config.ConfigRoot;
import org.jboss.wsf.stack.cxf.JBossWSInvoker;
import org.jboss.wsf.stack.cxf.client.configuration.BeanCustomizer;
import org.jboss.wsf.stack.cxf.deployment.EndpointImpl;
import org.jboss.wsf.stack.cxf.deployment.WSDLFilePublisher;

/**
 * 
 * @author alessio.soldano@jboss.com
 * @since 16-Jun-2010
 */
public class ServerBeanCustomizer extends BeanCustomizer
{
   //private static final ResourceBundle bundle = BundleUtils.getBundle(ServerBeanCustomizer.class);
   private static ServerConfig serverConfig;
   
   private WSDLFilePublisher wsdlPublisher;

   private List<Endpoint> depEndpoints;
   
   private UnifiedVirtualFile deploymentRoot;
   
   private String configName = org.jboss.wsf.spi.metadata.config.EndpointConfig.STANDARD_ENDPOINT_CONFIG;
   private String configFile;

   @Override
   public void customize(Object beanInstance)
   {
      if (beanInstance instanceof EndpointImpl)
      {
         configureEndpoint((EndpointImpl) beanInstance);
      }
      if (beanInstance instanceof ServerFactoryBean)
      {
         ServerFactoryBean factory = (ServerFactoryBean) beanInstance;

         if (factory.getInvoker() instanceof JBossWSInvoker)
         {
            ((JBossWSInvoker) factory.getInvoker()).setTargetBean(factory.getServiceBean());
         }
         if (depEndpoints != null)
         {
            final String targetBeanName = factory.getServiceBean().getClass().getName();
            for (Endpoint depEndpoint : depEndpoints)
            {
               if (depEndpoint.getTargetBeanClass().getName().equals(targetBeanName))
               {
                  depEndpoint.addAttachment(ServerFactoryBean.class, factory);
               }
            }
         }
      }
      super.customize(beanInstance);
   }

   protected void configureEndpoint(EndpointImpl endpoint)
   {
      //Configure wsdl file publisher
      if (wsdlPublisher != null)
      {
         endpoint.setWsdlPublisher(wsdlPublisher);
      }
      //Configure according to the specified jaxws endpoint configuration
      if (!endpoint.isPublished()) //before publishing, we set the jaxws conf
      {
         Object implementor = endpoint.getImplementor();

         // setup our invoker for http endpoints if invoker is not configured in jbossws-cxf.xml DD
         boolean isHttpEndpoint = endpoint.getAddress() != null && endpoint.getAddress().substring(0, 5).toLowerCase().startsWith("http");
         if ((endpoint.getInvoker() == null) && isHttpEndpoint) 
         {
            endpoint.setInvoker(new JBossWSInvoker());
         }
         EndpointConfig epConfig = implementor.getClass().getAnnotation(EndpointConfig.class);

         System.out.println("implementer ..... " + implementor);
         System.out.println("class ..... " + implementor.getClass());
         System.out.println("epConfig ..... " + implementor.getClass().getAnnotation(EndpointConfig.class));

         if (epConfig != null)
         {
            if (!epConfig.configName().isEmpty())
            {
               configName = epConfig.configName();
            }
            if (!epConfig.configFile().isEmpty())
            {
               configFile = epConfig.configFile();
            }
         }

        System.out.println("configFile .... " + configFile);
         if (configFile == null)
         {
        System.out.println("ServerConfig is being set ............................................ ");
            //use endpoint configs from AS domain
            ServerConfig sc = getServerConfig();
            for (org.jboss.wsf.spi.metadata.config.EndpointConfig config : sc.getEndpointConfigs())
            {
               if (config.getConfigName().equals(configName))
               {
                  endpoint.setEndpointConfig(config);
                  break;
               }
            }
         }
         else
         {
        System.out.println("ConfigRoot is being set ............................................ ");
            //look for provided endpoint config file
            try
            {
               UnifiedVirtualFile vf = deploymentRoot.findChild(configFile);
               ConfigRoot config = ConfigMetaDataParser.parse(vf.toURL());
               endpoint.setEndpointConfig(config.getEndpointConfigByName(configName));
            }
            catch (IOException e)
            {
               throw new RuntimeException("Could not read config file " + configFile);
            }
         }
      }
   }
   
   private static synchronized ServerConfig getServerConfig()
   {
      if (serverConfig == null)
      {
         final ClassLoader cl = ClassLoaderProvider.getDefaultProvider().getServerIntegrationClassLoader();
         SPIProvider spiProvider = SPIProviderResolver.getInstance(cl).getProvider();
         serverConfig = spiProvider.getSPI(ServerConfigFactory.class, cl).getServerConfig();
      }
      return serverConfig;
   }
   
   public void setDeploymentRoot(UnifiedVirtualFile deploymentRoot)
   {
      this.deploymentRoot = deploymentRoot;
   }

   public void setWsdlPublisher(WSDLFilePublisher wsdlPublisher)
   {
      this.wsdlPublisher = wsdlPublisher;
   }

   public void setDeploymentEndpoints(List<Endpoint> endpoints)
   {
      this.depEndpoints = endpoints;
   }

   public void setConfigName(String configName)
   {
      this.configName = configName;
   }

   public void setConfigFile(String configFile)
   {
      this.configFile = configFile;
   }
}
