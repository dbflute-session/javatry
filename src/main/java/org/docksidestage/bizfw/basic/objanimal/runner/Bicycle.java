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
package org.docksidestage.bizfw.basic.objanimal.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for bicycle(自転車).
 * @author jflute
 */
public class Bicycle implements FastRunner {

    private static final Logger logger = LoggerFactory.getLogger(Bicycle.class);

    @Override
    public void run() {
        // dummy implementation
        logger.debug("I want to ride my bicycle");
    }
}
