package org.docksidestage.bizfw.debug.sorter;

import java.util.List;

import org.docksidestage.bizfw.debug.Word;

/**
 * @author zaya
 */
public class QuickSorter implements Sorter<Word> {
    private List<Word> words;

    public QuickSorter() {
    }

    public QuickSorter(List<Word> words) {
        this.words = words;
    }

    @Override
    public List<Word> sort() {
        return sort(words);
    }

    @Override
    public List<Word> sort(List<Word> wordList) {
        return sort(wordList, 0, wordList.size() - 1);
    }

    int partition(List<Word> wordList, int under, int upper) {
        Word pivot = wordList.get(upper);
        int i = (under - 1);
        for (int j = under; j < upper; j++) {
            if (wordList.get(j).getWord().compareTo(pivot.getWord()) < 0) {
                i++;
                Word temp = wordList.get(i);
                wordList.set(i, wordList.get(j));
                wordList.set(j, temp);
            }
        }
        Word temp = wordList.get(i + 1);
        wordList.set(i + 1, wordList.get(upper));
        wordList.set(upper, temp);
        return i + 1;
    }

    List<Word> sort(List<Word> wordList, int low, int high) {
        if (low < high) {
            int pi = partition(wordList, low, high);
            sort(wordList, low, pi - 1);
            sort(wordList, pi + 1, high);
        }
        return wordList;
    }
}
