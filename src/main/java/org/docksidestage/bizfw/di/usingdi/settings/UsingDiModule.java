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

import java.util.Map;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.basic.supercar.SupercarDealer;
import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer;
import org.docksidestage.bizfw.di.cast.TooLazyDog;
import org.docksidestage.bizfw.di.container.component.DiContainerModule;
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
        doBindAccessorAction(componentMap); // should be after basic cast
        doBindAnnotationAction(componentMap);
        doBindDelegatingLogic(componentMap);
        doBindDelegatingAction(componentMap);
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
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // auto injection by accessor is unsupported in simple DI container of javatry
    // so you need to inject them manually at your DI settings
    // (generally it depends on DI container specification)
    // _/_/_/_/_/_/_/_/_/_/
    private void doBindAccessorAction(Map<Class<?>, Object> componentMap) {
        UsingDiAccessorAction action = new UsingDiAccessorAction();
        action.setAnimal((Animal) componentMap.get(Animal.class));
        action.setSupercarDealer((SupercarDealer) componentMap.get(SupercarDealer.class));
        componentMap.put(UsingDiAccessorAction.class, action);
    }

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // auto injection by annotation is supported in simple DI container of javatry
    // so you don't need to inject them here
    // (generally it depends on DI container specification)
    // _/_/_/_/_/_/_/_/_/_/
    private void doBindAnnotationAction(Map<Class<?>, Object> componentMap) {
        componentMap.put(UsingDiAnnotationAction.class, new UsingDiAnnotationAction());
    }

    private void doBindDelegatingAction(Map<Class<?>, Object> componentMap) {
        componentMap.put(UsingDiDelegatingAction.class, new UsingDiDelegatingAction());
    }

    private void doBindDelegatingLogic(Map<Class<?>, Object> componentMap) {
        componentMap.put(UsingDiDelegatingLogic.class, new UsingDiDelegatingLogic());
    }
}
