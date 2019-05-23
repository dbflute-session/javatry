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
package org.docksidestage.bizfw.colorbox.size;

/**
 * @author jflute
 */
public class BoxSize {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int height;
    private final int width;
    private final int depth;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BoxSize(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    public String toString() {
        return "{" + height + ", " + width + ", " + depth + "}";
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }
}
