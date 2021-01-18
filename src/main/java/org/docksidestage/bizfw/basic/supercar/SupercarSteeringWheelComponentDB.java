/*
 * Copyright 2019-2021 the original author or authors.
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
package org.docksidestage.bizfw.basic.supercar;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jflute
 */
public class SupercarSteeringWheelComponentDB {

    private final Map<Integer, String> clincherSpecMap; // not null

    public SupercarSteeringWheelComponentDB() {
        clincherSpecMap = new HashMap<>();
        clincherSpecMap.put(1, new String(new byte[] { 0x2d, 0x5b, 0x40, 0x5f, 0x40, 0x5d, 0x2d }));
        clincherSpecMap.put(2, new String(new byte[] { 0x2f, 0x28, 0x3e, 0x5f, 0x3c, 0x29, 0x5c }));
        clincherSpecMap.put(3, new String(new byte[] { 0x5c, 0x28, 0x5e, 0x5f, 0x5e, 0x29, 0x2f }));
    }

    public String findClincherSpecText(Integer clincherSpecId) {
        return clincherSpecMap.get(clincherSpecId);
    }

    public Map<Integer, String> getClincherSpecMap() { // read-only
        return Collections.unmodifiableMap(clincherSpecMap);
    }
}
