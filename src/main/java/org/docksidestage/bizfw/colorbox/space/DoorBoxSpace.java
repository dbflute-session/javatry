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
package org.docksidestage.bizfw.colorbox.space;

import org.docksidestage.bizfw.colorbox.size.BoxSize;

/**
 * @author jflute
 */
public class DoorBoxSpace extends BoxSpace {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int BROKEN_DAMAGE = 3;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private boolean open;
    private int damageCount;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public DoorBoxSpace(BoxSize size) {
        super(size);
    }

    // ===================================================================================
    //                                                                    Content Handling
    //                                                                    ================
    @Override
    public Object getContent() {
        if (isOpen()) {
            return super.getContent();
        } else {
            damageClosedAccess();
            return isBroken() ? super.getContent() : null;
        }
    }

    @Override
    public void setContent(Object content) {
        if (isOpen()) {
            super.setContent(content);
        } else {
            damageClosedAccess();
            if (isBroken()) {
                super.setContent(content);
            }
        }
    }

    private void damageClosedAccess() {
        if (!isBroken()) {
            ++damageCount;
        }
    }

    // ===================================================================================
    //                                                                         Open/Broken
    //                                                                         ===========
    public boolean isOpen() {
        return open;
    }

    public void openTheDoor() {
        open = true;
    }

    public void closeTheDoor() {
        open = false;
    }

    public boolean isBroken() {
        return damageCount >= BROKEN_DAMAGE;
    }
}
