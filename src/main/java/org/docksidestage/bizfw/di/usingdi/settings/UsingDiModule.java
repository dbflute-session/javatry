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
package org.docksidestage.bizfw.di.usingdi.settings;

import java.lang.reflect.Field;
import java.util.Map;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.basic.supercar.SupercarDealer;
import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer;
import org.docksidestage.bizfw.di.cast.TooLazyDog;
import org.docksidestage.bizfw.di.container.component.DiContainerModule;
import org.docksidestage.bizfw.di.container.component.SimpleInject;
import org.docksidestage.bizfw.di.usingdi.UsingDiAccessorAction;
import org.docksidestage.bizfw.di.usingdi.UsingDiAnnotationAction;
import org.docksidestage.bizfw.di.usingdi.UsingDiDelegatingAction;
import org.docksidestage.bizfw.di.usingdi.UsingDiDelegatingLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class UsingDiModule implements DiContainerModule {

    private static final Logger logger = LoggerFactory.getLogger(UsingDiModule.class);

    // ===================================================================================
    //                                                                               Bind
    //                                                                              ======
    @Override
    public void bind(Map<Class<?>, Object> componentMap) {
        // basic cast
        doBindAnimal(componentMap);
        doBindSupercarDealer(componentMap);

        // action
        doBindAccessorAction(componentMap);
        doBindAnnotationAction(componentMap);
        doBindDelegatingLogic(componentMap); // should be before caller 
        doBindDelegatingAction(componentMap); // needs already-initialized logic
    }

    // ===================================================================================
    //                                                                          Basic Cast
    //                                                                          ==========
    private void doBindAnimal(Map<Class<?>, Object> componentMap) {
        TooLazyDog dog = new TooLazyDog("tofu");
        dog.petMe();
        dog.playWith(new Cat());
        componentMap.put(Animal.class, dog);
    }

    private void doBindSupercarDealer(Map<Class<?>, Object> componentMap) {
        componentMap.put(SupercarDealer.class, new SupercarDealer() {
            @Override
            protected SupercarManufacturer createSupercarManufacturer() {
                return new SupercarManufacturer() {
                    @Override
                    public Supercar makeSupercar(String catalogKey) {
                        logger.info("...Making supercar by {}", catalogKey); // extension here
                        return super.makeSupercar(catalogKey);
                    }
                };
            }
        });
    }

    // ===================================================================================
    //                                                                              Action
    //                                                                              ======
    private void doBindAccessorAction(Map<Class<?>, Object> componentMap) {
        UsingDiAccessorAction action = new UsingDiAccessorAction();
        action.setAnimal((Animal) componentMap.get(Animal.class));
        action.setSupercarDealer((SupercarDealer) componentMap.get(SupercarDealer.class));
        componentMap.put(UsingDiAccessorAction.class, action);
    }

    private void doBindAnnotationAction(Map<Class<?>, Object> componentMap) {
        UsingDiAnnotationAction action = new UsingDiAnnotationAction();
        injectDependency(componentMap, action);
        componentMap.put(UsingDiAnnotationAction.class, action);
    }

    private void doBindDelegatingAction(Map<Class<?>, Object> componentMap) {
        UsingDiDelegatingAction action = new UsingDiDelegatingAction();
        injectDependency(componentMap, action);
        componentMap.put(UsingDiDelegatingAction.class, action);
    }

    private void doBindDelegatingLogic(Map<Class<?>, Object> componentMap) {
        UsingDiDelegatingLogic logic = new UsingDiDelegatingLogic();
        injectDependency(componentMap, logic);
        componentMap.put(UsingDiDelegatingLogic.class, logic);
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private void injectDependency(Map<Class<?>, Object> componentMap, Object baseObj) {
        Field[] declaredFields = baseObj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            SimpleInject inject = field.getAnnotation(SimpleInject.class);
            if (inject == null) { // no annotation
                continue;
            }
            // has annotation hereo
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
}
