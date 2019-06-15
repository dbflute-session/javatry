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
package org.docksidestage.javatry.basic.base.st5;

/**
 * @author jflute
 */
public class St5TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St5TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    public void buyOneDayPassport(int money) {
        if (quantity <= 0) {
            throw new St5TicketSoldOutException("Sold out");
        }
        --quantity;
        if (money < ONE_DAY_PRICE) {
            throw new St5TicketShortMoneyException("Short money: " + money);
        }
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + money;
        } else {
            salesProceeds = money;
        }
    }

    public static class St5TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public St5TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class St5TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public St5TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
