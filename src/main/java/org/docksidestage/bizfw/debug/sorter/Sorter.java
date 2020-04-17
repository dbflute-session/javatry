package org.docksidestage.bizfw.debug.sorter;

import java.util.List;

/**
 * @author zaya
 */
public interface Sorter<T> {
    public List<T> sort();

    public List<T> sort(List<T> list);
}
