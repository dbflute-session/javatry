package org.docksidestage.bizfw.debug.sorter;

import java.util.List;

// done TODO zaya <T> のJavaDocをお願い by jflute (2020/04/20)

/**
 * @author zaya
 * @param <T> the type of elements in the list to be sorted (ソートされるリストの要素の型)
 */
public interface Sorter<T> {
    // done TODO zaya インターフェースのメソッドは、public無しでOK (省略可能) by jflute (2020/04/20)
    List<T> sort();

    List<T> sort(List<T> list);
}
