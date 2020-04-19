package org.docksidestage.bizfw.debug.searcher;

import java.util.Iterator;
import java.util.List;

import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.WordPool;

/**
 * @author zaya
 */
public class IteratorSearcher implements Searcher {
    public List<Word> words;

    public IteratorSearcher() {
        words = new WordPool().getWords();
    }

    @Override
    public Word search(String searchingFor) {
        Iterator<Word> iterator = words.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getWord().equals(searchingFor)) {
                return iterator.next();
            }
        }
        throw new IllegalArgumentException("the word you are looking for is not here, word:" + searchingFor);
    }
}
