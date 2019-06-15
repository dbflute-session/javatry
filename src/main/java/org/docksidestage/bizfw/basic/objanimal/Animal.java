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
 * @author jflute
 */
public abstract class Animal {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
        hitPoint = getInitialHitPoint();
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        prepareAbdominalMuscle();
        breatheIn();
        String barkWord = getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        downHitPoint();
        return barkedSound;
    }

    protected void prepareAbdominalMuscle() {
        // dummy
    }

    protected void breatheIn() {
        // dummy
    }

    protected abstract String getBarkWord();

    protected BarkedSound doBark(String barkWord) {
        return new BarkedSound(barkWord);
    }

    protected void downHitPoint() {
        --hitPoint;
    }
}
