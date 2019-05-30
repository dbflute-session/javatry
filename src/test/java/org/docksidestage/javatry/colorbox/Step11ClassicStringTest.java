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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author fuwai_so
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        ColorBox colorBox = colorBoxList.get(0);
        BoxColor boxColor = colorBox.getColor();
        String colorName = boxColor.getColorName();
        int answer = colorName.length();
        log(answer, colorName); // also show name for visual check
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
//        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
//        colorBoxList.stream()
//                .flatMap(box -> box.getSpaceList().stream())
//                .map(space -> space.getContent())
//                .filter(content -> content instanceof String)
//                .map(content -> ((String) content).length())
//                .map(comparator);
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxStr = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) { // if the content is String type
                    String strContent = (String) content;
                    int currentLength = strContent.length();
                    if (maxStr == null || maxStr.length() < currentLength) {
                        maxStr = strContent;
                    }
                }
            }
        }
        log(maxStr != null ? maxStr : "*Not found string content");
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
//        List<ColorBox> colorBoxList = new ArrayList<>();
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        log(colorBoxList);
        String maxStr = null;
        String minStr = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) { // if the content is String type
                    String strContent = (String) content;
                    int currentLength = strContent.length();
                    if (maxStr == null || maxStr.length() < currentLength) {
                        maxStr = strContent;
                    }
                    if (minStr == null || minStr.length() > currentLength) {
                        minStr = strContent;
                    }
                }
            }
        }

        int different = maxStr.length() - minStr.length();

        log(different);
    }

    /**
     * Which value (toString() if non-string) has second-max legnth in color-boxes? (without sort)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxStr = null;
        String secondMaxStr = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) { // if the content is String type
                    String strContent = (String) content;
                    int currentLength = strContent.length();

                    if (maxStr == null || maxStr.length() < currentLength) {
                        secondMaxStr = maxStr;
                        maxStr = strContent;
                    } else if (secondMaxStr == null || secondMaxStr.length() < currentLength) {
                        secondMaxStr = strContent;
                    }
                }
            }
        }

        log(secondMaxStr);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String stringAddUp = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) { // if the content is String type
                    String strContent = (String) content;
                    stringAddUp += strContent;
                }
            }
        }
        log(stringAddUp.length());
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String longestColor = null;
        for (ColorBox colorBox : colorBoxList) {
            String currentColor = colorBox.getColor().getColorName();
            if (longestColor == null || longestColor.length() < currentColor.length()) {
                longestColor = currentColor;
            }
        }
        log(longestColor);
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
        List<String> targetedColorList = new ArrayList<>();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) { // if the content is String type
                    String strContent = (String) content;
                    if (strContent.startsWith("Water")) {
                        targetedColorList.add(colorBox.getColor().getColorName());
                        break;
                    }
                }
            }
        }
        for (String color : targetedColorList) {
            log(color);
        }
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> targetedColorList = new ArrayList<>();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) { // if the content is String type
                    String strContent = (String) content;
                    if (strContent.endsWith("front")) {
                        targetedColorList.add(colorBox.getColor().getColorName());
                    }
                }
            }
        }
        for (String color : targetedColorList) {
            log(color);
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with "front" of string ending with "front" in color-boxes? <br>
     * (あなたのカラーボックスに入ってる "front" で終わる文字列で、"front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int startingIndex = -1;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) { // if the content is String type
                    String strContent = (String) content;
                    if (strContent.endsWith("front")) { // The latest content which ends with "Water" will be selected
                        startingIndex = strContent.indexOf("front");
                    }
                }
            }
        }
        if (startingIndex == -1) {
            log("Index not found");
        } else {
            log(startingIndex+1);
        }
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (あなたのカラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int startingIndex = -1;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) { // if the content is String type
                    String strContent = (String) content;
                    if (strContent.contains("ど") && strContent.indexOf("ど") != strContent.lastIndexOf("ど")) {
                        startingIndex = strContent.lastIndexOf("ど");
                    }
                }
            }
        }
        if (startingIndex == -1) {
            log("Index not found");
        } else {
            log(startingIndex+1);
        }
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
        char firstChar = 0;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) { // if the content is String type
                    String strContent = (String) content;
                    if (strContent.endsWith("front")) { // The latest content which ends with "Water" will be selected
                        firstChar = strContent.charAt(0);
                    }
                }
            }
        }
        log(firstChar);
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        char lastChar = 0;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) { // if the content is String type
                    String strContent = (String) content;
                    if (strContent.startsWith("Water")) { // The latest content which ends with "Water" will be selected
                        lastChar = strContent.charAt(strContent.length() - 1);
                    }
                }
            }
        }
        log(lastChar);
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
        String replacedString = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) { // if the content is String type
                    String strContent = (String) content;
                    if (strContent.contains("o")) {
                        replacedString = strContent.replace("o","");
                    }
                }
            }
        }
        log(replacedString);
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String replacedPath = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof File) { // if the content is String type
                    String FilePath = ((File) content).getPath();
                    replacedPath = FilePath.replace("/","\\");
                }
            }
        }
        log(replacedPath);
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
        int totalLength = 0;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof YourPrivateRoom.DevilBox) { // if the content is DevilBox
                    ((YourPrivateRoom.DevilBox) content).wakeUp();
                    ((YourPrivateRoom.DevilBox) content).allowMe();
                    ((YourPrivateRoom.DevilBox) content).open();
                    try {
                        totalLength += ((YourPrivateRoom.DevilBox) content).getText().length();
                    } catch (Exception e) {
                        System.out.println("Exception??");
                        log("Exception found");
                    }
                }
            }
        }
        log(totalLength);
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
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if(content instanceof YourPrivateRoom.SecretBox) {
                    String value = ((YourPrivateRoom.SecretBox) content).getText();
                    if(value.split("map").length == 2)
                        log(value);
                } else if(content instanceof String) {
                    String value = (String) content;
                    if(value.split("map").length == 2)
                        log(value);
                }
            }
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if(content instanceof YourPrivateRoom.SecretBox) {
                    String value = ((YourPrivateRoom.SecretBox) content).getText();
                    if(value.split("map").length > 2)
                        log(value);
                } else if(content instanceof String) {
                    String value = (String) content;
                    if(value.split("map").length > 2)
                        log(value);
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            if(!colorBox.getColor().getColorName().equals("white")) {
                continue;
            }
            BoxSpace upperSpace = colorBox.getSpaceList().get(0);
            String content = ((YourPrivateRoom.SecretBox) upperSpace.getContent()).getText();
            content = content.substring(content.indexOf("{")+1);
            content = content.replace("}", "");

            String[] mapPair =  content.split(";");
            Map<String,String> map = new HashMap<>();
            for(String pair : mapPair) {
                String[] entry = pair.split("=");
                map.put(entry[0].trim(), entry[1].trim());
            }
            log(map.toString());
        }
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            if(!colorBox.getColor().getColorName().equals("white")) {
                continue;
            }
            BoxSpace upperSpace = colorBox.getSpaceList().get(1);
            String content = ((YourPrivateRoom.SecretBox) upperSpace.getContent()).getText();
            log(stringToMap(content).toString());
        }
    }

    public Map stringToMap(String input) {
        input = input.substring(input.indexOf("{")+1);
        input = input.replace("}", "");

        String[] mapPair =  input.split(";");
        Map<String,String> map = new HashMap<>();
        for(String pair : mapPair) {
            String[] entry = pair.split("=");
            if (entry[1].contains("{")) {
                log(entry[1]);
                entry[1] = stringToMap(entry[1]).toString();
            }
            map.put(entry[0].trim(), entry[1].trim());
        }
        return map;
    }
}
