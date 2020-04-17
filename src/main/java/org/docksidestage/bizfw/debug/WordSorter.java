package org.docksidestage.bizfw.debug;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.docksidestage.bizfw.debug.sorter.BubbleSorter;
import org.docksidestage.bizfw.debug.sorter.SelectionSorter;
import org.docksidestage.bizfw.debug.sorter.Sorter;

/**
 * @author zaya
 */
public class WordSorter implements Sorter<Word> {
    public List<Word> words;

    public WordSorter() {
        words = new WordPool().getWords();
    }

    @Override
    public List<Word> sort() {
        List<Sorter> sorters = Arrays.asList(new BubbleSorter(), new SelectionSorter());
        int i = new Random().nextInt(sorters.size() - 1);
        return sorters.get(i).sort();
    }

    @Override
    public List<Word> sort(List<Word> list) {
        List<Sorter> sorters = Arrays.asList(new BubbleSorter(), new SelectionSorter());
        int i = new Random().nextInt(sorters.size() - 1);
        return sorters.get(i).sort(list);
    }
}
