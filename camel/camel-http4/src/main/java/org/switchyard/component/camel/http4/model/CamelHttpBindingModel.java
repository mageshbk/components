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
package org.switchyard.component.camel.http4.model;

import java.net.URI;

import org.switchyard.component.camel.common.model.CamelBindingModel;

/**
 * Camel HTTP binding model.
 */
public interface CamelHttpBindingModel extends CamelBindingModel {
    /**
     * Gets resourceUri.
     * @return resourceUri
     */
    URI getResourceUri();

    /**
     * Sets resourceUri.
     * @param resourceUri resourceUri
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setResourceUri(URI resourceUri);

    /**
     * Gets host.
     * @return host
     */
    String getHost();

    /**
     * Sets host.
     * @param host host
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setHost(String host);

    /**
     * Gets port.
     * @return port
     */
    Integer getPort();

    /**
     * Sets port.
     * @param port port
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setPort(Integer port);

    /**
     * Gets maxTotalConnections.
     * @return maxTotalConnections
     */
    Integer getMaxTotalConnections();

    /**
     * Sets maxTotalConnections.
     * @param maxTotalConnections maxTotalConnections
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setMaxTotalConnections(Integer maxTotalConnections);

    /**
     * Gets connectionsPerRoute.
     * @return connectionsPerRoute
     */
    Integer getConnectionsPerRoute();

    /**
     * Sets connectionsPerRoute.
     * @param connectionsPerRoute connectionsPerRoute
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setConnectionsPerRoute(Integer connectionsPerRoute);

    /**
     * Gets cookieStore.
     * @return cookieStore
     */
    String getCookieStore();

    /**
     * Sets cookieStore.
     * @param cookieStore cookieStore
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setCookieStore(String cookieStore);

    /**
     * Gets httpClientConfigurer.
     * @return httpClientConfigurer
     */
    String getHttpClientConfigurer();

    /**
     * Sets httpClientConfigurer.
     * @param httpClientConfigurer httpClientConfigurer
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setHttpClientConfigurer(String httpClientConfigurer);

    /**
     * Gets clientConnectionManager.
     * @return clientConnectionManager
     */
    String getClientConnectionManager();

    /**
     * Sets clientConnectionManager.
     * @param clientConnectionManager clientConnectionManager
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setClientConnectionManager(String clientConnectionManager);

    /**
     * Gets httpBinding.
     * @return httpBinding
     */
    String getHttpBinding();

    /**
     * Sets httpBinding.
     * @param httpBinding httpBinding
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setHttpBinding(String httpBinding);

    /**
     * Gets httpContext.
     * @return httpContext
     */
    String getHttpContext();

    /**
     * Sets httpContext.
     * @param httpContext httpContext
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setHttpContext(String httpContext);

    /**
     * Gets sslContextParameters.
     * @return sslContextParameters
     */
    String getSslContextParameters();

    /**
     * Sets sslContextParameters.
     * @param sslContextParameters sslContextParameters
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setSslContextParameters(String sslContextParameters);

    /**
     * Gets x509HostnameVerifier.
     * @return x509HostnameVerifier
     */
    String getX509HostnameVerifier();

    /**
     * Sets x509HostnameVerifier.
     * @param x509HostnameVerifier x509HostnameVerifier
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setX509HostnameVerifier(String x509HostnameVerifier);

    /**
     * Gets connectionTimeToLive.
     * @return connectionTimeToLive
     */
    Long getConnectionTimeToLive();

    /**
     * Sets connectionTimeToLive.
     * @param connectionTimeToLive connectionTimeToLive
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setConnectionTimeToLive(Long connectionTimeToLive);

    /**
     * Gets authenticationPreemptive.
     * @return authenticationPreemptive
     */
    Boolean isAuthenticationPreemptive();

    /**
     * Sets authenticationPreemptive.
     * @param authenticationPreemptive authenticationPreemptive
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setAuthenticationPreemptive(Boolean authenticationPreemptive);

    /**
     * Gets throwExceptionOnFailure.
     * @return throwExceptionOnFailure
     */
    Boolean isThrowExceptionOnFailure();

    /**
     * Sets throwExceptionOnFailure.
     * @param throwExceptionOnFailure throwExceptionOnFailure
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setThrowExceptionOnFailure(Boolean throwExceptionOnFailure);

    /**
     * Gets bridgeEndpoint.
     * @return bridgeEndpoint
     */
    Boolean isBridgeEndpoint();

    /**
     * Sets bridgeEndpoint.
     * @param bridgeEndpoint bridgeEndpoint
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setBridgeEndpoint(Boolean bridgeEndpoint);

    /**
     * Gets clearExpiredCookies.
     * @return clearExpiredCookies
     */
    Boolean isClearExpiredCookies();

    /**
     * Sets clearExpiredCookies.
     * @param clearExpiredCookies clearExpiredCookies
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setClearExpiredCookies(Boolean clearExpiredCookies);

    /**
     * Gets disableStreamCache.
     * @return disableStreamCache
     */
    Boolean isDisableStreamCache();

    /**
     * Sets disableStreamCache.
     * @param disableStreamCache disableStreamCache
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setDisableStreamCache(Boolean disableStreamCache);

    /**
     * Gets headerFilterStrategy.
     * @return headerFilterStrategy
     */
    String getHeaderFilterStrategy();

    /**
     * Sets headerFilterStrategy.
     * @param headerFilterStrategy headerFilterStrategy
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setHeaderFilterStrategy(String headerFilterStrategy);

    /**
     * Gets transferException.
     * @return transferException
     */
    Boolean isTransferException();

    /**
     * Sets transferException.
     * @param transferException transferException
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setTransferException(Boolean transferException);

    /**
     * Gets urlRewrite.
     * @return urlRewrite
     */
    String getUrlRewrite();

    /**
     * Sets urlRewrite.
     * @param urlRewrite urlRewrite
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setUrlRewrite(String urlRewrite);

    /**
     * Gets authUsername.
     * @return authUsername
     */
    String getAuthUsername();

    /**
     * Sets authUsername.
     * @param authUsername authUsername
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setAuthUsername(String authUsername);

    /**
     * Gets authPassword.
     * @return authPassword
     */
    String getAuthPassword();

    /**
     * Sets authPassword.
     * @param authPassword authPassword
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setAuthPassword(String authPassword);

    /**
     * Gets authDomain.
     * @return authDomain
     */
    String getAuthDomain();

    /**
     * Sets authDomain.
     * @param authDomain authDomain
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setAuthDomain(String authDomain);

    /**
     * Gets authHost.
     * @return authHost
     */
    String getAuthHost();

    /**
     * Sets authHost.
     * @param authHost authHost
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setAuthHost(String authHost);

    /**
     * Gets proxyAuthHost.
     * @return proxyAuthHost
     */
    String getProxyAuthHost();

    /**
     * Sets proxyAuthHost.
     * @param proxyAuthHost proxyAuthHost
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setProxyAuthHost(String proxyAuthHost);

    /**
     * Gets proxyAuthPort.
     * @return proxyAuthPort
     */
    Integer getProxyAuthPort();

    /**
     * Sets proxyAuthPort.
     * @param proxyAuthPort proxyAuthPort
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setProxyAuthPort(Integer proxyAuthPort);

    /**
     * Gets proxyAuthScheme.
     * @return proxyAuthScheme
     */
    String getProxyAuthScheme();

    /**
     * Sets proxyAuthScheme.
     * @param proxyAuthScheme proxyAuthScheme
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setProxyAuthScheme(String proxyAuthScheme);

    /**
     * Gets proxyAuthUsername.
     * @return proxyAuthUsername
     */
    String getProxyAuthUsername();

    /**
     * Sets proxyAuthUsername.
     * @param proxyAuthUsername proxyAuthUsername
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setProxyAuthUsername(String proxyAuthUsername);

    /**
     * Gets proxyAuthPassword.
     * @return proxyAuthPassword
     */
    String getProxyAuthPassword();

    /**
     * Sets proxyAuthPassword.
     * @param proxyAuthPassword proxyAuthPassword
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setProxyAuthPassword(String proxyAuthPassword);

    /**
     * Gets proxyAuthDomain.
     * @return proxyAuthDomain
     */
    String getProxyAuthDomain();

    /**
     * Sets proxyAuthDomain.
     * @param proxyAuthDomain proxyAuthDomain
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setProxyAuthDomain(String proxyAuthDomain);

    /**
     * Gets proxyAuthNtHost.
     * @return proxyAuthNtHost
     */
    String getProxyAuthNtHost();

    /**
     * Sets proxyAuthNtHost.
     * @param proxyAuthNtHost proxyAuthNtHost
     * @return this BindingModel (useful for chaining)
     */
    CamelHttpBindingModel setProxyAuthNtHost(String proxyAuthNtHost);

}
