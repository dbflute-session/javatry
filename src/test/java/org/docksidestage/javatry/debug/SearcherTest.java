package org.docksidestage.javatry.debug;

import org.docksidestage.bizfw.debug.BinarySearcher;
import org.docksidestage.bizfw.debug.IteratorSearcher;
import org.docksidestage.bizfw.debug.Word;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zaya
 */
public class SearcherTest {

    @Test
    public void test_binarySearch_found() {
        // act
        Word found = new BinarySearcher().search("私");

        // assert
        Assert.assertEquals("日本語", found.getLanguage().name);
        Assert.assertEquals("私", found.getWord());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_binarySearch_notFound() {
        new BinarySearcher().search("君");
    }

    /**
     * todo iterator.nextを変数化しないで、返す時も呼ぶようにする by zaya
     */
    @Test
    public void test_iteratorSearch_found() {
        // act
        Word found = new IteratorSearcher().search("私");

        // assert
        Assert.assertEquals("日本語", found.getLanguage().name);
        Assert.assertEquals("私", found.getWord());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_iteratorSearch_notFound() {
        new IteratorSearcher().search("君");
    }
}
