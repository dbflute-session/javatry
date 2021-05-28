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
package org.docksidestage.javatry.debug;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.docksidestage.bizfw.debug.Language;
import org.docksidestage.bizfw.debug.LanguagePool;
import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.WordPool;
import org.docksidestage.unit.PlainTestCase;

/**
 * Some of the tests are failing as a consequence of some bug.
 * Debug, fix the bug caused by main code, make it green and save the world!
 * (テストの一部がメインコードのバグ原因で落ちています。デバグして、バグを直して、テストを通るようにしてください。)
 * @author zaya
 * @author jflute
 */
public class Step21WordPoolTest extends PlainTestCase {

    public void test_getWords() {
        // ## Arrange ##
        WordPool pool = new WordPool();

        // ## Act ##
        List<Word> result = pool.getWords();

        // ## Assert ##
        assertEquals("日本語", result.get(0).getLanguage().name);
        assertEquals("私", result.get(0).getWord());
        assertEquals("日本語", result.get(1).getLanguage().name);
        assertEquals("柿", result.get(1).getWord());
        assertEquals("日本語", result.get(2).getLanguage().name);
        assertEquals("荼", result.get(2).getWord());
        assertEquals("日本語", result.get(3).getLanguage().name);
        assertEquals("昴", result.get(3).getWord());
    }

    public void test_find() {
        // ## Arrange ##
        WordPool pool = new WordPool();

        // ## Act ##
        Word found = pool.find(2L);

        // ## Assert ##
        assertEquals("日本語", found.getLanguage().name);
        assertEquals("柿", found.getWord());
    }

    public void test_create() {
        // ## Arrange ##
        WordPool pool = new WordPool();

        // ## Act ##
        Map.Entry<Long, Word> actual = pool.create(new Language("日本語"), "財布");

        // ## Assert ##
        assertTrue(pool.getWords().contains(actual.getValue()));
    }

    public void test_findId() {
        // ## Arrange ##
        WordPool pool = new WordPool();
        List<String> words = Arrays.asList("私", "柿", "荼", "昂");

        for (int i = 0; i < words.size(); i++) {
            // ## Act ##
            Long actual = pool.findId(words.get(i));

            // ## Assert ##
            assertEquals(i + 1, actual.intValue());
        }

    }

    public void test_update() {
        // ## Arrange ##
        WordPool pool = new WordPool();
        Map.Entry<Long, Word> created = pool.create(new Language("日本語"), "つくえ");

        // ## Act ##
        Word result = pool.update("日本語", "つくえ", "ぼうし");

        // ## Assert ##
        assertEquals("ぼうし", result.getWord());
        assertEquals("日本語", result.getLanguage().name);
        assertEquals(created.getKey(), pool.findId("ぼうし"));
    }

    public void test_replace() {
        // ## Arrange ##
        WordPool pool = new WordPool();
        Map.Entry<Long, Word> created = pool.create(new Language("日本語"), "つくえ");

        // ## Act ##
        String result = pool.replace(created.getKey(), "くえ", "ばき");

        // ## Assert ##s
        Word actual = pool.find(result);
        assertEquals("つばき", actual.getWord());
        assertEquals("日本語", actual.getLanguage().name);
        assertEquals(created.getKey(), pool.findId("つばき"));
    }

    public void test_getLanguages() {
        // ## Arrange ##
        WordPool pool = new WordPool();
        Language expected = new LanguagePool().getLanguage("Japanese");

        // ## Act ##
        List<Language> languages = pool.getLanguages();

        // ## Assert ##
        assertEquals(expected.name, languages.get(0).name);
        assertEquals(expected.description, languages.get(0).description);
        assertEquals(expected.countries, languages.get(0).countries);
        assertEquals(expected.rank, languages.get(0).rank);
    }
}
