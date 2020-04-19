package org.docksidestage.javatry.debug;

import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.searcher.BinarySearcher;
import org.docksidestage.bizfw.debug.searcher.IteratorSearcher;
import org.docksidestage.bizfw.debug.searcher.LinearSearcher;
import org.docksidestage.unit.PlainTestCase;
import org.junit.Assert;

/**
 * @author zaya
 */
public class Step22SearcherTest extends PlainTestCase {

    // todo languageのkeyを日本語にして検索するようにする by zaya
    public void test_binarySearch_found() {
        // act
        Word found = new BinarySearcher().search("私");

        // assert
        Assert.assertEquals("日本語", found.getLanguage().name);
        Assert.assertEquals("私", found.getWord());
    }

    public void test_binarySearch_notFound() {
        assertException(IllegalArgumentException.class, () -> new BinarySearcher().search("君"));
    }

    /**
     * todo iterator.nextを変数化しないで、返す時も呼ぶようにする by zaya
     */
    public void test_iteratorSearch_found() {
        // act
        Word found = new IteratorSearcher().search("私");

        // assert
        Assert.assertEquals("日本語", found.getLanguage().name);
        Assert.assertEquals("私", found.getWord());
    }

    public void test_iteratorSearch_notFound() {
        assertException(IllegalArgumentException.class, () -> new IteratorSearcher().search("君"));
    }

    public void test_linearSearch_found() {
        // act
        Word found = new LinearSearcher().search("私");

        // assert
        Assert.assertEquals("日本語", found.getLanguage().name);
        Assert.assertEquals("私", found.getWord());
    }

    public void test_linearSearch_notFound() {
        assertException(IllegalArgumentException.class, () -> new LinearSearcher().search("君"));
    }
}
