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

import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer.Supercar;

/**
 * The dealer(販売業者) of supercar.
 * @author jflute
 */
public class SupercarDealer {

    public Supercar orderSupercar(String clientRequirement) {
        SupercarManufacturer manufacturer = createSupercarManufacturer();
        if (clientRequirement.contains("steering wheel is like sea")) {
            return manufacturer.makeSupercar("piari");
        } else if (clientRequirement.contains("steering wheel is useful on land")) {
            return manufacturer.makeSupercar("land");
        } else if (clientRequirement.contains("steering wheel has many shop")) {
            return manufacturer.makeSupercar("piari");
        } else {
            throw new IllegalStateException("Cannot understand the client requirement: " + clientRequirement);
        }
    }

    protected SupercarManufacturer createSupercarManufacturer() {
        return new SupercarManufacturer();
    }
}
