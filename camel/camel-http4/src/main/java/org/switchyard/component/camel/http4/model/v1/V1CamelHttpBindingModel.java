/* 
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.switchyard.component.camel.http4.model.v1;

import java.net.URI;
import java.util.List;

import org.switchyard.component.camel.common.QueryString;
import org.switchyard.component.camel.common.model.v1.V1BaseCamelBindingModel;
import org.switchyard.component.camel.http4.model.CamelHttpBindingModel;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

/**
 * V1Camel HTTP4 binding model.
 */
public class V1CamelHttpBindingModel extends V1BaseCamelBindingModel
    implements CamelHttpBindingModel {

    /**
     * The name of this binding type ("binding.http4").
     */
    public static final String HTTP = "http4";

    private static final String RESOURCEURI = "resourceUri";
    private static final String HOST = "host";
    private static final String PORT = "port";
    private static final String MAXTOTALCONNECTIONS = "maxTotalConnections";
    private static final String CONNECTIONSPERROUTE = "connectionsPerRoute";
    private static final String COOKIESTORE = "cookieStore";
    private static final String HTTPCLIENTCONFIGURER = "httpClientConfigurer";
    private static final String CLIENTCONNECTIONMANAGER = "clientConnectionManager";
    private static final String HTTPBINDING = "httpBinding";
    private static final String HTTPCONTEXT = "httpContext";
    private static final String SSLCONTEXTPARAMETERS = "sslContextParameters";
    private static final String X509HOSTNAMEVERIFIER = "x509HostnameVerifier";
    private static final String CONNECTIONTIMETOLIVE = "connectionTimeToLive";
    private static final String AUTHENTICATIONPREEMPTIVE = "authenticationPreemptive";
    private static final String THROWEXCEPTIONONFAILURE = "throwExceptionOnFailure";
    private static final String BRIDGEENDPOINT = "bridgeEndpoint";
    private static final String CLEAREXPIREDCOOKIES = "clearExpiredCookies";
    private static final String DISABLESTREAMCACHE = "disableStreamCache";
    private static final String HEADERFILTERSTRATEGY = "headerFilterStrategy";
    private static final String TRANSFEREXCEPTION = "transferException";
    private static final String URLREWRITE = "urlRewrite";
    private static final String AUTHUSERNAME = "authUsername";
    private static final String AUTHPASSWORD = "authPassword";
    private static final String AUTHDOMAIN = "authDomain";
    private static final String AUTHHOST = "authHost";
    private static final String PROXYAUTHHOST = "proxyAuthHost";
    private static final String PROXYAUTHPORT = "proxyAuthPort";
    private static final String PROXYAUTHSCHEME = "proxyAuthScheme";
    private static final String PROXYAUTHUSERNAME = "proxyAuthUsername";
    private static final String PROXYAUTHPASSWORD = "proxyAuthPassword";
    private static final String PROXYAUTHDOMAIN = "proxyAuthDomain";
    private static final String PROXYAUTHNTHOST = "proxyAuthNtHost";

    /**
     * Create a new HttpBindingModel.
     * @param namespace namespace
     */
    public V1CamelHttpBindingModel(String namespace) {
        super(HTTP, namespace);

        setModelChildrenOrder(RESOURCEURI, HOST, PORT, MAXTOTALCONNECTIONS, CONNECTIONSPERROUTE, COOKIESTORE, HTTPCLIENTCONFIGURER, CLIENTCONNECTIONMANAGER, HTTPBINDING, HTTPCONTEXT, SSLCONTEXTPARAMETERS, X509HOSTNAMEVERIFIER, CONNECTIONTIMETOLIVE, AUTHENTICATIONPREEMPTIVE, THROWEXCEPTIONONFAILURE, BRIDGEENDPOINT, CLEAREXPIREDCOOKIES, DISABLESTREAMCACHE, HEADERFILTERSTRATEGY, TRANSFEREXCEPTION, URLREWRITE, AUTHUSERNAME, AUTHPASSWORD, AUTHDOMAIN, AUTHHOST, PROXYAUTHHOST, PROXYAUTHPORT, PROXYAUTHSCHEME, PROXYAUTHUSERNAME, PROXYAUTHPASSWORD, PROXYAUTHDOMAIN, PROXYAUTHNTHOST);
    }

    /**
     * Create a HttpBindingModel from the specified configuration and descriptor.
     * 
     * @param config The switchyard configuration instance.
     * @param desc The switchyard descriptor instance.
     */
    public V1CamelHttpBindingModel(Configuration config, Descriptor desc) {
        super(config, desc);
    }

    @Override
    public URI getResourceUri() {
        String uriStr = getConfig(RESOURCEURI);
        if (uriStr != null) {
            return URI.create(uriStr);
        } else {
            return null;
        }
    }

    @Override
    public V1CamelHttpBindingModel setResourceUri(URI resourceUri) {
        setConfig(RESOURCEURI, resourceUri);
        return this;
    }


    @Override
    public String getHost() {
        return getConfig(HOST);
    }

    @Override
    public V1CamelHttpBindingModel setHost(String host) {
        setConfig(HOST, host);
        return this;
    }


    @Override
    public Integer getPort() {
        return getIntegerConfig(PORT);
    }

    @Override
    public V1CamelHttpBindingModel setPort(Integer port) {
        setConfig(PORT, port);
        return this;
    }


    @Override
    public Integer getMaxTotalConnections() {
        return getIntegerConfig(MAXTOTALCONNECTIONS);
    }

    @Override
    public V1CamelHttpBindingModel setMaxTotalConnections(Integer maxTotalConnections) {
        setConfig(MAXTOTALCONNECTIONS, maxTotalConnections);
        return this;
    }


    @Override
    public Integer getConnectionsPerRoute() {
        return getIntegerConfig(CONNECTIONSPERROUTE);
    }

    @Override
    public V1CamelHttpBindingModel setConnectionsPerRoute(Integer connectionsPerRoute) {
        setConfig(CONNECTIONSPERROUTE, connectionsPerRoute);
        return this;
    }


    @Override
    public String getCookieStore() {
        return getConfig(COOKIESTORE);
    }

    @Override
    public V1CamelHttpBindingModel setCookieStore(String cookieStore) {
        setConfig(COOKIESTORE, cookieStore);
        return this;
    }


    @Override
    public String getHttpClientConfigurer() {
        return getConfig(HTTPCLIENTCONFIGURER);
    }

    @Override
    public V1CamelHttpBindingModel setHttpClientConfigurer(String httpClientConfigurer) {
        setConfig(HTTPCLIENTCONFIGURER, httpClientConfigurer);
        return this;
    }


    @Override
    public String getClientConnectionManager() {
        return getConfig(CLIENTCONNECTIONMANAGER);
    }

    @Override
    public V1CamelHttpBindingModel setClientConnectionManager(String clientConnectionManager) {
        setConfig(CLIENTCONNECTIONMANAGER, clientConnectionManager);
        return this;
    }


    @Override
    public String getHttpBinding() {
        return getConfig(HTTPBINDING);
    }

    @Override
    public V1CamelHttpBindingModel setHttpBinding(String httpBinding) {
        setConfig(HTTPBINDING, httpBinding);
        return this;
    }


    @Override
    public String getHttpContext() {
        return getConfig(HTTPCONTEXT);
    }

    @Override
    public V1CamelHttpBindingModel setHttpContext(String httpContext) {
        setConfig(HTTPCONTEXT, httpContext);
        return this;
    }


    @Override
    public String getSslContextParameters() {
        return getConfig(SSLCONTEXTPARAMETERS);
    }

    @Override
    public V1CamelHttpBindingModel setSslContextParameters(String sslContextParameters) {
        setConfig(SSLCONTEXTPARAMETERS, sslContextParameters);
        return this;
    }


    @Override
    public String getX509HostnameVerifier() {
        return getConfig(X509HOSTNAMEVERIFIER);
    }

    @Override
    public V1CamelHttpBindingModel setX509HostnameVerifier(String x509HostnameVerifier) {
        setConfig(X509HOSTNAMEVERIFIER, x509HostnameVerifier);
        return this;
    }


    @Override
    public Long getConnectionTimeToLive() {
        return getLongConfig(CONNECTIONTIMETOLIVE);
    }

    @Override
    public V1CamelHttpBindingModel setConnectionTimeToLive(Long connectionTimeToLive) {
        setConfig(CONNECTIONTIMETOLIVE, connectionTimeToLive);
        return this;
    }


    @Override
    public Boolean isAuthenticationPreemptive() {
        return getBooleanConfig(AUTHENTICATIONPREEMPTIVE);
    }

    @Override
    public V1CamelHttpBindingModel setAuthenticationPreemptive(Boolean authenticationPreemptive) {
        setConfig(AUTHENTICATIONPREEMPTIVE, authenticationPreemptive);
        return this;
    }


    @Override
    public Boolean isThrowExceptionOnFailure() {
        return getBooleanConfig(THROWEXCEPTIONONFAILURE);
    }

    @Override
    public V1CamelHttpBindingModel setThrowExceptionOnFailure(Boolean throwExceptionOnFailure) {
        setConfig(THROWEXCEPTIONONFAILURE, throwExceptionOnFailure);
        return this;
    }


    @Override
    public Boolean isBridgeEndpoint() {
        return getBooleanConfig(BRIDGEENDPOINT);
    }

    @Override
    public V1CamelHttpBindingModel setBridgeEndpoint(Boolean bridgeEndpoint) {
        setConfig(BRIDGEENDPOINT, bridgeEndpoint);
        return this;
    }


    @Override
    public Boolean isClearExpiredCookies() {
        return getBooleanConfig(CLEAREXPIREDCOOKIES);
    }

    @Override
    public V1CamelHttpBindingModel setClearExpiredCookies(Boolean clearExpiredCookies) {
        setConfig(CLEAREXPIREDCOOKIES, clearExpiredCookies);
        return this;
    }


    @Override
    public Boolean isDisableStreamCache() {
        return getBooleanConfig(DISABLESTREAMCACHE);
    }

    @Override
    public V1CamelHttpBindingModel setDisableStreamCache(Boolean disableStreamCache) {
        setConfig(DISABLESTREAMCACHE, disableStreamCache);
        return this;
    }


    @Override
    public String getHeaderFilterStrategy() {
        return getConfig(HEADERFILTERSTRATEGY);
    }

    @Override
    public V1CamelHttpBindingModel setHeaderFilterStrategy(String headerFilterStrategy) {
        setConfig(HEADERFILTERSTRATEGY, headerFilterStrategy);
        return this;
    }


    @Override
    public Boolean isTransferException() {
        return getBooleanConfig(TRANSFEREXCEPTION);
    }

    @Override
    public V1CamelHttpBindingModel setTransferException(Boolean transferException) {
        setConfig(TRANSFEREXCEPTION, transferException);
        return this;
    }


    @Override
    public String getUrlRewrite() {
        return getConfig(URLREWRITE);
    }

    @Override
    public V1CamelHttpBindingModel setUrlRewrite(String urlRewrite) {
        setConfig(URLREWRITE, urlRewrite);
        return this;
    }


    @Override
    public String getAuthUsername() {
        return getConfig(AUTHUSERNAME);
    }

    @Override
    public V1CamelHttpBindingModel setAuthUsername(String authUsername) {
        setConfig(AUTHUSERNAME, authUsername);
        return this;
    }


    @Override
    public String getAuthPassword() {
        return getConfig(AUTHPASSWORD);
    }

    @Override
    public V1CamelHttpBindingModel setAuthPassword(String authPassword) {
        setConfig(AUTHPASSWORD, authPassword);
        return this;
    }


    @Override
    public String getAuthDomain() {
        return getConfig(AUTHDOMAIN);
    }

    @Override
    public V1CamelHttpBindingModel setAuthDomain(String authDomain) {
        setConfig(AUTHDOMAIN, authDomain);
        return this;
    }


    @Override
    public String getAuthHost() {
        return getConfig(AUTHHOST);
    }

    @Override
    public V1CamelHttpBindingModel setAuthHost(String authHost) {
        setConfig(AUTHHOST, authHost);
        return this;
    }


    @Override
    public String getProxyAuthHost() {
        return getConfig(PROXYAUTHHOST);
    }

    @Override
    public V1CamelHttpBindingModel setProxyAuthHost(String proxyAuthHost) {
        setConfig(PROXYAUTHHOST, proxyAuthHost);
        return this;
    }


    @Override
    public Integer getProxyAuthPort() {
        return getIntegerConfig(PROXYAUTHPORT);
    }

    @Override
    public V1CamelHttpBindingModel setProxyAuthPort(Integer proxyAuthPort) {
        setConfig(PROXYAUTHPORT, proxyAuthPort);
        return this;
    }


    @Override
    public String getProxyAuthScheme() {
        return getConfig(PROXYAUTHSCHEME);
    }

    @Override
    public V1CamelHttpBindingModel setProxyAuthScheme(String proxyAuthScheme) {
        setConfig(PROXYAUTHSCHEME, proxyAuthScheme);
        return this;
    }


    @Override
    public String getProxyAuthUsername() {
        return getConfig(PROXYAUTHUSERNAME);
    }

    @Override
    public V1CamelHttpBindingModel setProxyAuthUsername(String proxyAuthUsername) {
        setConfig(PROXYAUTHUSERNAME, proxyAuthUsername);
        return this;
    }


    @Override
    public String getProxyAuthPassword() {
        return getConfig(PROXYAUTHPASSWORD);
    }

    @Override
    public V1CamelHttpBindingModel setProxyAuthPassword(String proxyAuthPassword) {
        setConfig(PROXYAUTHPASSWORD, proxyAuthPassword);
        return this;
    }


    @Override
    public String getProxyAuthDomain() {
        return getConfig(PROXYAUTHDOMAIN);
    }

    @Override
    public V1CamelHttpBindingModel setProxyAuthDomain(String proxyAuthDomain) {
        setConfig(PROXYAUTHDOMAIN, proxyAuthDomain);
        return this;
    }


    @Override
    public String getProxyAuthNtHost() {
        return getConfig(PROXYAUTHNTHOST);
    }

    @Override
    public V1CamelHttpBindingModel setProxyAuthNtHost(String proxyAuthNtHost) {
        setConfig(PROXYAUTHNTHOST, proxyAuthNtHost);
        return this;
    }


    @Override
    public URI getComponentURI() {
        Configuration modelConfiguration = getModelConfiguration();
        List<Configuration> children = modelConfiguration.getChildren();

        StringBuffer baseUri = new StringBuffer();
        baseUri.append(HTTP);
        baseUri.append(":");
        if (getHost() != null) {
            baseUri.append(getHost());
        }
        if (getPort() != null) {
            baseUri.append(":");
            baseUri.append(getPort());
        }
        if (getResourceUri() != null) {
            baseUri.append("/");
            baseUri.append(getResourceUri());
        }

        QueryString queryStr = new QueryString();
        traverseConfiguration(children, queryStr, RESOURCEURI, HOST, PORT);

        return URI.create(baseUri.toString() + queryStr.toString());
    }

}
