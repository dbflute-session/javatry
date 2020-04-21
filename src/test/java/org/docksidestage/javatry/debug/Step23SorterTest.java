package org.docksidestage.javatry.debug;

import java.util.Arrays;
import java.util.List;

import org.docksidestage.bizfw.debug.Language;
import org.docksidestage.bizfw.debug.LanguagePool;
import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.WordAssort;
import org.docksidestage.bizfw.debug.sorter.WordSorter;
import org.docksidestage.unit.PlainTestCase;
import org.junit.Assert;

/**
 * All of the tests are failing as a consequence of some bug.
 * Debug, fix the bug, make it green and save the world!
 * (テストの一部がバグ原因で落ちています。デバグして、バグを直して、テストを通るようにしてください。)
 * @author zaya
 */
public class Step23SorterTest extends PlainTestCase {

    public void test_languageSort() {
        // act
        List<Language> languages = new LanguagePool().sort();

        // assert
        int rank = 0;
        for (Language language : languages) {
            assertTrue(rank < language.rank);
            rank = language.rank;
        }
    }

    public void test_wordSorter() {
        // arrange
        Language language = new LanguagePool().getLanguage("English");
        List<Word> input = Arrays.asList(new Word(language, "Candy"), new Word(language, "Table"), new Word(language, "Bee"),
                new Word(language, "Zebra"), new Word(language, "Apple"));
        List<Word> expected = Arrays.asList(new Word(language, "Apple"), new Word(language, "Bee"), new Word(language, "Candy"),
                new Word(language, "Table"), new Word(language, "Zebra"));

        // act
        List<Word> result = new WordSorter().sort(input);

        // assert
        for (int i = 0; i < result.size(); i++) {
            Assert.assertEquals(expected.get(i).getLanguage().name, result.get(i).getLanguage().name);
            Assert.assertEquals(expected.get(i).getWord(), result.get(i).getWord());
        }
    }

    public void test_wordPoolSorter() {
        // arrange
        Language language = new LanguagePool().getLanguage("Japanese");
        List<Word> expected =
                Arrays.asList(new Word(language, "昴"), new Word(language, "柿"), new Word(language, "私"), new Word(language, "荼"));

        // act
        List<Word> result = new WordAssort().sort();

        // assert
        for (int i = 0; i < result.size(); i++) {
            Assert.assertEquals(expected.get(i).getLanguage().name, result.get(i).getLanguage().name);
            Assert.assertEquals(expected.get(i).getWord(), result.get(i).getWord());
        }
    }
}
