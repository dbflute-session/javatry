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
public class St8DbFacade {

    // ===================================================================================
    //                                                                     one-to-one Only
    //                                                                     ===============
    public St8Member oldselectMember(Integer memberId) { // traditional style
        if (memberId.equals(1)) {
            return new St8Member(memberId, "broadway", new St8Withdrawal(11, "music"));
        } else if (memberId.equals(2)) {
            return new St8Member(memberId, "dockside", new St8Withdrawal(12, null));
        } else if (memberId.equals(3)) {
            return new St8Member(memberId, "hangar", null);
        } else {
            return null;
        }
    }

    public Optional<St8Member> selectMember(Integer stageId) { // Java8 style
        return Optional.ofNullable(oldselectMember(stageId));
    }

    // ===================================================================================
    //                                                                    one-to-many Also
    //                                                                    ================
    public List<St8Member> selectMemberListAll() {
        int firstId = 1;
        St8Member firstMember = selectMember(firstId).orElseThrow(() -> {
            return new IllegalStateException("not found by firstId:" + firstId);
        });
        firstMember.addPurchase(new St8Purchase(111, 100));
        firstMember.addPurchase(new St8Purchase(112, 200));
        firstMember.addPurchase(new St8Purchase(113, 200));
        firstMember.addPurchase(new St8Purchase(114, 300));

        int secondId = 2;
        St8Member secondMember = selectMember(secondId).orElseThrow(() -> {
            return new IllegalStateException("not found by secondId:" + secondId);
        });

        int thirdId = 3;
        St8Member thirdMember = selectMember(thirdId).orElseThrow(() -> {
            return new IllegalStateException("not found by thirdId:" + thirdId);
        });
        thirdMember.addPurchase(new St8Purchase(131, 700));
        thirdMember.addPurchase(new St8Purchase(132, 800));

        List<St8Member> memberList = new ArrayList<>();
        memberList.add(firstMember);
        memberList.add(secondMember);
        memberList.add(thirdMember);
        return memberList;
    }
}
