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
package org.docksidestage.bizfw.basic.screw;

/**
 * The manufacturer(製造業者) of special screw(特別なねじ).
 * @author jflute
 */
public class SpecialScrewManufacturer {

    public SpecialScrew makeSpecialScrew(ScrewSpec screwSpec) {
        String specText = screwSpec.getSpecText();
        if (specText.equals("\\(^_^)/")) { // too pinpoint!?
            String msg = "The kawaii face is not useful to make screw: " + screwSpec;
            throw new SpecialScrewCannotMakeBySpecException(msg);
        }
        return new SpecialScrew(specText);
    }

    public static class ScrewSpec {

        private final String specText;

        public ScrewSpec(String specText) {
            this.specText = specText;
        }

        public String getSpecText() {
            return specText;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + ":{" + specText + "}";
        }
    }

    public static class SpecialScrew {

        public SpecialScrew(String specText) {
            // dummy
        }
    }

    public static class SpecialScrewCannotMakeBySpecException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public SpecialScrewCannotMakeBySpecException(String msg) {
            super(msg);
        }
    }
}
