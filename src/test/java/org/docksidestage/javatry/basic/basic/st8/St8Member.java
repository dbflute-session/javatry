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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author jflute
 */
public class St8Member {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Integer memberId;
    private final String memberName;
    private final St8Withdrawal withdrawal; // null allowed
    private List<St8Purchase> purchaseList = new ArrayList<>();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St8Member(Integer memberId, String memberName, St8Withdrawal withdrawal) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.withdrawal = withdrawal;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Integer getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public Optional<St8Withdrawal> getWithdrawal() { // Java8 style
        return Optional.ofNullable(withdrawal);
    }

    public St8Withdrawal oldgetWithdrawal() { // traditional style
        return withdrawal;
    }

    public List<St8Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void addPurchase(St8Purchase purchase) {
        purchaseList.add(purchase);
    }
}
