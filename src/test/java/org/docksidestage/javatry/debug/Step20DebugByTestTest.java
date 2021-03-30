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

import org.docksidestage.bizfw.basic.objanimal.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.unit.PlainTestCase;

import junit.framework.AssertionFailedError;

/**
 * The test of Debug by Test using JUnit. <br>
 * Show answer by log() or write answer on comment for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step20DebugByTestTest extends PlainTestCase {

    // ===================================================================================
    //                                                                           Assertion
    //                                                                           =========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_assertion_assertTrue() {
        String sea = "mystic";
        int hangar = 0;
        int broadway = 0;
        String message = "hangar=" + hangar + ", broadway=" + broadway;
        boolean condition = hangar > broadway;
        try {
            assertTrue(message, condition);
        } catch (AssertionFailedError e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => 
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_assertion_assertNull() {
        String sea = "mystic";
        String object = "oneman";
        String message = "must be null, object=" + object;
        try {
            assertNull(message, object);
        } catch (AssertionFailedError e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => 
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_assertion_assertEquals() { // too difficult
        String sea = "mystic";
        try {
            String expected = "showbase";
            String actual = "oneman";
            assertEquals(expected, actual);
        } catch (AssertionFailedError e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => 
    }

    // ===================================================================================
    //                                                                          Basic Test
    //                                                                          ==========
    // -----------------------------------------------------
    //                                               Example
    //                                               -------
    /**
     * Test that the Dog's bark() returns "wan" as a bark word. <br>
     * (Dogのbark()を呼ぶと鳴き声として "wan" が戻ってくることをテストしましょう)
     */
    public void test_basicTest_example_normal() { // learn here
        // ## Arrange ##
        Dog dog = new Dog();

        // ## Act ##
        BarkedSound bark = dog.bark();

        // ## Assert ##
        String expected = "wan";
        assertEquals(expected, bark.getBarkWord());
    }

    /**
     * Test that the Dog's bark() in case short of hit-point throws the "tired" exception. <br>
     * (Dogのbark()を呼んでヒットポイントがなくなると、疲れた例外が投げられることをテストしましょう)
     */
    public void test_basicTest_example_exception() { // learn here
        // ## Arrange ##
        Dog dog = new Dog() { // simple mock
            @Override
            protected int getInitialHitPoint() { // for test
                return 1; // immediately tired
            }
        };

        try {
            // ## Act ##
            dog.bark();

            // ## Assert ##
            fail("bark() must throw");
        } catch (IllegalStateException e) {
            log(e);
            assertTrue(e.getMessage().contains("tired")); // keyword only here
        }

        // you can also implement it like this:
        //assertException(IllegalStateException.class, () -> dog.bark()).handle(cause -> {
        //    assertTrue(cause.getMessage().contains("tired")); // keyword only here
        //});
    }

    // -----------------------------------------------------
    //                                           Let's write
    //                                           -----------
    /**
     * Test that sales proceeeds is correct in case of buying one day ticket with 10,000 yen from TicketBooth. <br>
     * (TicketBoothで、10,000円を渡して1Dayチケットを買うと、売上が適切な金額になることをテストしましょう)
     */
    public void test_basicTest_TicketBooth_buyOneDayPassport_salesProceeds() {
        // ## Arrange ##

        // ## Act ##

        // ## Assert ##
    }

    /**
     * Test that buying one day ticket from TicketBooth in case sold out throws the "sold out" exception. <br>
     * (TicketBoothで、売り切れてる状態で1Dayチケットを買うと、売り切れを表す例外が投げられることをテストしましょう)
     */
    public void test_basicTest_TicketBooth_buyOneDayPassport_soldOut() {
        // ## Arrange ##

        // ## Act ##

        // ## Assert ##
    }

    /**
     * Test something you like about TicketBooth. <br>
     * (TicketBoothの何かしらのケースを好きなようにテストしましょう)
     */
    public void test_basicTest_TicketBooth_freedom_asYouLikeIt() {
        // ## Arrange ##

        // ## Act ##

        // ## Assert ##
    }

    // ===================================================================================
    //                                                                      Make TestClass
    //                                                                      ==============
    /**
     * Test ZombieDiary count for Zombie's bark() on ZombieTest class. <br>
     * (ZombieTestクラスにて、Zombieがbark()するたびにZombieDiaryにカウントが記録されることをテストしましょう)
     */
    public void test_makeTestClass_Zombie() {
        // no use here
        //
        // make your test class at the following place:
        //
        // src/test/java
        //   |-org.docksidestage
        //      |-bizfw
        //         |-basic
        //            |-objanimal
        //               |-ZombieTest.java (extends PlainTestCase)
        //
        // basically you can auto-generate it by IDE (e.g. Eclipse, IntelliJ)
    }

    /**
     * Test something you like about a class in src/main/java. <br>
     * (src/main/javaの何かしらのクラスを好きなようにテストしましょう)
     */
    public void test_makeTestClass_asYouLikeIt() {
        // no use here
    }

    // ===================================================================================
    //                                                                         JUnit Intro
    //                                                                         ===========
    /**
     * Investigate the difference between JUnit3, JUnit4, JUnit5 easily in about 5 minutes and 10 minutes. <br>
     * (JUnit3, JUnit4, JUnit5の違いを、5分10分ほどで簡単に調べてみましょう)
     */
    public void test_JUnitIntro_learnVersion() {
        // [small tips]
        //
        // JUnit3:
        //  o test class needs extends [any]TestCase
        //  o test method needs prefix "test_"
        //  o super class style
        //
        // JUnit4:
        //  o no super class
        //  o test method needs @Test annotation
        //  o annotation style
        //
        // JUnit5:
        //  o no super class
        //  o test method needs @Test annotation
        //  o extremely improving JUnit4
    }

    /**
     * Read the source code of PlainTestCase class easily in about 2,3 minutes. <br>
     * (PlainTestCaseクラスのソースコードを2,3分ほどで軽く読んでみましょう)
     */
    public void test_JUnitIntro_readSuperClass() {
        // no use here
    }
}
