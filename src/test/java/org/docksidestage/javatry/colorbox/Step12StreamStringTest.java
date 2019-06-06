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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
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

        Optional<ColorBox> first = colorBoxList.stream()
                .findFirst();
        first.ifPresentOrElse(box -> {
            List<BoxSpace> spaceList = box.getSpaceList();
            log(spaceList);
        }, () -> {
            log("NOT FOUND");
        });

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
                .flatMap(box -> box.getSpaceList()
                        .stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String)
                .map(content -> ((String) content))
                .reduce("", (String s1, String s2) -> {
                    return s1.length() > s2.length() ? s1 : s2;
                });
        log(answer);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Comparator<String> compByLen = Comparator.comparing(String::length);
        String maxStr = stringStream(colorBoxList).max(compByLen)
                .orElse(null);
        String minStr = stringStream(colorBoxList).min(compByLen)
                .orElse(null);
        log(maxStr.length() - minStr.length());
    }

    private Stream<String> stringStream(List<ColorBox> colorBoxList) {
        return colorBoxList.stream()
                .flatMap(box -> box.getSpaceList()
                        .stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String)
                .map(content -> ((String) content));
    }

    // has small #adjustmemts from ClassicStringTest
    //  o sort allowed in Stream
    /**
     * Which value (toString() if non-string) has second-max legnth in color-boxes? (sort allowed in Stream)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (Streamでのソートありで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxStr = stringStream(colorBoxList).max(Comparator.comparing(String::length))
                .orElse(null);
        String secondMaxStr = stringStream(colorBoxList).filter(contentStr -> !contentStr.equals(maxStr))
                .max(Comparator.comparing(String::length))
                .orElse(null);
        log(secondMaxStr);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String calcLenSum = stringStream(colorBoxList).reduce("", (String s1, String s2) -> s1 + s2);
        log(calcLenSum.length());
        int sum = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList()
                        .stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof String)
                .mapToInt(content -> ((String) content).length())
                .sum();
        log(sum);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Optional<String> optMaxLenColor = colorBoxList.stream()
                .map(box -> box.getColor()
                        .getColorName())
                .max(Comparator.comparingInt(String::length));
        String maxLenColor = optMaxLenColor.orElseThrow(() -> {
            throw new IllegalStateException("NOT FOUND");
        });
        log(maxLenColor);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        String target = "Water";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        stringStream(colorBoxList);
        colorBoxList.stream()
                .filter(box -> {
                    List<String> matchList = box.getSpaceList()
                            .stream()
                            .map(space -> space.getContent())
                            .filter(content -> content instanceof String)
                            .map(content -> ((String) content))
                            .filter(s -> s.startsWith(target))
                            .collect(Collectors.toList());
                    return !matchList.isEmpty();
                })
                .map(box -> box.getColor())
                .forEach(boxColor -> log(boxColor.getColorName()));
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        String target = "front";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .filter(box -> {
                    List<String> matchList = box.getSpaceList()
                            .stream()
                            .map(space -> space.getContent())
                            .filter(content -> content instanceof String)
                            .map(content -> ((String) content))
                            .filter(s -> s.endsWith(target))
                            .collect(Collectors.toList());
                    return !matchList.isEmpty();
                })
                .map(box -> box.getColor())
                .forEach(boxColor -> log(boxColor.getColorName()));
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        String target = "front";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        stringStream(colorBoxList).filter(contentStr -> contentStr.endsWith(target))
                .forEach(contentStr -> log(contentStr + " : " + (contentStr.lastIndexOf(target) + 1)));
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        String target = "ど";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        stringStream(colorBoxList).filter(contentStr -> contentStr.contains(target))
                .forEach(contentStr -> log(contentStr + " : " + (contentStr.lastIndexOf(target) + 1) + " 文字目"));

    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        String target = "front";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        stringStream(colorBoxList).filter(contentStr -> contentStr.endsWith(target))
                .forEach(contentStr -> log(contentStr + " : " + contentStr.substring(0, 1)));
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        String target = "Water";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        stringStream(colorBoxList).filter(contentStr -> contentStr.startsWith(target))
                .forEach(contentStr -> log(contentStr + " : " + contentStr.substring(contentStr.length() - 1)));
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        String target = "o";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        stringStream(colorBoxList).filter(strContent -> strContent.contains(target))
                .map(strContent -> strContent.replace(target, ""))
                .forEach(strContent -> log(strContent + " : " + strContent.length()));
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        String target = "/";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(box -> box.getSpaceList()
                        .stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof File)
                .map(content -> content.toString().replace(target, "\\"))
                .forEach(strContent -> log(strContent));
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
        int dbTextLenSum = colorBoxList.stream()
                .flatMap(box -> box.getSpaceList()
                        .stream())
                .map(space -> space.getContent())
                .filter(content -> content instanceof YourPrivateRoom.DevilBox)
                .map(content -> ((YourPrivateRoom.DevilBox) content))
                .map(db -> {
                    db.wakeUp();
                    db.allowMe();
                    db.open();
                    try {
                        return db.getText();
                    } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
                        return "";
                    }
                })
                .mapToInt(dbText -> dbText.length())
                .sum();
        log(dbTextLenSum);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList()
                        .stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof Map)
                .map(mapContent -> {
                    StringBuilder mapStr = new StringBuilder("map:{ ");
                    Set<String> entries = ((Map) mapContent).entrySet();
                    entries.forEach(e -> mapStr.append(e).append(" ; "));
                    return mapStr.append("}").toString().replace("=", " = ");
                })
                .forEach(mapStrContent -> log(mapStrContent));
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList()
                        .stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof Map)
                .map(mapContent -> get_mapStringStream((Map) mapContent))
                .forEach(mapStrContent -> log(mapStrContent));
    }

    private String get_mapStringStream(Map mapContent) {
        StringBuilder resultMap = new StringBuilder("map:{ ");
        Set<String> keys = mapContent.keySet();
        keys.stream()
                .map(k -> {
                    resultMap.append(k);
                    return mapContent.get(k);
                }).map(v -> {
                    if (v instanceof Map) return get_mapStringStream((Map) v);
                    else return v.toString();
                }).forEach(vStr -> resultMap.append(" = ").append(vStr).append(" ; "));
        return resultMap.append("}").toString();
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