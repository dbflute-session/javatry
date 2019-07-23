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

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.supercar.SupercarDealer;
import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer.Supercar;
import org.docksidestage.bizfw.di.nondi.factory.NonDiAnimalFactory;
import org.docksidestage.bizfw.di.nondi.factory.NonDiSupercarFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class NonDiIndividualFactoryAction {

    private static final Logger logger = LoggerFactory.getLogger(NonDiDirectSecondAction.class);

    // ===================================================================================
    //                                                                         Dog Process
    //                                                                         ===========
    public void callFriend() { // no change
        Animal animal = createAnimal();
        animal.bark();
    }

    public void wakeupMe() { // no change
        Animal animal = createAnimal();
        for (int i = 0; i < 3; i++) {
            animal.bark();
        }
    }

    private Animal createAnimal() {
        return new NonDiAnimalFactory().createAnimal(); // has many process to bark
    }

    // ===================================================================================
    //                                                                    Supercar Process
    //                                                                    ================
    public void goToOffice() { // no change
        SupercarDealer dealer = createSupercarDealer();
        Supercar supercar = dealer.orderSupercar("I think...steering wheel is like sea");
        logger.debug("Go to office by {}", supercar);
    }

    public void sendGift() { // no change
        SupercarDealer dealer = createSupercarDealer();
        Supercar supercar = dealer.orderSupercar("I think...steering wheel is useful on land");
        logger.debug("Send {} to my friend", supercar);
    }

    private SupercarDealer createSupercarDealer() {
        return new NonDiSupercarFactory().createSupercarDealer(); // has exntesion in nested class
    }
}
