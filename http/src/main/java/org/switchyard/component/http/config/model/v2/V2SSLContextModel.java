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
package org.switchyard.component.http.config.model.v2;

import org.switchyard.component.http.config.model.HttpNameValueModel;
import org.switchyard.component.http.config.model.HttpNameValueModel.HttpName;
import org.switchyard.component.http.config.model.SSLContextModel;
import org.switchyard.component.http.config.model.v1.V1HttpNameValueModel;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;
import org.switchyard.config.model.BaseModel;

/**
 * An SSL Config trust Model version 2.
 */
public class V2SSLContextModel extends BaseModel implements SSLContextModel {

    private static final String[] MODEL_CHILDREN_ORDER = new String[]{
        HttpName.keystore.name(),
        HttpName.keystorePass.name(),
        HttpName.protocols.name(),
    };

    private HttpNameValueModel _keystore;
    private HttpNameValueModel _keystorePass;
    private HttpNameValueModel _keystoreProtocols;

    /**
     * Creates a new SSLContextModel.
     * @param namespace namespace
     */
    public V2SSLContextModel(String namespace) {
        super(namespace, HttpName.ssl.name());
        setModelChildrenOrder(MODEL_CHILDREN_ORDER);
    }

    /**
     * Creates a new SSLContextModel.
     * @param namespace namespace
     * @param name the name of the model
     */
    public V2SSLContextModel(String namespace, String name) {
        super(namespace, name);
        setModelChildrenOrder(MODEL_CHILDREN_ORDER);
    }

    /**
     * Creates a new SSLContextModel with the specified configuration and descriptor.
     * @param config the configuration
     * @param desc the descriptor
     */
    public V2SSLContextModel(Configuration config, Descriptor desc) {
        super(config, desc);
        setModelChildrenOrder(MODEL_CHILDREN_ORDER);
    }

    /**
     * {@inheritDoc}
     */
    public String getKeystore() {
        if (_keystore == null) {
            _keystore = getNameValue(HttpName.keystore);
        }
        return _keystore != null ? _keystore.getValue() : null;
    }

    /**
     * {@inheritDoc}
     */
    public SSLContextModel setKeystore(String keystore) {
        _keystore = setNameValue(_keystore, HttpName.keystore, keystore);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public String getKeystorePass() {
        if (_keystorePass == null) {
            _keystorePass = getNameValue(HttpName.keystorePass);
        }
        return _keystorePass != null ? _keystorePass.getValue() : null;
    }

    /**
     * {@inheritDoc}
     */
    public SSLContextModel setKeystorePass(String keystorePass) {
        _keystorePass = setNameValue(_keystorePass, HttpName.keystorePass, keystorePass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public String getProtocols() {
        if (_keystoreProtocols == null) {
            _keystoreProtocols = getNameValue(HttpName.protocols);
        }
        return _keystoreProtocols != null ? _keystoreProtocols.getValue() : null;
    }

    /**
     * {@inheritDoc}
     */
    public SSLContextModel setProtocols(String protocols) {
        _keystoreProtocols = setNameValue(_keystoreProtocols, HttpName.protocols, protocols);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public String[] getProtocolsAsArray() {
        String protocols = getProtocols();
        if (protocols != null) {
            return protocols.split(",");
        } else {
            return new String[0];
        }
    }

    protected HttpNameValueModel getNameValue(HttpName name) {
        return (HttpNameValueModel)getFirstChildModel(name.name());
    }

    protected HttpNameValueModel setNameValue(HttpNameValueModel model, HttpName name, String value) {
        if (value != null) {
            if (model == null) {
                model = getNameValue(name);
            }
            if (model == null) {
                model = new V1HttpNameValueModel(getNamespaceURI(), name);
                setChildModel(model);
            }
            model.setValue(value);
        } else {
            getModelConfiguration().removeChildren(name.name());
            model = null;
        }
        return model;
    }

}
