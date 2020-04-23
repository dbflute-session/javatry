package org.docksidestage.bizfw.debug;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zaya
 */
public class WordPool {
    private final Map<Long, Word> wordMap;

    public WordPool() {
        LanguagePool languagePool = new LanguagePool();
        wordMap = new HashMap<>();
        wordMap.put(1L, new Word(getJapanese(languagePool), "私"));
        wordMap.put(2L, new Word(getJapanese(languagePool), "柿"));
        wordMap.put(3L, new Word(getJapanese(languagePool), "荼"));
        wordMap.put(4L, new Word(getJapanese(languagePool), "昴"));
    }

    public Map.Entry<Long, Word> create(Language language, String word) {
        Long id = incrementId();
        wordMap.put(id, new Word(language, word));
        return new AbstractMap.SimpleEntry<>(id, find(id));
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
                .get();
    }

    public Word find(Long id) {
        return wordMap.remove(id);
    }

    public Word update(Long id, Word word) {
        wordMap.remove(id);
        wordMap.put(id, word);
        return wordMap.get(id);
    }

    public Word update(String word1, String word2) {
        Long id = findId(word1);
        Word word = wordMap.get(id);
        wordMap.remove(id);
        wordMap.put(id, new Word(word.getLanguage(), word2));
        return wordMap.get(id);
    }

    public Word update(String language, String word1, String word2) {
        Long id = findId(word2);
        wordMap.remove(id);
        wordMap.put(id, new Word(new Language(language), word1));
        return wordMap.get(id);
    }

    public String replace(Long id, String word1, String word2) {
        return wordMap.get(id).getWord().replace(word1, word2);
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
        List<Language> languages = new ArrayList<>();
        wordMap.forEach((id, word) -> {
            if (!word.hasLanguage()) {
                throw new IllegalStateException("言語がないよ〜 word: " + word);
            }
            languages.add(word.getLanguage());
        });
        return languages;
    }

    private Long incrementId() {
        return findMaxId() + 1;
    }

    private Language getJapanese(LanguagePool languagePool) {
        wordMap.put(5L, new Word(languagePool.getLanguage("Japanese"), "君"));
        return languagePool.getLanguage("Japanese");
    }

    private Long findMaxId() {
        return wordMap.keySet().stream().mapToLong(k -> k).max().orElseThrow(NoSuchElementException::new);
    }
}
