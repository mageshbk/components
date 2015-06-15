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

import org.switchyard.component.http.config.model.HttpNameValueModel.HttpName;
import org.switchyard.component.http.config.model.v1.V1HttpMarshaller;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;
import org.switchyard.config.model.Model;
import org.switchyard.config.model.composite.BindingModel;

/**
 * Marshaller for HTTP Gateway configurations along with SSL context.
 */
public class V2HttpMarshaller extends V1HttpMarshaller {

    /**
     * Construct a HTTP Model Marshaller with help of a Descriptor.
     * 
     * @param desc the Descriptor 
     */
    public V2HttpMarshaller(Descriptor desc) {
        super(desc);
    }

    /**
     * Reads a HTTP Model configuration.
     * 
     * @param config the configuration
     * @return the HTTP Binding Model 
     */
    @Override
    public Model read(Configuration config) {
        Descriptor desc = getDescriptor();
        String name = config.getName();
        if (name.startsWith(BindingModel.BINDING)) {
            return new V2HttpBindingModel(config, getDescriptor());
        } else if (name.equals(HttpName.ssl.name())) {
            return new V2SSLContextModel(config, getDescriptor());
        }
        return super.read(config);
    }
}
