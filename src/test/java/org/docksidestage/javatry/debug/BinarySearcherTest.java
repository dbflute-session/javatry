package org.docksidestage.javatry.debug;

import org.docksidestage.bizfw.debug.BinarySearcher;
import org.docksidestage.bizfw.debug.Word;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zaya
 */
public class BinarySearcherTest {

    @Test
    public void testSearch_found() {
        // act
        Word found = new BinarySearcher().search("私");

        // assert
        Assert.assertEquals("日本語", found.getLanguage().name);
        Assert.assertEquals("私", found.getWord());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearch_notFound() {
        new BinarySearcher().search("君");
    }
}
