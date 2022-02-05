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
package org.docksidestage.bizfw.debug;

/**
 * @author zaya
 * @author jflute
 */
public class Language {

    public String name;
    public String description = "";
    public int countries = 0;
    public int rank;

    public Language(String name) {
        this.name = name;
    }

    public Language(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Language(String name, String description, int countries) {
        this.name = name;
        this.description = description;
        this.countries = countries;
    }

    public Language(String name, int countries, int rank) {
        this.name = name;
        this.rank = rank;
        this.countries = countries;
    }

    public Language(String name, String description, int countries, int rank) {
        this.name = name;
        this.description = description;
        this.countries = countries;
        this.rank = rank;
    }

    public String getDescription() {
        return description;
    }

    public void setCountries(int countries) {
        this.countries = countries;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountries() {
        return countries;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
