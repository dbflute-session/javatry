package org.docksidestage.bizfw.debug;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zaya
 */
public class LanguagePool {

    public Map<String, Language> languages;

    public LanguagePool() {
        languages = new HashMap<>();
        languages.put("日本語", new Language("日本語"));
        languages.put("English", new Language("English"));
    }

    public List<Language> getLanguages() {
        return (List<Language>) languages.values();
    }

    public Language getLanguage(String languageName) {
        return languages.get(languageName);
    }
}
