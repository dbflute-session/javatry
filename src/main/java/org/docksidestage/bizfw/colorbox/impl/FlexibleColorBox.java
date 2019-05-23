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
package org.docksidestage.bizfw.colorbox.impl;

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.bizfw.colorbox.AbstractColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;

/**
 * @author jflute
 */
public class FlexibleColorBox extends AbstractColorBox {

    private final CompactColorBox compactColorBox; // not null

    public FlexibleColorBox(BoxColor color, CompactColorBox compactColorBox) {
        super(color);
        this.compactColorBox = compactColorBox;
    }

    @Override
    public List<BoxSpace> getSpaceList() {
        List<BoxSpace> mergedList = new ArrayList<>();
        mergedList.addAll(super.getSpaceList());
        mergedList.addAll(compactColorBox.getSpaceList());
        return mergedList;
    }

    @Override
    public void addSpace(BoxSpace boxSpace) { // to public
        super.addSpace(boxSpace);
    }
}
