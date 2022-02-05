/*
 * Copyright 2019-2022 the original author or authors.
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
package org.docksidestage.bizfw.basic.screw;

import org.docksidestage.bizfw.basic.screw.exception.ScrewCannotMakeBySpecException;

/**
 * The manufacturer(製造業者) of special screw(特別なねじ).
 * @author jflute
 */
public class SpecialScrewManufacturer {

    public SpecialScrew makeSpecialScrew(ScrewSpec screwSpec) {
        if (isKawaiiFaceScrewSpec(screwSpec)) {
            String msg = "The kawaii face is already unsupported so we cannot make it.";
            throw new ScrewCannotMakeBySpecException(msg);
        }
        return new SpecialScrew(screwSpec.getSpecText());
    }

    private boolean isKawaiiFaceScrewSpec(ScrewSpec screwSpec) {
        String specText = screwSpec.getSpecText();
        String kawaiiText = new String(new byte[] { 0x5c, 0x28, 0x5e, 0x5f, 0x5e, 0x29, 0x2f });
        return specText.equals(kawaiiText);
    }
}
