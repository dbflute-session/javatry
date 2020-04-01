package org.docksidestage.bizfw.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordPool {
    private List<Word> words;

    public WordPool() {
        LanguagePool languagePool = new LanguagePool();
        words = new ArrayList<>();
        words.add(new Word(languagePool.getLanguage("日本語"), "私"));
        words.add(new Word(languagePool.getLanguage("日本語"), "こんにちは"));
        words.add(new Word(languagePool.getLanguage("日本語"), "食べる"));
    }

    public List<Word> getWords() {
        return words;
    }

    public List<String> getWordsOnly() {
        return words.stream().map(word -> word.getWord()).collect(Collectors.toList());
    }

    public List<Language> getLanguages() {
        return words.stream().map(word -> word.getLanguage()).collect(Collectors.toList());
    }
}
