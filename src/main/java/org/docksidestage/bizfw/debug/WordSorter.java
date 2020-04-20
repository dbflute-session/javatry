package org.docksidestage.bizfw.debug;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.docksidestage.bizfw.debug.sorter.BubbleSorter;
import org.docksidestage.bizfw.debug.sorter.QuickSorter;
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
        // TODO zaya Sorterのジェネリック型 (Sorter<Word>) by jflute (2020/04/20)
        List<Sorter> sorters = Arrays.asList(new BubbleSorter(), new SelectionSorter(), new QuickSorter());
        int i = new Random().nextInt(sorters.size());
        return sorters.get(i).sort();
    }

    @Override
    public List<Word> sort(List<Word> list) {
        List<Sorter> sorters = Arrays.asList(new BubbleSorter(), new SelectionSorter(), new QuickSorter());
        int i = new Random().nextInt(sorters.size());
        return sorters.get(i).sort(list);
    }
}
