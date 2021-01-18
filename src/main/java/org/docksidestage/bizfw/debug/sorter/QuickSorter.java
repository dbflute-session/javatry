/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.debug.sorter;

import java.util.List;

import org.docksidestage.bizfw.debug.Word;

/**
 * @author zaya
 * @author jflute
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
