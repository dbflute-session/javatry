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
package org.docksidestage.unit;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.docksidestage.unit.flute.beanorder.BeanOrderValidator;
import org.docksidestage.unit.flute.beanorder.ExpectedBeanOrderBy;
import org.docksidestage.unit.flute.exception.ExceptionExaminer;
import org.docksidestage.unit.flute.exception.ExceptionExpectationAfter;
import org.docksidestage.unit.flute.markhere.MarkHereManager;
import org.docksidestage.unit.flute.util.DfResourceUtil;
import org.docksidestage.unit.flute.util.Srl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

/**
 * @author jflute (from UTFlute)
 * @since 1.0.0 (2019/04/14 Sunday)
 */
public abstract class PlainTestCase extends TestCase {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The logger instance for sub class. (NotNull) */
    private final Logger _xlogger = LoggerFactory.getLogger(getClass());
    // UTFlute wants to use logger for caller output
    // but should remove the dependency to Log4j
    // (logging through commons-logging gives us fixed caller...)
    //protected final Logger _xlogger = Logger.getLogger(getClass());

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** The manager of mark here. (NullAllowed: lazy-loaded) */
    private MarkHereManager _xmarkHereManager;

    /** The reserved title for logging test case beginning. (NullAllowed: before preparation or already showed) */
    private String _xreservedTitle;

    // ===================================================================================
    //                                                                            Settings
    //                                                                            ========
    @Override
    protected void setUp() throws Exception {
        xreserveShowTitle();
        super.setUp();
    }

    protected void xreserveShowTitle() {
        // lazy-logging (no logging test case, no title)
        _xreservedTitle = "<<< " + xgetCaseDisp() + " >>>";
    }

    protected String xgetCaseDisp() {
        return getClass().getSimpleName() + "." + getName() + "()";
    }

    @Override
    protected void runTest() throws Throwable {
        try {
            super.runTest();
            postTest();
        } catch (Throwable e) { // to record in application log
            log("Failed to finish the test: " + xgetCaseDisp(), e);
            throw e;
        }
    }

    protected void postTest() {
    }

    @Override
    protected void tearDown() throws Exception {
        xclearMark(); // last process to be able to be used in tearDown()
        super.tearDown();
    }

    // -----------------------------------------------------
    //                                            Basic Info
    //                                            ----------
    protected Method getTestMethod() {
        String methodName = getName();
        try {
            return getClass().getMethod(methodName, (Class[]) null);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new IllegalStateException("Not found the method: " + methodName, e);
        }
    }

    // ===================================================================================
    //                                                                       Assert Helper
    //                                                                       =============
    // -----------------------------------------------------
    //                                                Equals
    //                                                ------
    // to avoid setting like this:
    //  assertEquals(Integer.valueOf(3), member.getMemberId())
    protected void assertEquals(String message, int expected, Integer actual) {
        assertEquals(message, Integer.valueOf(expected), actual);
    }

    protected void assertEquals(int expected, Integer actual) {
        assertEquals(null, Integer.valueOf(expected), actual);
    }

    // -----------------------------------------------------
    //                                            True/False
    //                                            ----------
    protected void assertTrueAll(boolean... conditions) {
        int index = 0;
        for (boolean condition : conditions) {
            assertTrue("conditions[" + index + "]" + " expected: <true> but was: " + condition, condition);
            ++index;
        }
    }

    protected void assertTrueAny(boolean... conditions) {
        boolean hasTrue = false;
        for (boolean condition : conditions) {
            if (condition) {
                hasTrue = true;
                break;
            }
        }
        assertTrue("all conditions were false", hasTrue);
    }

    protected void assertFalseAll(boolean... conditions) {
        int index = 0;
        for (boolean condition : conditions) {
            assertFalse("conditions[" + index + "]" + " expected: <false> but was: " + condition, condition);
            ++index;
        }
    }

    protected void assertFalseAny(boolean... conditions) {
        boolean hasFalse = false;
        for (boolean condition : conditions) {
            if (!condition) {
                hasFalse = true;
                break;
            }
        }
        assertTrue("all conditions were true", hasFalse);
    }

    // -----------------------------------------------------
    //                                                String
    //                                                ------
    /**
     * Assert that the string contains the keyword.
     * <pre>
     * String str = "foo";
     * assertContains(str, "fo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "oo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "foo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "Foo"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param str The string to assert. (NotNull)
     * @param keyword The keyword string. (NotNull)
     */
    protected void assertContains(String str, String keyword) {
        if (!Srl.contains(str, keyword)) {
            log("Asserted string: " + str); // might be large so show at log
            fail("the string should have the keyword but not found: " + keyword);
        }
    }

    /**
     * Assert that the string contains the keyword. (ignore case)
     * <pre>
     * String str = "foo";
     * assertContains(str, "fo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "oo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "foo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "Foo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "ux"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param str The string to assert. (NotNull)
     * @param keyword The keyword string. (NotNull)
     */
    protected void assertContainsIgnoreCase(String str, String keyword) {
        if (!Srl.containsIgnoreCase(str, keyword)) {
            log("Asserted string: " + str); // might be large so show at log
            fail("the string should have the keyword but not found: " + keyword);
        }
    }

    /**
     * Assert that the string contains all keywords.
     * <pre>
     * String str = "foo";
     * assertContains(str, "fo", "oo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "f", "foo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "f", "Foo"); <span style="color: #3F7E5E">// false</span>
     * assertContains(str, "fx", "oo"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param str The string to assert. (NotNull)
     * @param keywords The array of keyword string. (NotNull)
     */
    protected void assertContainsAll(String str, String... keywords) {
        if (!Srl.containsAll(str, keywords)) {
            log("Asserted string: " + str); // might be large so show at log
            fail("the string should have all keywords but not found: " + Arrays.asList(keywords));
        }
    }

    /**
     * Assert that the string contains all keywords. (ignore case)
     * <pre>
     * String str = "foo";
     * assertContains(str, "fo", "oo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "f", "foo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "f", "Foo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "fx", "oo"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param str The string to assert. (NotNull)
     * @param keywords The array of keyword string. (NotNull)
     */
    protected void assertContainsAllIgnoreCase(String str, String... keywords) {
        if (!Srl.containsAllIgnoreCase(str, keywords)) {
            log("Asserted string: " + str); // might be large so show at log
            fail("the string should have all keywords but not found: " + Arrays.asList(keywords));
        }
    }

    /**
     * Assert that the string contains any keyword.
     * <pre>
     * String str = "foo";
     * assertContains(str, "fo", "oo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "f", "foo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "f", "qux"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "F", "qux"); <span style="color: #3F7E5E">// false</span>
     * assertContains(str, "fx", "ux"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param str The string to assert. (NotNull)
     * @param keywords The array of keyword string. (NotNull)
     */
    protected void assertContainsAny(String str, String... keywords) {
        if (!Srl.containsAny(str, keywords)) {
            log("Asserted string: " + str); // might be large so show at log
            fail("the string should have any keyword but not found: " + Arrays.asList(keywords));
        }
    }

    /**
     * Assert that the string contains any keyword. (ignore case)
     * <pre>
     * String str = "foo";
     * assertContains(str, "fo", "oo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "f", "foo"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "f", "qux"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "F", "qux"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "fx", "ux"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param str The string to assert. (NotNull)
     * @param keywords The array of keyword string. (NotNull)
     */
    protected void assertContainsAnyIgnoreCase(String str, String... keywords) {
        if (!Srl.containsAnyIgnoreCase(str, keywords)) {
            log("Asserted string: " + str); // might be large so show at log
            fail("the string should have any keyword but not found: " + Arrays.asList(keywords));
        }
    }

    /**
     * Assert that the string does not contains the keyword.
     * <pre>
     * String str = "foo";
     * assertNotContains(str, "ux"); <span style="color: #3F7E5E">// true</span>
     * assertNotContains(str, "Foo"); <span style="color: #3F7E5E">// true</span>
     * assertNotContains(str, "fo"); <span style="color: #3F7E5E">// false</span>
     * assertNotContains(str, "oo"); <span style="color: #3F7E5E">// false</span>
     * assertNotContains(str, "foo"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param str The string to assert. (NotNull)
     * @param keyword The keyword string. (NotNull)
     */
    protected void assertNotContains(String str, String keyword) {
        if (Srl.contains(str, keyword)) {
            log("Asserted string: " + str); // might be large so show at log
            fail("the string should not have the keyword but found: " + keyword);
        }
    }

    /**
     * Assert that the string does not contains the keyword. (ignore case)
     * <pre>
     * String str = "foo";
     * assertContains(str, "ux"); <span style="color: #3F7E5E">// true</span>
     * assertContains(str, "Foo"); <span style="color: #3F7E5E">// false</span>
     * assertContains(str, "fo"); <span style="color: #3F7E5E">// false</span>
     * assertContains(str, "oo"); <span style="color: #3F7E5E">// false</span>
     * assertContains(str, "foo"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param str The string to assert. (NotNull)
     * @param keyword The keyword string. (NotNull)
     */
    protected void assertNotContainsIgnoreCase(String str, String keyword) {
        if (Srl.containsIgnoreCase(str, keyword)) {
            log("Asserted string: " + str); // might be large so show at log
            fail("the string should not have the keyword but found: " + keyword);
        }
    }

    // -----------------------------------------------------
    //                                                  List
    //                                                  ----
    /**
     * Assert that the list has an element containing the keyword.
     * <pre>
     * List&lt;String&gt; strList = ...; <span style="color: #3F7E5E">// [foo, bar]</span>
     * assertContainsKeyword(strList, "fo"); <span style="color: #3F7E5E">// true</span>
     * assertContainsKeyword(strList, "ar"); <span style="color: #3F7E5E">// true</span>
     * assertContainsKeyword(strList, "foo"); <span style="color: #3F7E5E">// true</span>
     * assertContainsKeyword(strList, "Foo"); <span style="color: #3F7E5E">// false</span>
     * assertContainsKeyword(strList, "ux"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param strList The list of string. (NotNull)
     * @param keyword The keyword string. (NotNull)
     */
    protected void assertContainsKeyword(Collection<String> strList, String keyword) {
        if (!Srl.containsKeyword(strList, keyword)) {
            fail("the list should have the keyword but not found: " + keyword);
        }
    }

    /**
     * Assert that the list has an element containing all keywords.
     * <pre>
     * List&lt;String&gt; strList = ...; <span style="color: #3F7E5E">// [foo, bar]</span>
     * assertContainsKeyword(strList, "fo", "ar", "foo"); <span style="color: #3F7E5E">// true</span>
     * assertContainsKeyword(strList, "fo", "ar", "Foo"); <span style="color: #3F7E5E">// false</span>
     * assertContainsKeyword(strList, "fo", "ux", "foo"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param strList The list of string. (NotNull)
     * @param keywords The array of keyword string. (NotNull)
     */
    protected void assertContainsKeywordAll(Collection<String> strList, String... keywords) {
        if (!Srl.containsKeywordAll(strList, keywords)) {
            fail("the list should have all keywords but not found: " + Arrays.asList(keywords));
        }
    }

    /**
     * Assert that the list has an element containing all keywords. (ignore case)
     * <pre>
     * List&lt;String&gt; strList = ...; <span style="color: #3F7E5E">// [foo, bar]</span>
     * assertContainsKeyword(strList, "fo", "ar", "foo"); <span style="color: #3F7E5E">// true</span>
     * assertContainsKeyword(strList, "fO", "ar", "Foo"); <span style="color: #3F7E5E">// true</span>
     * assertContainsKeyword(strList, "fo", "ux", "foo"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param strList The list of string. (NotNull)
     * @param keywords The array of keyword string. (NotNull)
     */
    protected void assertContainsKeywordAllIgnoreCase(Collection<String> strList, String... keywords) {
        if (!Srl.containsKeywordAllIgnoreCase(strList, keywords)) {
            fail("the list should have all keywords (case ignored) but not found: " + Arrays.asList(keywords));
        }
    }

    /**
     * Assert that the list has an element containing any keyword.
     * <pre>
     * List&lt;String&gt; strList = ...; <span style="color: #3F7E5E">// [foo, bar]</span>
     * assertContainsKeyword(strList, "fo", "ar", "foo"); <span style="color: #3F7E5E">// true</span>
     * assertContainsKeyword(strList, "fo", "ux", "qux"); <span style="color: #3F7E5E">// true</span>
     * assertContainsKeyword(strList, "Fo", "ux", "qux"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param strList The list of string. (NotNull)
     * @param keywords The array of keyword string. (NotNull)
     */
    protected void assertContainsKeywordAny(Collection<String> strList, String... keywords) {
        if (!Srl.containsKeywordAny(strList, keywords)) {
            fail("the list should have any keyword but not found: " + Arrays.asList(keywords));
        }
    }

    /**
     * Assert that the list has an element containing any keyword. (ignore case)
     * <pre>
     * List&lt;String&gt; strList = ...; <span style="color: #3F7E5E">// [foo, bar]</span>
     * assertContainsKeyword(strList, "fo", "ar", "foo"); <span style="color: #3F7E5E">// true</span>
     * assertContainsKeyword(strList, "fo", "ux", "qux"); <span style="color: #3F7E5E">// true</span>
     * assertContainsKeyword(strList, "Fo", "ux", "qux"); <span style="color: #3F7E5E">// true</span>
     * assertContainsKeyword(strList, "po", "ux", "qux"); <span style="color: #3F7E5E">// false</span>
     * </pre>
     * @param strList The list of string. (NotNull)
     * @param keywords The array of keyword string. (NotNull)
     */
    protected void assertContainsKeywordAnyIgnoreCase(Collection<String> strList, String... keywords) {
        if (!Srl.containsKeywordAnyIgnoreCase(strList, keywords)) {
            fail("the list should have any keyword (case ignored) but not found: " + Arrays.asList(keywords));
        }
    }

    /**
     * Assert that the list has any element (not empty). <br>
     * You can use this to guarantee assertion in loop like this:
     * <pre>
     * List&lt;Member&gt; memberList = memberBhv.selectList(cb);
     * <span style="color: #FD4747">assertHasAnyElement(memberList);</span>
     * for (Member member : memberList) {
     *     assertTrue(member.getMemberName().startsWith("S"));
     * }
     * </pre>
     * @param notEmptyList The list expected not empty. (NotNull)
     */
    protected void assertHasAnyElement(Collection<?> notEmptyList) {
        if (notEmptyList.isEmpty()) {
            fail("the list should have any element (not empty) but empty.");
        }
    }

    protected void assertHasOnlyOneElement(Collection<?> lonelyList) {
        if (lonelyList.size() != 1) {
            fail("the list should have the only one element but: " + lonelyList);
        }
    }

    protected void assertHasPluralElement(Collection<?> crowdedList) {
        if (crowdedList.size() < 2) {
            fail("the list should have plural elements but: " + crowdedList);
        }
    }

    protected void assertHasZeroElement(Collection<?> emptyList) {
        if (!emptyList.isEmpty()) {
            fail("the list should have zero element (empty) but: " + emptyList);
        }
    }

    // -----------------------------------------------------
    //                                             Exception
    //                                             ---------
    /**
     * Assert that the callback throws the exception.
     * <pre>
     * String <span style="color: #553000">str</span> = <span style="color: #70226C">null</span>;
     * <span style="color: #CC4747">assertException</span>(NullPointerException.<span style="color: #70226C">class</span>, () <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> <span style="color: #553000">str</span>.toString());
     * 
     * <span style="color: #CC4747">assertException</span>(NullPointerException.<span style="color: #70226C">class</span>, () <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> <span style="color: #553000">str</span>.toString()).<span style="color: #994747">handle</span>(<span style="color: #553000">cause</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     assertContains(<span style="color: #553000">cause</span>.getMessage(), ...);
     * });
     * </pre>
     * @param <CAUSE> The type of expected cause exception. 
     * @param exceptionType The expected exception type. (NotNull)
     * @param noArgInLambda The callback for calling methods that should throw the exception. (NotNull)
     * @return The after object that has handler of expected cause for chain call. (NotNull) 
     */
    protected <CAUSE extends Throwable> ExceptionExpectationAfter<CAUSE> assertException(Class<CAUSE> exceptionType,
            ExceptionExaminer noArgInLambda) {
        assertNotNull(exceptionType);
        final String expected = exceptionType.getSimpleName();
        Throwable cause = null;
        try {
            noArgInLambda.examine();
        } catch (Throwable e) {
            cause = e;
            final Class<? extends Throwable> causeClass = cause.getClass();
            final String exp = buildExceptionSimpleExp(cause);
            if (!exceptionType.isAssignableFrom(causeClass)) {
                final String actual = causeClass.getSimpleName();
                log("*Different exception, expected: {} but...", exceptionType.getName(), cause);
                fail("*Different exception, expected: " + expected + " but: " + actual + " => " + exp);
            } else {
                log("expected: " + exp);
            }
        }
        if (cause == null) {
            fail("*No exception, expected: " + expected);
        }
        @SuppressWarnings("unchecked")
        final CAUSE castCause = (CAUSE) cause;
        return new ExceptionExpectationAfter<CAUSE>(castCause);
    }

    private String buildExceptionSimpleExp(Throwable cause) {
        final StringBuilder sb = new StringBuilder();
        final String firstMsg = cause.getMessage();
        boolean line = firstMsg != null && firstMsg.contains(ln());
        sb.append("(").append(cause.getClass().getSimpleName()).append(")").append(firstMsg);
        final Throwable secondCause = cause.getCause();
        if (secondCause != null) {
            final String secondMsg = secondCause.getMessage();
            line = line || secondMsg != null && secondMsg.contains(ln());
            sb.append(line ? ln() : " / ");
            sb.append("(").append(secondCause.getClass().getSimpleName()).append(")").append(secondMsg);
            final Throwable thirdCause = secondCause.getCause();
            if (thirdCause != null) {
                final String thirdMsg = thirdCause.getMessage();
                line = line || thirdMsg != null && thirdMsg.contains(ln());
                sb.append(line ? ln() : " / ");
                sb.append("(").append(thirdCause.getClass().getSimpleName()).append(")").append(thirdMsg);
            }
        }
        final String whole = sb.toString();
        return (whole.contains(ln()) ? ln() : "") + whole;
    }

    // -----------------------------------------------------
    //                                                 Order
    //                                                 -----
    /**
     * Assert that the bean list is ordered as expected specification.
     * <pre>
     * assertOrder(memberList, orderBy -&gt; {
     *     orderBy.desc(mb -&gt; mb.getBirthdate()).asc(mb -&gt; mb.getMemberId());
     * });
     * </pre>
     * @param <BEAN> The type of element of ordered list.
     * @param beanList The list of bean. (NotNull)
     * @param oneArgLambda The callback for order specification. (NotNull)
     */
    protected <BEAN> void assertOrder(List<BEAN> beanList, Consumer<ExpectedBeanOrderBy<BEAN>> oneArgLambda) {
        assertNotNull(beanList);
        assertNotNull(oneArgLambda);
        assertHasAnyElement(beanList);
        new BeanOrderValidator<BEAN>(oneArgLambda).validateOrder(beanList, vio -> {
            fail("[Order Failure] " + vio); // for now
        });
    }

    // -----------------------------------------------------
    //                                             Mark Here
    //                                             ---------
    /**
     * Mark here to assert that it goes through the road.
     * <pre>
     * memberBhv.selectCursor(<span style="color: #553000">cb</span> -&gt; ..., entity -&gt; {
     *     <span style="color: #FD4747">markHere</span>("cursor");
     * });
     * <span style="color: #994747">assertMarked</span>("cursor"); <span style="color: #3F7E5E">// the callback called</span>
     * </pre>
     * @param mark The your original mark expression as string. (NotNull)
     */
    protected void markHere(String mark) {
        assertNotNull(mark);
        xgetMarkHereManager().mark(mark);
    }

    /**
     * Assert the mark is marked. (found in existing marks)
     * <pre>
     * memberBhv.selectCursor(<span style="color: #553000">cb</span> -&gt; ..., entity -&gt; {
     *     <span style="color: #994747">markHere</span>("cursor");
     * });
     * <span style="color: #FD4747">assertMarked</span>("cursor"); <span style="color: #3F7E5E">// the callback called</span>
     * </pre>
     * @param mark The your original mark expression as string. (NotNull)
     */
    protected void assertMarked(String mark) {
        assertNotNull(mark);
        xgetMarkHereManager().assertMarked(mark);
    }

    /**
     * Is the mark marked? (found the mark in existing marks?)
     * @param mark The your original mark expression as string. (NotNull)
     * @return The determination, true or false.
     */
    protected boolean isMarked(String mark) {
        assertNotNull(mark);
        return xgetMarkHereManager().isMarked(mark);
    }

    protected MarkHereManager xgetMarkHereManager() {
        if (_xmarkHereManager == null) {
            _xmarkHereManager = new MarkHereManager();
        }
        return _xmarkHereManager;
    }

    protected boolean xhasMarkHereManager() {
        return _xmarkHereManager != null;
    }

    protected void xdestroyMarkHereManager() {
        _xmarkHereManager = null;
    }

    protected void xclearMark() {
        if (xhasMarkHereManager()) {
            xgetMarkHereManager().checkNonAssertedMark();
            xgetMarkHereManager().clearMarkMap();
            xdestroyMarkHereManager();
        }
    }

    // ===================================================================================
    //                                                                      Logging Helper
    //                                                                      ==============
    /**
     * Log the messages. <br>
     * If you set an exception object to the last element, it shows stack traces.
     * <pre>
     * Member member = ...;
     * <span style="color: #FD4747">log</span>(member.getMemberName(), member.getBirthdate());
     * <span style="color: #3F7E5E">// -&gt; Stojkovic, 1965/03/03</span>
     *
     * Exception e = ...;
     * <span style="color: #FD4747">log</span>(member.getMemberName(), member.getBirthdate(), e);
     * <span style="color: #3F7E5E">// -&gt; Stojkovic, 1965/03/03</span>
     * <span style="color: #3F7E5E">//  (and stack traces)</span>
     * </pre>
     * @param msgs The array of messages. (NotNull)
     */
    protected void log(Object... msgs) {
        if (msgs == null) {
            throw new IllegalArgumentException("The argument 'msgs' should not be null.");
        }
        Throwable cause = null;
        final int arrayLength = msgs.length;
        if (arrayLength > 0) {
            final Object lastElement = msgs[arrayLength - 1];
            if (lastElement instanceof Throwable) {
                cause = (Throwable) lastElement;
            }
        }
        final StringBuilder sb = new StringBuilder();
        int index = 0;
        int skipCount = 0;
        for (Object msg : msgs) {
            if (index == arrayLength - 1 && cause != null) { // last loop and it is cause
                break;
            }
            if (skipCount > 0) { // already resolved as variable
                --skipCount; // until count zero
                continue;
            }
            if (sb.length() > 0) {
                sb.append(", ");
            }
            final String appended;
            if (msg instanceof Timestamp) {
                appended = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(msg);
            } else if (msg instanceof Date) {
                appended = new SimpleDateFormat("yyyy/MM/dd").format(msg);
            } else {
                String strMsg = msg != null ? msg.toString() : null;
                int nextIndex = index + 1;
                skipCount = 0; // just in case
                while (strMsg != null && strMsg.contains("{}")) {
                    if (arrayLength <= nextIndex) {
                        break;
                    }
                    final Object nextObj = msgs[nextIndex];
                    final String replacement;
                    if (nextObj != null) {
                        // escape two special characters of replaceFirst() to avoid illegal group reference
                        replacement = Srl.replace(Srl.replace(nextObj.toString(), "\\", "\\\\"), "$", "\\$");
                    } else {
                        replacement = "null";
                    }
                    strMsg = strMsg.replaceFirst("\\{\\}", replacement);
                    ++skipCount;
                    ++nextIndex;
                }
                appended = strMsg;
            }
            sb.append(appended);
            ++index;
        }
        final String msg = sb.toString();
        if (_xreservedTitle != null) {
            _xlogger.debug("");
            _xlogger.debug(_xreservedTitle);
            _xreservedTitle = null;
        }
        if (cause != null) {
            _xlogger.debug(msg, cause);
        } else {
            _xlogger.debug(msg);
        }
        // see comment for logger definition for the detail
        //_xlogger.log(PlainTestCase.class.getName(), Level.DEBUG, msg, cause);
    }

    // ===================================================================================
    //                                                                       System Helper
    //                                                                       =============
    /**
     * Get the line separator. (LF fixedly)
     * @return The string of the line separator. (NotNull)
     */
    protected String ln() {
        return "\n";
    }

    // ===================================================================================
    //                                                                          Filesystem
    //                                                                          ==========
    /**
     * Get the directory object of the (application or Eclipse) project.
     * @return The file object of the directory. (NotNull)
     */
    protected File getProjectDir() { // you can override
        final Set<String> markSet = defineProjectDirMarkSet();
        for (File dir = getTestCaseBuildDir(); dir != null; dir = dir.getParentFile()) {
            if (dir.isDirectory()) {
                if (Arrays.stream(dir.listFiles()).anyMatch(file -> markSet.contains(file.getName()))) {
                    return dir;
                }
            }
        }
        throw new IllegalStateException("Not found the project dir marks: " + markSet);
    }

    /**
     * Define the marks of the (application or Eclipse) project.
     * @return the set of mark file name for the project. (NotNull)
     */
    protected Set<String> defineProjectDirMarkSet() {
        return new HashSet<>(Arrays.asList("build.xml", "pom.xml", "build.gradle", ".project", ".idea"));
    }

    /**
     * Get the directory object of the build for the test case. (default: target/test-classes)
     * @return The file object of the directory. (NotNull)
     */
    protected File getTestCaseBuildDir() {
        return DfResourceUtil.getBuildDir(getClass()); // target/test-classes
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    protected Logger xgetLogger() {
        return _xlogger;
    }

    protected String xgetReservedTitle() {
        return _xreservedTitle;
    }

    protected void xsetReservedTitle(String reservedTitle) {
        _xreservedTitle = reservedTitle;
    }
}
