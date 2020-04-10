package org.docksidestage.bizfw.debug;

import java.util.Iterator;
import java.util.List;

public class IteratorSearcher implements Searcher {
    public List<Word> words;

    public IteratorSearcher() {
        words = new WordPool().getWords();
    }

    @Override
    public Word search(String searchingFor) {
        Iterator<Word> iterator = words.iterator();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            if (word.getWord().equals(searchingFor)) {
                return word;
            }
        }
        throw new IllegalArgumentException("the word you are looking for is not here, word:" + searchingFor);
    }
}
