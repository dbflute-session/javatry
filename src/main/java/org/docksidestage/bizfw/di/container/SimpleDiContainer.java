/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.di.container;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.di.container.component.DiContainerModule;
import org.docksidestage.bizfw.di.container.component.SimpleInject;
import org.docksidestage.bizfw.exception.ExceptionMessageBuilder;

/**
 * @author jflute
 */
public class SimpleDiContainer extends Dog {

    // ===================================================================================
    //                                                                Singleton Definition
    //                                                                ====================
    public static final SimpleDiContainer instance = new SimpleDiContainer();

    public static SimpleDiContainer getInstance() {
        return instance;
    }

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Map<Class<?>, Object> componentMap = new LinkedHashMap<>(); // non-thread-safe, yeah!

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    private SimpleDiContainer() { // closet for singleton
    }

    // ===================================================================================
    //                                                                          Initialize
    //                                                                          ==========
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // How to initializa DI components:
    //
    // 1. register your modules: registerModule()
    // 2. resolve dependencies finally: resolveDependency()
    // _/_/_/_/_/_/_/_/_/_/
    public void registerModule(DiContainerModule module) {
        module.bind(componentMap);
    }

    public void resolveDependency() {
        componentMap.values().forEach(component -> {
            injectDependency(componentMap, component);
        });
    }

    protected void injectDependency(Map<Class<?>, Object> componentMap, Object baseObj) { // very simple implementation
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // annotation injection only supported for now by jflute(2019/10/16)
        // _/_/_/_/_/_/_/_/_/_/
        Field[] declaredFields = baseObj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            SimpleInject inject = field.getAnnotation(SimpleInject.class);
            if (inject == null) { // no annotation
                continue;
            }

            // always overwrite because of flexible initialization
            //try {
            //    if (field.get(baseObj) != null) { // already injected
            //        continue;
            //    }
            //} catch (IllegalArgumentException | IllegalAccessException e) {
            //    String msg = "Failed to get the component to the field: " + field;
            //    throw new IllegalStateException(msg, e);
            //}

            // has annotation here
            Class<?> fieldType = field.getType(); // e.g. Animal.class
            Object component = componentMap.get(fieldType);
            field.setAccessible(true); // ignore private scope
            try {
                field.set(baseObj, component); // e.g. action.animal = component;
            } catch (IllegalArgumentException | IllegalAccessException e) {
                String msg = "Failed to inject the component to the field: " + field + ", " + component;
                throw new IllegalStateException(msg, e);
            }
        }
    }

    // ===================================================================================
    //                                                                  Component Handling
    //                                                                  ==================
    public Object getComponent(Class<?> componentType) { // not null, no generics to be simple
        Object component = componentMap.get(componentType);
        if (component == null) {
            throwSimpleDiComponentNotFoundException();
        }
        return component;
    }

    protected void throwSimpleDiComponentNotFoundException() {
        ExceptionMessageBuilder br = new ExceptionMessageBuilder();
        br.addNotice("Not found the component by the type in DI container.");
        br.addItem("Advice");
        br.addElement("Make sure your component is registered in DI container");
        br.addElement("For example, you need to initialize modules for your components like this:");
        br.addElement("  (x):");
        br.addElement("    // if no initialization");
        br.addElement("    SimpleDiContainer container = SimpleDiContainer.getInstance();");
        br.addElement("    Object yourObj = container.getComponent(YourObject.class); // *Bad");
        br.addElement("  (o):");
        br.addElement("    // basically only once (should be implemented at e.g. application boot)");
        br.addElement("    SimpleDiContainer container = SimpleDiContainer.getInstance();");
        br.addElement("    container.registerModule(new YourModule()); // Good: contains your components");
        br.addElement("    container.resolveDependency(); // Good: don't forget for dependencies");
        br.addElement("    ...");
        br.addElement("    // code for user of DI container (e.g. Web framework)");
        br.addElement("    Object yourObj = container.getComponent(YourObject.class);");
        br.addItem("Registered Component");
        if (componentMap.isEmpty()) {
            br.addElement("*empty");
        } else { // has existing components
            componentMap.keySet().forEach(type -> {
                br.addElement("o " + type);
            });
        }
        String msg = br.buildExceptionMessage();
        throw new SimpleDiComponentNotFoundException(msg);
    }

    public static class SimpleDiComponentNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public SimpleDiComponentNotFoundException(String msg) {
            super(msg);
        }
    }
}
