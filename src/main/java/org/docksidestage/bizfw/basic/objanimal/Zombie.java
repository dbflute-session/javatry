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
    //                                                                           Attribute
    //                                                                           =========
    protected boolean zombieMagicPowerSuppressed;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Zombie() {
    }

    public Zombie suppressZombieMagicPower() { // e.g. new Zombie().suppressZombieMagicPower()
        zombieMagicPowerSuppressed = true;
        return this;
    }

    @Override
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected void breatheIn() {
        if (isZombieMagicPowerEnabled()) { // zombie needs magic power to breathe in
            super.breatheIn();
        } else {
            throw new IllegalStateException("Cannot breathe in because of magic power suppressed.");
        }
    }

    protected boolean isZombieMagicPowerEnabled() {
        return !zombieMagicPowerSuppressed;
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
