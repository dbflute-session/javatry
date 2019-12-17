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
package org.docksidestage.bizfw.di.nondi;

import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.basic.supercar.SupercarDealer;
import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer;
import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer.Supercar;
import org.docksidestage.bizfw.di.cast.TooLazyDog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class NonDiDirectSecondAction {

    private static final Logger logger = LoggerFactory.getLogger(NonDiDirectSecondAction.class);

    // ===================================================================================
    //                                                                         Dog Process
    //                                                                         ===========
    public void callFriend() {
        // has many process to bark
        TooLazyDog dog = new TooLazyDog("tofu");
        dog.petMe();
        dog.playWith(new Cat());
        dog.bark();
    }

    public void wakeupMe() {
        // also needs same process here
        TooLazyDog dog = new TooLazyDog("tofu");
        dog.petMe();
        dog.playWith(new Cat());
        for (int i = 0; i < 3; i++) {
            dog.bark();
        }
    }

    // ===================================================================================
    //                                                                    Supercar Process
    //                                                                    ================
    public void goToOffice() {
        // has extension in nested class
        SupercarDealer dealer = new SupercarDealer() {
            @Override
            protected SupercarManufacturer createSupercarManufacturer() {
                return new SupercarManufacturer() {
                    @Override
                    public Supercar makeSupercar(String catalogKey) {
                        logger.info("...Making supercar by {}", catalogKey);
                        return super.makeSupercar(catalogKey);
                    }
                };
            }
        };
        Supercar supercar = dealer.orderSupercar("I think...steering wheel is like sea");
        logger.debug("Go to office by {}", supercar);
    }

    public void sendGift() {
        // also needs same extension here
        SupercarDealer dealer = new SupercarDealer() {
            @Override
            protected SupercarManufacturer createSupercarManufacturer() {
                return new SupercarManufacturer() {
                    @Override
                    public Supercar makeSupercar(String catalogKey) {
                        logger.info("...Making supercar by {}", catalogKey);
                        return super.makeSupercar(catalogKey);
                    }
                };
            }
        };
        Supercar supercar = dealer.orderSupercar("I think...steering wheel is useful on land");
        logger.debug("Send {} to my friend", supercar);
    }
}
