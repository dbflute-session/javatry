package org.docksidestage.bizfw.debug;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.docksidestage.bizfw.debug.sorter.*;

/**
 * @author zaya
 */
public class WordAssort extends WordSorter {

    public WordAssort() {
        words = new WordPool().getWords();
    }

    @Override
    public List<Word> sort() {
        List<Sorter<Word>> sorters = Arrays.asList(new BubbleSorter(words), new SelectionSorter(words), new QuickSorter());
        int i = new Random().nextInt(sorters.size());
        return sorters.get(i).sort();
    }

    @Override
    public List<Word> sort(List<Word> list) {
        List<Sorter<Word>> sorters = Arrays.asList(new BubbleSorter(), new SelectionSorter(), new QuickSorter(list));
        int i = new Random().nextInt(sorters.size());
        return sorters.get(i).sort(list);
    }
}
