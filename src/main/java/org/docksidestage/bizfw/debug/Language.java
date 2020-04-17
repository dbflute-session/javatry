package org.docksidestage.bizfw.debug;

import java.util.Collections;
import java.util.List;

/**
 * @author zaya
 */
public class Language {
    public String name;
    public String description = "";
    public List<String> countries = Collections.emptyList();
    public Long rank;

    public Language(String name) {
        this.name = name;
    }

    public Language(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Language(String name, String description, List<String> countries) {
        this.name = name;
        this.description = description;
        this.countries = countries;
    }

    public Language(String name, String description, List<String> countries, Long rank) {
        this.name = name;
        this.description = description;
        this.countries = countries;
        this.rank = rank;
    }
}
