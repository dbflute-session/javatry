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

import org.docksidestage.bizfw.colorbox.AbstractColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.size.BoxSize;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;

/**
 * @author jflute
 */
public class StandardColorBox extends AbstractColorBox {

    public StandardColorBox(BoxColor color, BoxSize spaceSize) {
        super(color);
        addSpace(createSpace(spaceSize));
        addSpace(createSpace(spaceSize));
        addSpace(createSpace(spaceSize));
    }

    protected BoxSpace createSpace(BoxSize spaceSize) {
        return new BoxSpace(spaceSize);
    }

    public BoxSpace getUpperSpace() {
        return getSpaceList().get(0);
    }

    public BoxSpace getMiddleSpace() {
        return getSpaceList().get(1);
    }

    public BoxSpace getLowerSpace() {
        return getSpaceList().get(2);
    }
}
