package org.docksidestage.bizfw.debug.sorter;

import java.util.List;

// TODO zaya <T> のJavaDocをお願い by jflute (2020/04/20)
/**
 * @author zaya
 */
public interface Sorter<T> {
    // TODO zaya インターフェースのメソッドは、public無しでOK (省略可能) by jflute (2020/04/20)
    public List<T> sort();

    public List<T> sort(List<T> list);
}
