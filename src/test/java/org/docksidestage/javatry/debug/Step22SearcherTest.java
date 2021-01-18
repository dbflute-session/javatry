/*
 * Copyright 2019-2021 the original author or authors.
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
package org.docksidestage.javatry.debug;

import org.docksidestage.bizfw.debug.Word;
import org.docksidestage.bizfw.debug.searcher.IteratorSearcher;
import org.docksidestage.bizfw.debug.searcher.LinearSearcher;
import org.docksidestage.unit.PlainTestCase;

/**
 * Some of the tests are failing as a consequence of some bug.
 * Debug, fix the bug, make it green and save the world!
 * (テストの一部がバグ原因で落ちています。デバグして、バグを直して、テストを通るようにしてください。)
 * @author zaya
 * @author jflute
 */
public class Step22SearcherTest extends PlainTestCase {

    public void test_iteratorSearch_found() {
        // act
        Word found = new IteratorSearcher().search("私");

        // assert
        assertEquals("日本語", found.getLanguage().name);
        assertEquals("私", found.getWord());
    }

    public void test_iteratorSearch_notFound() {
        assertException(IllegalArgumentException.class, () -> new IteratorSearcher().search("君"));
    }

    public void test_linearSearch_notFound() {
        assertException(IllegalArgumentException.class, () -> new LinearSearcher().search("君"));
    }
}
