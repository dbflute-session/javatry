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
package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for cat(猫).
 * @author jflute
 */
public class Cat extends Animal implements FastRunner {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Cat.class);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Cat() {
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected String getBarkWord() {
        return "nya-"; // mew? in English
    }

    // ===================================================================================
    //                                                                              Runner
    //                                                                              ======
    @Override
    public void run() {
        // dummy implementation
        logger.debug("...Running now");
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    protected void downHitPoint() {
        super.downHitPoint();
        if (hitPoint % 2 == 0) {
            super.downHitPoint();
        }
    }
}
