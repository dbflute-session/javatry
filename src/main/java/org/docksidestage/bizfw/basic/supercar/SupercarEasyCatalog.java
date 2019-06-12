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
package org.docksidestage.bizfw.basic.supercar;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jflute
 */
public class SupercarEasyCatalog {

    private final Map<String, Integer> steeringWheelCatalogMap; // not null

    public SupercarEasyCatalog() {
        steeringWheelCatalogMap = new HashMap<>();
        steeringWheelCatalogMap.put("sea", 1); // is like sea
        steeringWheelCatalogMap.put("land", 2); // is on land
        steeringWheelCatalogMap.put("piari", 3); // has many shop
    }

    public Integer findSteeringWheelSpecId(String catalogKey) {
        return steeringWheelCatalogMap.get(catalogKey);
    }

    public Map<String, Integer> getSteeringWheelCatalogMap() { // read-only
        return Collections.unmodifiableMap(steeringWheelCatalogMap);
    }
}
