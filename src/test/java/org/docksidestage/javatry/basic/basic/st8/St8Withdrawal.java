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
package org.docksidestage.javatry.basic.st8;

import java.util.Optional;

/**
 * @author jflute
 */
public class St8Withdrawal {

    private final Integer withdrawalId;
    private final String primaryReason; // null allowed

    public St8Withdrawal(Integer withdrawalId, String primaryReason) {
        this.withdrawalId = withdrawalId;
        this.primaryReason = primaryReason;
    }

    public Integer getWithdrawalId() {
        return withdrawalId;
    }

    public Optional<String> getPrimaryReason() { // Java8 style
        return Optional.ofNullable(primaryReason);
    }

    public String oldgetPrimaryReason() { // traditional style
        return primaryReason;
    }
}
