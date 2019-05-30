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
import java.util.List;
import java.util.Map;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.impl.StandardColorBox;
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
 * @author kotanomura
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String maxStr = null;

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    if (maxStr == null || maxStr.length() < strContent.length()) {
                        maxStr = strContent;
                    }
                }
            }
        }

        log(maxStr);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String maxStr = null;
        String minStr = null;

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    if (maxStr == null || maxStr.length() < strContent.length()) {
                        maxStr = strContent;
                        log(maxStr);
                    }

                    if (minStr == null || minStr.length() > strContent.length()) {
                        minStr = strContent;
                        log(minStr);
                    }
                }
            }
        }

        int result = maxStr.length() - minStr.length();
        log(result);

    }

    /**
     * Which value (toString() if non-string) has second-max legnth in color-boxes? (without sort)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String firstMax = null;
        String secondMax = null;

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                String current = content != null ? content.toString() : null;
                int currentLength = current != null ? current.length() : 0;
                if (firstMax == null || firstMax.length() < currentLength) {
                    secondMax = firstMax;
                    firstMax = current;
                } else if (secondMax == null || secondMax.length() < currentLength) {
                    secondMax = current;
                }
            }
        }

        log(secondMax);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int sum = 0;

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();

            for (BoxSpace space : spaceList) {
                Object content = space.getContent();

                if (content instanceof String) {
                    String strContent = content.toString();
                    sum += strContent.length();
                }
            }
        }

        log(sum);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String maxColorName = null;

        for (ColorBox colorBox : colorBoxList) {
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();

            if (maxColorName == null || maxColorName.length() < colorName.length()) {
                maxColorName = colorName;
            }
        }

        log(maxColorName);
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

        String matchColorName = null;
        String startWord = "Water";

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = content.toString();
                    if (matchColorName == null || strContent.startsWith(startWord)) {
                        matchColorName = colorBox.getColor().toString();
                    }
                }
            }
        }

        log(matchColorName != null ? matchColorName : "Not found match color name");
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String matchColorName = null;
        String endWord = "front";

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = content.toString();
                    if (matchColorName == null || strContent.endsWith(endWord)) {
                        matchColorName = colorBox.getColor().toString();
                    }
                }
            }
        }

        log(matchColorName != null ? matchColorName : "Not found match color name");
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

        int startWordNum = 0;
        String targetWord = "front";

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = content.toString();
                    if (strContent.endsWith(targetWord)) {
                        startWordNum = strContent.indexOf(targetWord) + 1;
                    }
                }
            }
        }

        log(startWordNum + "文字目");
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (あなたのカラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int startWordNum = 0;
        String targetWord = "ど";

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = content.toString();
                    if (strContent.contains(targetWord)) {
                        startWordNum = strContent.lastIndexOf(targetWord) + 1;
                    }
                }
            }
        }

        log(startWordNum + "文字目");
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

        String targetWord = "front";
        String firstWord = null;

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = content.toString();
                    if (firstWord == null || strContent.endsWith(targetWord)) {
                        firstWord = strContent.substring(0, 1);
                    }
                }
            }
        }

        log(firstWord + "から始まるぜ");
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String targetWord = "Water";
        String endWord = null;

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = content.toString();
                    log(strContent);
                    if (endWord == null || strContent.startsWith(targetWord)) {
                        endWord = strContent.substring(strContent.length() - 1);
                    }
                }
            }
        }

        log(endWord + "で終わるぜ");
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

        String targetWord = "o";

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = content.toString();
                    if (strContent.contains(targetWord)) {
                        String replacedContent = strContent.replace(targetWord, "");
                        int wordCount = replacedContent.length();
                        log("'{}'から'{}'をremove -> '{}'でwordCount is '{}'", strContent, targetWord, replacedContent, wordCount);
                    }
                }
            }
        }
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String targetWord = "/";

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof File) {
                    String path = content.toString();
                    String replacedPath = path.replace(targetWord, "\\");
                    log(replacedPath);
                }
            }
        }
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
        List<YourPrivateRoom.DevilBox> dbs = new ArrayList<>();
        int lengthAllDevilBox = 0;

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof YourPrivateRoom.DevilBox) {
                    dbs.add((YourPrivateRoom.DevilBox) content);
                }
            }
        }

        for (YourPrivateRoom.DevilBox db : dbs) {
            db.wakeUp();
            db.allowMe();
            db.open();

            String dbText;

            try {
                dbText = db.getText();
            } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
                dbText = "";
            }

            lengthAllDevilBox += dbText.length();
            log(dbText);
        }

        log("合計の長さは{}", lengthAllDevilBox);

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
                if (content instanceof java.util.Map) {
                    StringBuilder result = new StringBuilder("map:{ ");
                    for (Object e : ((Map) content).entrySet()) {
                        result.append(e.toString()).append(" ; ");
                    }
                    result.append(" }");
                    String r = result.toString().replace("=", " = ");
                    log(r);
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
                if (content instanceof java.util.Map) {
                    Map map = (Map) content;
                    log(get_mapString(map));
                }
            }
        }
    }

    private String get_mapString(java.util.Map map) {
        StringBuilder result = new StringBuilder("map:{ ");

        for (Object k : map.keySet()) {
            Object v = map.get(k);
            String vStr;

            if (v instanceof Map) {
                Map vMap = (Map) v;
                vStr = get_mapString(vMap);
            } else {
                vStr = v.toString();
            }

            result.append(k.toString()).append(" = ").append(vStr + " ; ");

        }

        return result.append("}").toString();
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
            if (colorBox.getColor().getColorName().equals("white")) {
                BoxSpace upperSpace = ((StandardColorBox) colorBox).getUpperSpace();
                Object content = upperSpace.getContent();
                if (content instanceof YourPrivateRoom.SecretBox) {
                    String s = ((YourPrivateRoom.SecretBox) content).getText();
                    log(s);
                }
            }
        }
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (ColorBox cb : colorBoxList) {
            if (cb.getColor().getColorName().equals("white")) {
                BoxSpace middleSpace = ((StandardColorBox) cb).getMiddleSpace();
                BoxSpace lowerSpace = ((StandardColorBox) cb).getLowerSpace();
                Object middleSpaceContent = middleSpace.getContent();
                Object lowerSpaceContent = lowerSpace.getContent();

                if (middleSpaceContent instanceof YourPrivateRoom.SecretBox) {
                    String ms = ((YourPrivateRoom.SecretBox) middleSpaceContent).getText();
                    log(ms);
                }

                if (lowerSpaceContent instanceof YourPrivateRoom.SecretBox) {
                    String ls = ((YourPrivateRoom.SecretBox) lowerSpaceContent).getText();
                    log(ls);
                }
            }
        }
    }
}
