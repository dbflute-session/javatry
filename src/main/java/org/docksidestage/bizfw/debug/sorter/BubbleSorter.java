package org.docksidestage.bizfw.debug.sorter;

import java.util.List;

import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.WordPool;

/**
 * @author zaya
 */
public class BubbleSorter implements Sorter<Word> {
    // done TODO zaya final付けちゃおう (他のところも) by jflute (2020/04/20)
    public final List<Word> words;

    public BubbleSorter() {
        words = new WordPool().getWords();
    }

    @Override
    public List<Word> sort() {
        return sort(words);
    }

    @Override
    public List<Word> sort(List<Word> wordList) {
        int n = wordList.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (wordList.get(j).getWord().compareTo(wordList.get(j + 1).getWord()) > 0) {
                    Word temp = wordList.get(j);
                    wordList.set(j, wordList.get(j + 1));
                    wordList.set(j + 1, temp);
                }
        return wordList;
    }
}
