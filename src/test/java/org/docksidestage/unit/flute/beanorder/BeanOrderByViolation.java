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

/**
 * @param <BEAN> The type of bean.
 * @author jflute (from UTFlute)
 */
public class BeanOrderByViolation<BEAN> {

    protected final int specNo;
    protected final BEAN previousBean;
    protected final BEAN nextBean;
    protected final Object previousValue;
    protected final Object nextValue;

    public BeanOrderByViolation(int specNo, BEAN previousBean, BEAN nextBean, Object previousValue, Object nextValue) {
        this.specNo = specNo;
        this.previousBean = previousBean;
        this.nextBean = nextBean;
        this.previousValue = previousValue;
        this.nextValue = nextValue;
    }

    @Override
    public String toString() {
        return "violation:{specNo=" + specNo + ", previous=" + previousValue + ", next=" + nextValue + "}";
    }

    public int getSpecNo() {
        return specNo;
    }

    public BEAN getPreviousBean() {
        return previousBean;
    }

    public BEAN getNextBean() {
        return nextBean;
    }

    public Object getPreviousValue() {
        return previousValue;
    }

    public Object getNextValue() {
        return nextValue;
    }
}
