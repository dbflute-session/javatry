package org.docksidestage.javatry.debug;

import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.WordSearcher;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zaya
 */
public class WordSearcherTest {

    @Test
    public void testSearch_found() {
        Word found = new WordSearcher().search("私");
        Assert.assertEquals("日本語", found.getLanguage().name);
        Assert.assertEquals("私", found.getWord());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearch_notFound() {
        new WordSearcher().search("君");
    }
}
