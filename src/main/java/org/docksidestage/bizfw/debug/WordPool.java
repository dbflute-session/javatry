/*
 * Copyright 2019-2021 the original author or authors.
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

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author zaya
 * @author jflute
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
        return wordMap.values()
                .stream()
                .filter(wordObj -> wordObj.getWord().equals(word))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public Long findId(String word) {
        return wordMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getWord().equals(word))
                .map(entry -> entry.getKey())
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

    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    //  * 指定された既存のwordを、指定された言語で新しいwordに更新する。<br>
    //  * そのwordが存在しなかったときの配慮は何もしてない。
    //  * @param language 新しいwordの言語 (NotNull)
    //  * @param word1 指定された言語の新しいword, word mapに保存される (NotNull)
    //  * @param word2 更新対象のwordを探すために使われる既存のword (NotNull)
    //  * @return 更新されたwordオブジェクト、更新後にword mapから引き出されたもの (NotNull)
    //  */
    /**
     * Update the specified existing word to the new word as the specified language. <br>
     * No consideration here when the word is not found.
     * @param language The language for new word. (NotNull)
     * @param word1 The new word for the specified language, which is saved in word map. (NotNull)
     * @param word2 The existing word to search update target word. (NotNull)
     * @return The updated word object which is retrieved from word map after update. (NotNull)
     */
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
