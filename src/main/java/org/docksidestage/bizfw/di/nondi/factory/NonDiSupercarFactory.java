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
package org.docksidestage.bizfw.di.nondi.factory;

import org.docksidestage.bizfw.basic.supercar.SupercarDealer;
import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class NonDiSupercarFactory {

    private static final Logger logger = LoggerFactory.getLogger(NonDiSupercarFactory.class);

    public SupercarDealer createSupercarDealer() {
        return new SupercarDealer() {
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
        };
    }
}
