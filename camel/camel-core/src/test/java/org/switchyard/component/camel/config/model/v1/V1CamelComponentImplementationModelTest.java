/*
 * JBoss, Home of Professional Open Source Copyright 2009, Red Hat Middleware
 * LLC, and individual contributors by the @authors tag. See the copyright.txt
 * in the distribution for a full listing of individual contributors.
 * 
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */
package org.switchyard.component.camel.config.model.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.switchyard.component.camel.config.model.CamelComponentImplementationModel;
import org.switchyard.component.camel.config.model.SingleRouteService;
import org.switchyard.config.model.ModelPuller;
import org.switchyard.config.model.composite.ComponentModel;
import org.switchyard.config.model.switchyard.SwitchYardModel;

/**
 * Test for {@link V1CamelBindingModel}.
 * 
 * @author Daniel Bevenius
 */
public class V1CamelComponentImplementationModelTest {
    
    private static boolean oldIgnoreWhitespace;

    @BeforeClass
    public static void setup() {
        oldIgnoreWhitespace = XMLUnit.getIgnoreWhitespace();
        XMLUnit.setIgnoreWhitespace(true);
    }
    
    @AfterClass
    public static void cleanup() {
        XMLUnit.setIgnoreWhitespace(oldIgnoreWhitespace);
    }
    
    @Test
    public void programmaticConfig() {
        assertThat(createModel().getJavaClass(), is(equalTo(SingleRouteService.class.getName())));
    }
    
    @Test
    public void validateModelWithRouteElement() throws Exception {
        final V1CamelImplementationModel implModel = getCamelImplementation("switchyard-implementation-beans.xml");
        
        validateModel(implModel);
        assertThat(implModel.getRoute(), is(notNullValue()));
    }
    
    @Test
    public void validateModelWithJavaElement() throws Exception {
        final V1CamelImplementationModel implModel = getCamelImplementation("switchyard-implementation-java.xml");
        
        validateModel(implModel);
        assertThat(SingleRouteService.class.getName(), is(equalTo(implModel.getJavaClass())));
    }
    
    @Test
    public void writeConfig() throws Exception {
        final String control = getCamelImplementation("switchyard-implementation-java.xml").toString();
        final String test = createModel().toString();
        XMLAssert.assertXMLEqual(control, test);
    }
    
    private V1CamelImplementationModel createModel() {
        return new V1CamelImplementationModel().setJavaClass(SingleRouteService.class.getName());
    }
    
    private void validateModel(final CamelComponentImplementationModel model) {
        assertThat(model.validateModel().isValid(), is(true));
    }
    
    private V1CamelImplementationModel getCamelImplementation(final String config) throws Exception {
        V1CamelImplementationModel implementation = null;
        final SwitchYardModel model = new ModelPuller<SwitchYardModel>().pull(config, getClass());
        for (ComponentModel componentModel : model.getComposite().getComponents()) {
            if (CamelComponentImplementationModel.CAMEL.equals(componentModel.getImplementation().getType())) {
                implementation = (V1CamelImplementationModel) componentModel.getImplementation();
                break;
            }
        }
        return implementation;
    }
}
