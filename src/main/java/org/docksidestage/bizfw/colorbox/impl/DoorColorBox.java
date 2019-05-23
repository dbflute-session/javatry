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

import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.size.BoxSize;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.space.DoorBoxSpace;

/**
 * @author jflute
 */
public class DoorColorBox extends StandardColorBox { // however door function should be plug-in?

    public DoorColorBox(BoxColor color, BoxSize spaceSize) {
        super(color, spaceSize);
    }

    @Override
    protected BoxSpace createSpace(BoxSize spaceSize) {
        return new DoorBoxSpace(spaceSize);
    }

    public List<DoorBoxSpace> getDoorSpaceList() { // cast method
        List<DoorBoxSpace> resultList = new ArrayList<DoorBoxSpace>();
        for (BoxSpace boxSpace : getSpaceList()) {
            resultList.add((DoorBoxSpace) boxSpace);
        }
        return resultList;
    }
}
