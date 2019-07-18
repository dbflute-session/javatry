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

import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.basic.supercar.SupercarDealer;
import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer.Supercar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class NonDiDirectFirstAction {

    private static final Logger logger = LoggerFactory.getLogger(NonDiDirectFirstAction.class);

    // ===================================================================================
    //                                                                         Dog Process
    //                                                                         ===========
    public void callFriend() {
        Dog dog = new Dog();
        dog.bark();
    }

    public void wakeupMe() {
        Dog dog = new Dog();
        dog.bark();
    }

    // ===================================================================================
    //                                                                    Supercar Process
    //                                                                    ================
    public void goToOffice() {
        SupercarDealer dealer = new SupercarDealer();
        Supercar supercar = dealer.orderSupercar("I think...steering wheel is like sea");
        logger.debug("Go to office by {}", supercar);
    }

    public void sendGift() {
        SupercarDealer dealer = new SupercarDealer();
        Supercar supercar = dealer.orderSupercar("I think...steering wheel is like sea");
        logger.debug("Send {} to my friend", supercar);
    }
}
