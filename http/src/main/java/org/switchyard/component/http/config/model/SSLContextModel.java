/*
 * Copyright 2015 Red Hat Inc. and/or its affiliates and other contributors.
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
package org.switchyard.component.http.config.model;

import org.switchyard.config.model.Model;

/**
 * An SSL Config trust Model.
 */
public interface SSLContextModel extends Model {

    /**
     * Gets keystore.
     * @return keystore
     */
    String getKeystore();

    /**
     * Sets keystore.
     * @param keystore keystore
     * @return this BindingModel (useful for chaining)
     */
    SSLContextModel setKeystore(String keystore);

    /**
     * Gets password.
     * @return password
     */
    String getKeystorePass();

    /**
     * Sets password.
     * @param password password
     * @return this BindingModel (useful for chaining)
     */
    SSLContextModel setKeystorePass(String password);

    /**
     * Gets protocol.
     * @return protocol
     */
    String getProtocols();

    /**
     * Gets the SSL protocols supported by this binding model.
     * 
     * @return the list of SSL protocols as an array
     */
    public String[] getProtocolsAsArray();

    /**
     * Sets protocol.
     * @param protocols list of comma seperated protocols
     * @return this BindingModel (useful for chaining)
     */
    SSLContextModel setProtocols(String protocols);

}
