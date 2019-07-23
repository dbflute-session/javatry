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

import java.util.LinkedHashMap;
import java.util.Map;

import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.di.container.component.DiContainerModule;

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
    //                                                                  Component Handling
    //                                                                  ==================
    public void register(DiContainerModule module) {
        module.bind(componentMap);
    }

    public Object getComponent(Class<?> componentType) { // no generics to be simple
        Object component = componentMap.get(componentType);
        if (component == null) {
            throw new IllegalStateException("Not found the component by the type: " + componentType);
        }
        return component;
    }
}
