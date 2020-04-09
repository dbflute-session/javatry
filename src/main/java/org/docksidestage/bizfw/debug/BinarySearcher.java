package org.docksidestage.bizfw.debug;

import java.util.List;

public class BinarySearcher implements Searcher {
    public List<Word> words;

    public BinarySearcher() {
        words = new WordPool().getWords();
    }

    /**
     * todo up lowをupper underにする by zaya
     * @param searchingFor
     * @return
     */
    @Override
    public Word search(String searchingFor) {
        int searchingForIndex = -1;
        int low = 0;
        int up = words.size() - 1;
        while (low <= up) {
            int mid = (low + up) / 2;
            if (words.get(mid).getWord().equals(searchingFor)) {
                searchingForIndex = mid;
                break;
            } else if (words.get(mid).getWord().compareTo(searchingFor) > 0) {
                low = mid + 1;
            } else {
                up = mid - 1;
            }
        }
        if (searchingForIndex < 0) {
            throw new IllegalArgumentException("the word you are looking for is not here, word:" + searchingFor);
        }
        return words.get(searchingForIndex);
    }
}
