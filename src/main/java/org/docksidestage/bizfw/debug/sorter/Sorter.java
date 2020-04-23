package org.docksidestage.bizfw.debug.sorter;

import java.util.List;

/**
 * @author zaya
 * @param <T> the type of elements in the list to be sorted (ソートされるリストの要素の型)
 */
public interface Sorter<T> {
    List<T> sort();

    List<T> sort(List<T> list);
}
