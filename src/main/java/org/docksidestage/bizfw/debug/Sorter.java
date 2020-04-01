package org.docksidestage.bizfw.debug;

import java.util.List;

/**
 * @author zaya
 */
public interface Sorter<T> {
    public List<T> sort();

    public List<T> sort(List<T> list);
}
