package org.docksidestage.javatry.debug;

import java.util.Arrays;
import java.util.List;

import org.docksidestage.bizfw.debug.Language;
import org.docksidestage.bizfw.debug.LanguagePool;
import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.WordSorter;
import org.docksidestage.unit.PlainTestCase;
import org.junit.Assert;

/**
 * @author zaya
 */
public class Step23WordSorterTest extends PlainTestCase {

    /**
     * todo 1/3が落ちるようにする（３種類のsorterの中で1つだけバグを埋め込んで）by zaya
     */
    public void testSort() {
        // arrange
        Language language = new LanguagePool().getLanguage("English");
        List<Word> input = Arrays.asList(new Word(language, "Candy"), new Word(language, "Bee"), new Word(language, "Apple"));
        List<Word> expected = Arrays.asList(new Word(language, "Apple"), new Word(language, "Bee"), new Word(language, "Candy"));

        // act
        List<Word> result = new WordSorter().sort(input);

        // assert
        for (int i = 0; i < result.size(); i++) {
            Assert.assertEquals(expected.get(i).getLanguage().name, result.get(i).getLanguage().name);
            Assert.assertEquals(expected.get(i).getWord(), result.get(i).getWord());
        }
    }
}
