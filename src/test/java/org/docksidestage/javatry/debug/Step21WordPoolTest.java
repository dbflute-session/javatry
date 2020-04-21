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
 * Debug, fix the bug, make it green and save the world!
 * (テストの一部がバグ原因で落ちています。デバグして、バグを直して、テストを通るようにしてください。)
 * @author zaya
 */
public class Step21WordPoolTest extends PlainTestCase {

    public void test_getWords() {
        // arrange
        WordPool pool = new WordPool();

        // act
        List<Word> result = pool.getWords();

        // assert
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
        // arrange
        WordPool pool = new WordPool();

        // act
        Word found = pool.find(2L);

        // assert
        assertEquals("日本語", found.getLanguage().name);
        assertEquals("柿", found.getWord());
    }

    public void test_create() {
        // arrange
        WordPool pool = new WordPool();

        // act
        Map.Entry<Long, Word> actual = pool.create(new Language("日本語"), "財布");

        // assert
        assertTrue(pool.getWords().contains(actual.getValue()));
    }

    public void test_findId() {
        // arrange
        WordPool pool = new WordPool();
        List<String> words = Arrays.asList("私", "柿", "荼", "昂");

        for (int i = 0; i < words.size(); i++) {
            // act
            Long actual = pool.findId(words.get(i));

            // assert
            assertEquals(i + 1, actual.intValue());
        }

    }

    public void test_update() {
        // arrange
        WordPool pool = new WordPool();
        Map.Entry<Long, Word> created = pool.create(new Language("日本語"), "つくえ");

        // act
        Word result = pool.update("日本語", "つくえ", "ぼうし");

        // assert
        assertEquals("ぼうし", result.getWord());
        assertEquals("日本語", result.getLanguage().name);
        assertEquals(created.getKey(), pool.findId("ぼうし"));
    }

    public void test_replace() {
        // arrange
        WordPool pool = new WordPool();
        Map.Entry<Long, Word> created = pool.create(new Language("日本語"), "つくえ");

        // act
        String result = pool.replace(created.getKey(), "くえ", "ばき");

        // assert
        Word actual = pool.find(result);
        assertEquals("つばき", actual.getWord());
        assertEquals("日本語", actual.getLanguage().name);
        assertEquals(created.getKey(), pool.findId("つばき"));
    }

    public void test_getLanguages() {
        // arrange
        WordPool pool = new WordPool();
        Language expected = new LanguagePool().getLanguage("Japanese");

        List<Language> languages = pool.getLanguages();

        assertEquals(expected.name, languages.get(0).name);
        assertEquals(expected.description, languages.get(0).description);
        assertEquals(expected.countries, languages.get(0).countries);
        assertEquals(expected.rank, languages.get(0).rank);
    }
}
