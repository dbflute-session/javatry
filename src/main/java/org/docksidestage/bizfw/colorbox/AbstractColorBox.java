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
package org.docksidestage.bizfw.colorbox;

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.size.BoxSize;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;

/**
 * @author jflute
 */
public abstract class AbstractColorBox implements ColorBox {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final BoxColor color; // not null
    private final List<BoxSpace> spaceList = new ArrayList<BoxSpace>(); // not null
    private BoxSize size = new BoxSize(0, 0, 0); // not null, as default (auto-calculated when a space added)

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AbstractColorBox(BoxColor color) {
        this.color = color;
    }

    // ===================================================================================
    //                                                                      Space Handling
    //                                                                      ==============
    protected void addSpace(BoxSpace boxSpace) {
        spaceList.add(boxSpace);
        calculateWholeSize();
    }

    protected void calculateWholeSize() {
        int edge = BoxFixedSpec.EDGE_SIZE;
        int sumHeight = 0;
        int maxWidth = 0;
        int maxDepth = 0;
        for (BoxSpace space : spaceList) {
            BoxSize size = space.getSize();
            sumHeight = sumHeight + size.getHeight();
            int width = size.getWidth();
            if (width > maxWidth) {
                maxWidth = width;
            }
            int depth = size.getDepth();
            if (depth > maxDepth) {
                maxDepth = depth;
            }
        }
        // +---+
        // |   |
        // |---|
        // |   |
        // |---|
        // |   |
        // +---+
        int heightEdge = edge * (spaceList.size() + 1);
        int widthEdge = edge * 2;
        int depthEdge = edge;
        size = new BoxSize(sumHeight + heightEdge, maxWidth + widthEdge, maxDepth + depthEdge);
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    public String toString() {
        return "{" + color + ", " + size + ", spaces=" + spaceList.size() + "}";
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public BoxColor getColor() {
        return color;
    }

    public BoxSize getSize() {
        return size;
    }

    public List<BoxSpace> getSpaceList() { // read-only
        return spaceList;
    }
}
