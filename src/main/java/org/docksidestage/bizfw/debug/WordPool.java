package org.docksidestage.bizfw.debug;

import java.util.*;
import java.util.stream.Collectors;

import javafx.util.Pair;

public class WordPool {
    private Map<Long, Word> wordMap;

    public WordPool() {
        LanguagePool languagePool = new LanguagePool();
        wordMap = new HashMap<>();
        wordMap.put(1L, new Word(languagePool.getLanguage("日本語"), "私"));
        wordMap.put(2L, new Word(languagePool.getLanguage("日本語"), "こんにちは"));
        wordMap.put(3L, new Word(languagePool.getLanguage("日本語"), "食べる"));
    }

    public Pair<Long, Word> create(Language language, String word) {
        Long id = incrementId();
        wordMap.put(id, new Word(language, word));
        return new Pair<>(id, wordMap.get(id));
    }

    public Word find(Long id) {
        return wordMap.get(id);
    }

    public Word find(String word) {
        return wordMap.values().stream().filter(v -> v.getWord().equals(word)).findFirst().orElseThrow(NoSuchElementException::new);
    }

    public Long findId(String word) {
        return wordMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getWord().equals(word))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public Word update(Long id, Word word) {
        wordMap.remove(id);
        wordMap.put(id, word);
        return wordMap.get(id);
    }

    public Word update(String language, String current, String update) {
        Long id = findId(current);
        wordMap.remove(id);
        wordMap.put(id, new Word(new Language(language), update));
        return wordMap.get(id);
    }

    public Word update(String current, String update) {
        Long id = findId(current);
        Word word = wordMap.get(id);
        wordMap.remove(id);
        wordMap.put(id, new Word(word.getLanguage(), update));
        return wordMap.get(id);
    }

    public Word replace(Long id, String beReplaced, String toBe) {
        String replace = wordMap.get(id).getWord().replace(beReplaced, toBe);
        update(wordMap.get(id).getWord(), replace);
        return wordMap.get(id);
    }

    public void delete(Long id) {
        wordMap.remove(id);
    }

    public List<Word> getWords() {
        return new ArrayList<>(wordMap.values());
    }

    public List<String> getWordsOnly() {
        return wordMap.values().stream().map(Word::getWord).collect(Collectors.toList());
    }

    public List<Language> getLanguages() {
        return wordMap.values().stream().map(Word::getLanguage).collect(Collectors.toList());
    }

    private Long incrementId() {
        return findMaxId() + 1;
    }

    private Long findMaxId() {
        return wordMap.keySet().stream().mapToLong(k -> k).max().orElseThrow(NoSuchElementException::new);
    }
}
