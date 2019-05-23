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
public class BoxSpace {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final BoxSize size;
    private Object content; // null allowed

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BoxSpace(BoxSize size) {
        this.size = size;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    public String toString() {
        return content != null ? content.toString() : "*null";
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public BoxSize getSize() {
        return size;
    }

    /**
     * @return The contents in color box. (NullAllowed: because of not required)
     */
    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
