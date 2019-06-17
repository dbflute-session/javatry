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

/**
 * The object for zombie(ゾンビ).
 * @author jflute
 */
public class Zombie extends Animal {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Zombie() {
    }

    @Override
    protected int getInitialHitPoint() {
        return -1; // infinity hit point
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    public BarkedSound bark() { // can do it directly
        return new BarkedSound(getBarkWord());
    }

    @Override
    protected String getBarkWord() {
        return "uooo"; // what in English?
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    protected void downHitPoint() {
        // do nothing, infinity hit point
    }
}
