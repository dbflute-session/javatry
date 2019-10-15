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

import java.util.List;

import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.size.BoxSize;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;

/**
 * The interface of color-box. <br>
 * Color-box is major furniture in Japan, which is like three-shelf bookcase.
 * <pre>
 *  +-----+
 *  |     | // e.g. books
 *  +-----+
 *  |     | // e.g. CDs 
 *  +-----+
 *  |     | // e.g. game machine
 *  +-----+
 * </pre>
 * @author jflute
 */
public interface ColorBox {

    /**
     * @return The object of color-box color, which has color information. (NotNull)
     */
    BoxColor getColor();

    /**
     * @return The object of color-box size, which has size information. (NotNull)
     */
    BoxSize getSize();

    /**
     * @return The list of space, which has content of color-box, and ordered from upper. (NotNull, NotEmpty)
     */
    List<BoxSpace> getSpaceList();
}
