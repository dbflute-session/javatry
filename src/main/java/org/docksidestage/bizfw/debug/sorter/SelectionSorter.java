package org.docksidestage.bizfw.debug.sorter;

import java.util.List;

import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.WordPool;

/**
 * @author zaya
 */
public class SelectionSorter implements Sorter<Word> {
    public List<Word> words;

    public SelectionSorter() {
        words = new WordPool().getWords();
    }

    @Override
    public List<Word> sort() {
        return sort(words);
    }

    @Override
    public List<Word> sort(List<Word> wordList) {
        int n = wordList.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (wordList.get(j).getWord().compareTo(wordList.get(min).getWord()) < 0) {
                    min = j;
                }
            }
            Word temp = wordList.get(min);
            wordList.set(min, wordList.get(i));
            wordList.set(i, temp);
        }
        return wordList;
    }
}
