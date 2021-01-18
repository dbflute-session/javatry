package org.docksidestage.javatry.debug;

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.bizfw.debug.Language;
import org.docksidestage.bizfw.debug.LanguagePool;
import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.WordAssort;
import org.docksidestage.bizfw.debug.sorter.WordSorter;
import org.docksidestage.unit.PlainTestCase;

/**
 * All of the tests are failing as a consequence of some bug.
 * Debug, fix the bug, make it green and save the world!
 * (テストの一部がバグ原因で落ちています。デバグして、バグを直して、テストを通るようにしてください。)
 * @author zaya
 * @author jflute
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
        List<Word> input = createWordList(language, "Candy", "Table", "Bee", "Zebra", "Apple");
        List<Word> expected = createWordList(language, "Apple", "Bee", "Candy", "Table", "Zebra");

        // act
        List<Word> result = new WordSorter().sort(input);

        // assert
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expected.get(i).getLanguage().name, result.get(i).getLanguage().name);
            assertEquals(expected.get(i).getWord(), result.get(i).getWord());
        }
    }

    public void test_wordPoolSorter() {
        // arrange
        Language language = new LanguagePool().getLanguage("Japanese");
        List<Word> expected = createWordList(language, "昴", "柿", "私", "荼");

        // act
        List<Word> result = new WordAssort().sort();

        // assert
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expected.get(i).getLanguage().name, result.get(i).getLanguage().name);
            assertEquals(expected.get(i).getWord(), result.get(i).getWord());
        }
    }

    private List<Word> createWordList(Language language, String... words) {
        List<Word> wordList = new ArrayList<Word>();
        for (String word : words) {
            wordList.add(new Word(language, word));
        }
        return wordList;
    }
}
