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
package org.docksidestage.javatry.basic;

import org.docksidestage.javatry.basic.base.St5TicketBooth;
import org.docksidestage.javatry.basic.base.St5TicketBooth.St5TicketShortMoneyException;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_ticket_basic() {
        St5TicketBooth booth = new St5TicketBooth();
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea); // your answer? =>
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_ticket_overpay() {
        St5TicketBooth booth = new St5TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? =>
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_ticket_nosales() {
        St5TicketBooth booth = new St5TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? =>
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_ticket_wrongQuantity_confirmation() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? =>
    }

    private Integer doTest_class_ticket_wrongQuantity() {
        St5TicketBooth booth = new St5TicketBooth();
        int money = 7399;
        try {
            booth.buyOneDayPassport(money);
            fail("always exception but none");
        } catch (St5TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + money, continued);
        }
        return booth.getQuantity();
    }

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_ticket_wrongQuantity_fixBug() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here
    }

    /**
     * (TwoDayPassportも買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_ticket_makeMethod_twoday() {
        // comment out after making the method
        //St5TicketBooth booth = new St5TicketBooth();
        //int money = 14000;
        //int change = booth.buyTwoDayPassport(money);
        //Integer sea = booth.getSalesProceeds() + change;
        //log(sea); // should be same as money

        // and show two-day passport quantity here
    }

    // ===================================================================================
    //                                                                            Challnge
    //                                                                            ========
    /**
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_ticket_refactor_recycle() {
        St5TicketBooth booth = new St5TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
    }

    /**
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でSt5Ticketクラスを戻すようにしてインしましょう)
     */
    public void test_class_ticket_refactor_return() {
        // comment out after modifying the method
        //St5TicketBooth booth = new St5TicketBooth();
        //St5Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        //log(oneDayPassport.isAlreadyIn()); // should be false
        //oneDayPassport.doInPark();
        //log(oneDayPassport.isAlreadyIn()); // should be true
    }

    /**
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_ticket_refactor_whole() {
        // comment out after modifying the method
        //St5TicketBooth booth = new St5TicketBooth();
        //int money = 20000;
        //St5TicketBuyResult twoDayPassportResult = booth.buyTwoDayPassport(money);
        //St5Ticket twoDayPassport = twoDayPassportResult.getTicket();
        //int change = twoDayPassportResult.getChange();
        //log(twoDayPassport.getBuyingPrice() + change); // should be same as money
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * (その他、気になるところがあったらリファクタリングしてみましょう (バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_ticket_refactor_yourself() {
        // write confirmation code here
    }
}
