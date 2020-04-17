package org.docksidestage.javatry.debug;

import java.util.List;

import javafx.util.Pair;
import org.docksidestage.bizfw.debug.Language;
import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.WordPool;
import org.docksidestage.unit.PlainTestCase;

/**
 * @author zaya
 */
public class Step21WordPoolTest extends PlainTestCase {

    public void test_getWords() {
        // arrange
        WordPool pool = new WordPool();

        // act
        List<Word> result = pool.getWords();

        // assert
        assertEquals(3, result.size());
        assertEquals("日本語", result.get(0).getLanguage().name);
        assertEquals("日本語", result.get(1).getLanguage().name);
        assertEquals("日本語", result.get(2).getLanguage().name);
        assertEquals("私", result.get(0).getWord());
        assertEquals("こんにちは", result.get(1).getWord());
        assertEquals("食べる", result.get(2).getWord());
    }

    public void test_create() {
        // arrange
        WordPool pool = new WordPool();

        // act
        Pair<Long, Word> actual = pool.create(new Language("日本語"), "財布");

        // assert
        assertTrue(pool.getWords().contains(actual.getValue()));
    }

    public void test_findById() {
        // arrange
        WordPool pool = new WordPool();

        // act
        Word found = pool.find(2L);

        // assert
        assertEquals("日本語", found.getLanguage().name);
        assertEquals("こんにちは", found.getWord());
    }

    public void test_findByWord() {
        // arrange
        WordPool pool = new WordPool();

        // act
        Word found = pool.find("食べる");

        // assert
        assertEquals("日本語", found.getLanguage().name);
        assertEquals("食べる", found.getWord());
    }

    public void test_findId() {
        // arrange
        WordPool pool = new WordPool();

        // act
        Long found = pool.findId("食べる");

        // assert
        assertEquals(new Long(3), found);
    }

    public void test_update() {
        // arrange
        WordPool pool = new WordPool();

        // act
        Word result = pool.update(1L, new Word(new Language("日本語"), "さようなら"));

        // assert
        assertEquals("さようなら", result.getWord());
        assertEquals("日本語", result.getLanguage().name);
        assertEquals(new Long(1), pool.findId("さようなら"));
    }

    /**
     * todo 更新前のwordを返すようにする by zaya
     */
    public void test_updateByWord() {
        // arrange
        WordPool pool = new WordPool();
        Pair<Long, Word> created = pool.create(new Language("日本語"), "つくえ");

        // act
        Word result = pool.update("つくえ", "ぼうし");

        // assert
        assertEquals("ぼうし", result.getWord());
        assertEquals("日本語", result.getLanguage().name);
        assertEquals(created.getKey(), pool.findId("ぼうし"));
    }

    /**
     * todo replaceのみ書いて、返り値使わないようにする by zaya
     */
    public void test_replace() {
        // arrange
        WordPool pool = new WordPool();
        Pair<Long, Word> created = pool.create(new Language("日本語"), "つくえ");

        // act
        Word result = pool.replace(created.getKey(), "くえ", "ばき");

        // assert
        assertEquals("つばき", result.getWord());
        assertEquals("日本語", result.getLanguage().name);
        assertEquals(created.getKey(), pool.findId("つばき"));
    }

    /**
     * todo 引数間違っている問題にする(もうちょっと引数欲しいかも) by zaya
     */
    public void test_update_withLanguage() {
        // arrange
        WordPool pool = new WordPool();
        Pair<Long, Word> created = pool.create(new Language("日本語"), "つくえ");

        // act
        Word result = pool.update("日本語", "つくえ", "ぼうし");

        // assert
        assertEquals("ぼうし", result.getWord());
        assertEquals("日本語", result.getLanguage().name);
        assertEquals(created.getKey(), pool.findId("ぼうし"));
    }

    public void test_delete() {
        // arrange
        WordPool pool = new WordPool();
        Pair<Long, Word> created = pool.create(new Language("日本語"), "削除");

        // act
        pool.delete(created.getKey());

        // assert
        assertFalse(pool.getWordsOnly().contains("削除"));
        assertNull(pool.find(created.getKey()));
    }
}
