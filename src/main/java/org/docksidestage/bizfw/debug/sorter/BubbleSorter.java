/*
 * Copyright 2019-2021 the original author or authors.
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
public class BubbleSorter implements Sorter<Word> {

    private List<Word> words;

    public BubbleSorter() {
    }

    public BubbleSorter(List<Word> words) {
        this.words = words;
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
