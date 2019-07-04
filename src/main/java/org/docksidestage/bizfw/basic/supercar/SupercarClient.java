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
package org.docksidestage.bizfw.basic.supercar;

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer.Supercar;

/**
 * The client(顧客) of supercar.
 * @author jflute
 */
public class SupercarClient {

    private final List<Supercar> myCarList = new ArrayList<>(4);

    public void buySupercar() {
        SupercarDealer dealer = createDealer();
        String clientRequirement = "steering wheel is like sea";
        Supercar orderedCustomCar = dealer.orderSupercar(clientRequirement);
        myCarList.add(orderedCustomCar);
    }

    protected SupercarDealer createDealer() {
        return new SupercarDealer();
    }
}
