package org.docksidestage.bizfw.debug;

/**
 * @author zaya
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
