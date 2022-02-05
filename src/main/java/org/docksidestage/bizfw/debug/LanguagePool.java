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

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zaya
 * @author jflute
 */
public class LanguagePool {

    public Map<String, Language> languages;

    public LanguagePool() {
        languages = new HashMap<>();
        languages.put("Japanese", new Language("日本語", 1, 13));
        languages.put("English", new Language("English", "", 58));
        languages.put("Spanish", new Language("Español", 20, 4));
        languages.put("Hindi", new Language("हिन्दी", "", 3, 3));
        languages.put("Polish", new Language("Polskie", "", 1, 38));
        languages.put("Greek", new Language("Ελληνικά", 2, 87));
    }

    public List<Language> getLanguages() {
        return (List<Language>) languages.values();
    }

    public Language getLanguage(String languageName) {
        return languages.get(languageName);
    }

    public List<Language> sort() {
        return languages.values().stream().sorted(Comparator.comparingInt(Language::getRank)).collect(Collectors.toList());
    }

    public List<Language> sortByCountries() {
        return languages.values().stream().sorted(Comparator.comparingInt(Language::getCountries)).collect(Collectors.toList());
    }

    public List<Language> sortByName() {
        return languages.values().stream().sorted(Comparator.comparing(Language::getName)).collect(Collectors.toList());
    }
}
