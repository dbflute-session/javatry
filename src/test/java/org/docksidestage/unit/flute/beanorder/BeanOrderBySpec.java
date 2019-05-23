/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.unit.flute.beanorder;

import java.util.function.Function;

/**
 * @param <BEAN> The type of bean.
 * @author jflute (from UTFlute)
 */
public class BeanOrderBySpec<BEAN> {

    protected final int specNo;
    protected final Function<BEAN, Comparable<? extends Object>> valueProvider;
    protected final boolean asc;
    protected final boolean nullsFirst;

    public BeanOrderBySpec(int specNo, Function<BEAN, Comparable<? extends Object>> valueProvider, boolean asc, boolean nullsFirst) {
        this.specNo = specNo;
        this.valueProvider = valueProvider;
        this.asc = asc;
        this.nullsFirst = nullsFirst;
    }

    public int getSpecNo() {
        return specNo;
    }

    public Function<BEAN, Comparable<? extends Object>> getValueProvider() {
        return valueProvider;
    }

    public boolean isAsc() {
        return asc;
    }

    public boolean isNullsFirst() {
        return nullsFirst;
    }
}
