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
package org.docksidestage.javatry.colorbox;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, using Stream API you can. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step12StreamStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * What is color name length of first color-box? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = colorBoxList.stream()
                .findFirst()
                .map(colorBox -> colorBox.getColor()) // consciously split as example
                .map(boxColor -> boxColor.getColorName())
                .map(colorName -> {
                    log(colorName); // for visual check
                    return String.valueOf(colorName.length());
                })
                .orElse("not found"); // basically no way because of not-empty list and not-null returns
        log(answer);
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String)
//                one of the method:
                .map(content -> content.toString())
                .max(Comparator.comparingInt(String::length))
                .orElse(null);

//        others method 1:
//                .reduce("",(String s1, String s2) -> { return s1.length() > s2.length() ? s1 : s2; })
//        others method 2:
//                .max(Comparator.comparingInt(content -> ((String) content).length()))
//                .get();

        log(answer);

//        List<ColorBox> whiteBoxList = colorBoxList.stream()
//                .filter(colorBox -> colorBox.getColor().getColorName().equals("white"))
//                .collect(Collectors.toList());
//
//        log(whiteBoxList);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int max = colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String)
                .map(content -> content.toString())
                .max(Comparator.comparingInt(String::length))
                .orElse(null)
                .length();

        int min = colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String)
                .map(content -> content.toString())
                .min(Comparator.comparingInt(String::length))
                .orElse(null)
                .length();

        log(max - min);
    }

    // has small #adjustmemts from ClassicStringTest
    //  o sort allowed in Stream
    /**
     * Which value (toString() if non-string) has second-max legnth in color-boxes? (sort allowed in Stream)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (Streamでのソートありで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
//        String maxString = colorBoxList.stream()
//                .flatMap(box -> box.getSpaceList().stream())
//                .map(space -> space.getContent())
//                .filter(content -> content instanceof String)
//                .map(content -> content.toString())
//                .max(Comparator.comparingInt(String::length))
//                .orElse(null);
//
//        int secondMaxLength = colorBoxList.stream()
//                .flatMap(box -> box.getSpaceList().stream())
//                .map(space -> space.getContent())
//                .filter(content -> content instanceof String)
//                .map(content -> content.toString())
//                .filter(content -> !content.equals(maxString))
//                .max(Comparator.comparingInt(String::length))
//                .orElse(null)
//                .length();

        String secondMax = colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String)
                .map(content -> content.toString())
                .sorted((a, b) -> b.length() - a.length())
                .collect(Collectors.toList()).get(1);

        log(secondMax.length());
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sum = colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> {return content instanceof String;})
                .mapToInt(content -> content.toString().length())
                .sum();

        log(sum);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int maxColorSize = colorBoxList.stream()
                .map(colorBox -> colorBox.getColor()) // consciously split as example
                .map(boxColor -> boxColor.getColorName())
                .max(Comparator.comparingInt(String::length))
                .orElse(null)
                .length();

        log(maxColorSize);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String target = colorBoxList.stream()
                .filter(box -> box.getSpaceList().stream()
                        .anyMatch(space -> space.getContent() instanceof String && space.getContent().toString().startsWith("Water")))
                .map(colorBox -> colorBox.getColor().getColorName()) // consciously split as example
                .collect(Collectors.toList()).get(0);

        log(target);
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String target = colorBoxList.stream()
                .filter(box -> box.getSpaceList().stream()
                        .anyMatch(space -> space.getContent() instanceof String && space.getContent().toString().endsWith("front")))
                .map(colorBox -> colorBox.getColor().getColorName()) // consciously split as example
                .collect(Collectors.toList()).get(0);

        log(target);
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String target = (String) colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String && content.toString().endsWith("front"))
                .collect(Collectors.toList()).get(0);

        log(target.indexOf("front") + 1);
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String target = (String) colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String &&
                        content.toString().contains("ど") &&
                        content.toString().indexOf("ど") != content.toString().lastIndexOf("ど"))
                .collect(Collectors.toList()).get(0);

        log(target.lastIndexOf("ど") + 1);
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String target = (String) colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String && content.toString().endsWith("front"))
                .collect(Collectors.toList()).get(0);

        log(target.charAt(0));
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String target = (String) colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String && content.toString().startsWith("Water"))
                .collect(Collectors.toList()).get(0);

        log(target.charAt(target.length() - 1));
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String target = colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String && content.toString().contains("o"))
                .map(content -> content.toString().replace("o",""))
                .collect(Collectors.toList()).get(0);

        log(target);
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String target = colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof File)
                .map(content -> ((File) content).getPath().replace("/","\\"))
                .collect(Collectors.toList()).get(0);

        log(target);
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int target = colorBoxList.stream()
                .flatMap(box -> box.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof YourPrivateRoom.DevilBox)
                .mapToInt(content -> {
                    ((YourPrivateRoom.DevilBox) content).wakeUp();
                    ((YourPrivateRoom.DevilBox) content).allowMe();
                    ((YourPrivateRoom.DevilBox) content).open();
                    try {
                        return ((YourPrivateRoom.DevilBox) content).getText().length();
                    } catch (Exception e) {
                        return 0;
                    }
                })
                .sum();

        log(target);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // has small #adjustmemts from ClassicStringTest
    //  o comment out because of too difficult to be stream?
    ///**
    // * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_flat() {
    //}
    //
    ///**
    // * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_nested() {
    //}
}
