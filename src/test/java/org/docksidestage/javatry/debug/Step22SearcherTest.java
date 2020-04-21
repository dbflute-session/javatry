package org.docksidestage.javatry.debug;

import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.searcher.IteratorSearcher;
import org.docksidestage.bizfw.debug.searcher.LinearSearcher;
import org.docksidestage.unit.PlainTestCase;
import org.junit.Assert;

/**
 * Some of the tests are failing as a consequence of some bug.
 * Debug, fix the bug, make it green and save the world!
 * (テストの一部がバグ原因で落ちています。デバグして、バグを直して、テストを通るようにしてください。)
 * @author zaya
 */
public class Step22SearcherTest extends PlainTestCase {

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

    public void test_linearSearch_notFound() {
        assertException(IllegalArgumentException.class, () -> new LinearSearcher().search("君"));
    }
}
