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

import static junit.framework.Assert.assertEquals;

import java.net.URI;
import java.text.ParseException;

import org.apache.camel.component.http4.HttpEndpoint;
import org.switchyard.component.camel.config.test.v1.V1BaseCamelReferenceBindingModelTest;
import org.switchyard.component.camel.http4.model.CamelHttpNamespace;
import org.switchyard.component.camel.http4.model.v1.V1CamelHttpBindingModel;

/**
 * Test of http4 binding model.
 */
public class V1CamelHttpBindingModelTest extends V1BaseCamelReferenceBindingModelTest<V1CamelHttpBindingModel, HttpEndpoint> {

    private static final String CAMEL_XML = "/v1/switchyard-http4-binding.xml";

    private static final String CAMEL_URI = 
        "http4:localhost:8080/resource?maxTotalConnections=200&connectionsPerRoute=20&clientConnectionManager=#clientConnectionManager&connectionTimeToLive=-1&authenticationPreemptive=false&throwExceptionOnFailure=true&bridgeEndpoint=false&clearExpiredCookies=true&disableStreamCache=true&transferException=false";
    private static final URI RESOURCEURI = URI.create("resource");
    private static final String HOST = "localhost";
    private static final Integer PORT = 8080;
    private static final Integer MAXTOTALCONNECTIONS = 200;
    private static final Integer CONNECTIONSPERROUTE = 20;
    private static final String COOKIESTORE = "#basicCookieStore";
    private static final String HTTPCLIENTCONFIGURER = "#clientConfigurer";
    private static final String CLIENTCONNECTIONMANAGER = "#clientConnectionManager";
    private static final String HTTPBINDING = "#httpBinding";
    private static final String HTTPCONTEXT = "#httpContext";
    private static final String SSLCONTEXTPARAMETERS = "#sslContextParameters";
    private static final String X509HOSTNAMEVERIFIER = "#strictHostnameVerifier";
    private static final Long CONNECTIONTIMETOLIVE = -1L;
    private static final Boolean AUTHENTICATIONPREEMPTIVE = false;
    private static final Boolean THROWEXCEPTIONONFAILURE = true;
    private static final Boolean BRIDGEENDPOINT = false;
    private static final Boolean CLEAREXPIREDCOOKIES = true;
    private static final Boolean DISABLESTREAMCACHE = true;
    private static final String HEADERFILTERSTRATEGY = "#headerFilter";
    private static final Boolean TRANSFEREXCEPTION = false;
    private static final String URLREWRITE = "#urlRewrite";
    private static final String AUTHUSERNAME = "none";
    private static final String AUTHPASSWORD = "none";
    private static final String AUTHDOMAIN = "domain";
    private static final String AUTHHOST = "localhost";
    private static final String PROXYAUTHHOST = "proxyHost";
    private static final Integer PROXYAUTHPORT = 80;
    private static final String PROXYAUTHSCHEME = "scheme";
    private static final String PROXYAUTHUSERNAME = "none";
    private static final String PROXYAUTHPASSWORD = "none";
    private static final String PROXYAUTHDOMAIN = "domain";
    private static final String PROXYAUTHNTHOST = "nthost";

    public V1CamelHttpBindingModelTest() throws ParseException {
        super(HttpEndpoint.class, CAMEL_XML);
        setSkipCamelEndpointTesting(true);
    }

    @Override
    protected void createModelAssertions(V1CamelHttpBindingModel model) {
        assertEquals(RESOURCEURI, model. getResourceUri());
        assertEquals(HOST, model. getHost());
        assertEquals(PORT, model. getPort());
        assertEquals(MAXTOTALCONNECTIONS, model. getMaxTotalConnections());
        assertEquals(CONNECTIONSPERROUTE, model. getConnectionsPerRoute());
        //assertEquals(COOKIESTORE, model. getCookieStore());
        //assertEquals(HTTPCLIENTCONFIGURER, model. getHttpClientConfigurer());
        assertEquals(CLIENTCONNECTIONMANAGER, model. getClientConnectionManager());
        //assertEquals(HTTPBINDING, model. getHttpBinding());
        //assertEquals(HTTPCONTEXT, model. getHttpContext());
        //assertEquals(SSLCONTEXTPARAMETERS, model. getSslContextParameters());
        //assertEquals(X509HOSTNAMEVERIFIER, model. getX509HostnameVerifier());
        assertEquals(CONNECTIONTIMETOLIVE, model. getConnectionTimeToLive());
        assertEquals(AUTHENTICATIONPREEMPTIVE, model. isAuthenticationPreemptive());
        assertEquals(THROWEXCEPTIONONFAILURE, model. isThrowExceptionOnFailure());
        assertEquals(BRIDGEENDPOINT, model. isBridgeEndpoint());
        assertEquals(CLEAREXPIREDCOOKIES, model. isClearExpiredCookies());
        assertEquals(DISABLESTREAMCACHE, model. isDisableStreamCache());
        //assertEquals(HEADERFILTERSTRATEGY, model. getHeaderFilterStrategy());
        assertEquals(TRANSFEREXCEPTION, model. isTransferException());
        //assertEquals(URLREWRITE, model. getUrlRewrite());
        /*assertEquals(AUTHUSERNAME, model. getAuthUsername());
        assertEquals(AUTHPASSWORD, model. getAuthPassword());
        assertEquals(AUTHDOMAIN, model. getAuthDomain());
        assertEquals(AUTHHOST, model. getAuthHost());
        assertEquals(PROXYAUTHHOST, model. getProxyAuthHost());
        assertEquals(PROXYAUTHPORT, model. getProxyAuthPort());
        assertEquals(PROXYAUTHSCHEME, model. getProxyAuthScheme());
        assertEquals(PROXYAUTHUSERNAME, model. getProxyAuthUsername());
        assertEquals(PROXYAUTHPASSWORD, model. getProxyAuthPassword());
        assertEquals(PROXYAUTHDOMAIN, model. getProxyAuthDomain());
        assertEquals(PROXYAUTHNTHOST, model. getProxyAuthNtHost());*/
    }

    @Override
    protected V1CamelHttpBindingModel createTestModel() {
        V1CamelHttpBindingModel abm = new V1CamelHttpBindingModel(CamelHttpNamespace.V_2_0.uri())
                                    .setResourceUri(RESOURCEURI)
                                    .setHost(HOST)
                                    .setPort(PORT)
                                    .setMaxTotalConnections(MAXTOTALCONNECTIONS)
                                    .setConnectionsPerRoute(CONNECTIONSPERROUTE)
                                    //.setCookieStore(COOKIESTORE)
                                    //.setHttpClientConfigurer(HTTPCLIENTCONFIGURER)
                                    .setClientConnectionManager(CLIENTCONNECTIONMANAGER)
                                    //.setHttpBinding(HTTPBINDING)
                                    //.setHttpContext(HTTPCONTEXT)
                                    //.setSslContextParameters(SSLCONTEXTPARAMETERS)
                                    //.setX509HostnameVerifier(X509HOSTNAMEVERIFIER)
                                    .setConnectionTimeToLive(CONNECTIONTIMETOLIVE)
                                    .setAuthenticationPreemptive(AUTHENTICATIONPREEMPTIVE)
                                    .setThrowExceptionOnFailure(THROWEXCEPTIONONFAILURE)
                                    .setBridgeEndpoint(BRIDGEENDPOINT)
                                    .setClearExpiredCookies(CLEAREXPIREDCOOKIES)
                                    .setDisableStreamCache(DISABLESTREAMCACHE)
                                    //.setHeaderFilterStrategy(HEADERFILTERSTRATEGY)
                                    .setTransferException(TRANSFEREXCEPTION)
                                    //.setUrlRewrite(URLREWRITE)
                                    /*.setAuthUsername(AUTHUSERNAME)
                                    .setAuthPassword(AUTHPASSWORD)
                                    .setAuthDomain(AUTHDOMAIN)
                                    .setAuthHost(AUTHHOST)
                                    .setProxyAuthHost(PROXYAUTHHOST)
                                    .setProxyAuthPort(PROXYAUTHPORT)
                                    .setProxyAuthScheme(PROXYAUTHSCHEME)
                                    .setProxyAuthUsername(PROXYAUTHUSERNAME)
                                    .setProxyAuthPassword(PROXYAUTHPASSWORD)
                                    .setProxyAuthDomain(PROXYAUTHDOMAIN)
                                    .setProxyAuthNtHost(PROXYAUTHNTHOST)*/
                                    ;
        return abm;
    }

    @Override
    protected String createEndpointUri() {
        return CAMEL_URI;
    }

}
