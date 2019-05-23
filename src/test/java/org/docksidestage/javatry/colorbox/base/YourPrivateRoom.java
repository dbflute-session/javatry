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
package org.docksidestage.javatry.colorbox.base;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.impl.CompactColorBox;
import org.docksidestage.bizfw.colorbox.impl.DoorColorBox;
import org.docksidestage.bizfw.colorbox.impl.StandardColorBox;
import org.docksidestage.bizfw.colorbox.size.BoxSize;
import org.docksidestage.bizfw.colorbox.space.DoorBoxSpace;

/**
 * @author jflute
 */
public class YourPrivateRoom {

    // ===================================================================================
    //                                                                         Entry Point
    //                                                                         ===========
    public List<ColorBox> getColorBoxList() {
        List<ColorBox> colorBoxList = new ArrayList<>();
        colorBoxList.add(makeFirstColorBox());
        colorBoxList.add(makeSecondColorBox());
        colorBoxList.add(makeThirdColorBox());
        colorBoxList.add(makeFourthColorBox());
        colorBoxList.add(makeFifthColorBox());
        colorBoxList.add(makeSixthColorBox());
        colorBoxList.add(makeSeventhColorBox());
        colorBoxList.add(makeEighthColorBox());
        colorBoxList.add(makeNinthColorBox());
        colorBoxList.add(makeTenthColorBox());
        return colorBoxList;
    }

    // ===================================================================================
    //                                                                              Making
    //                                                                              ======
    // -----------------------------------------------------
    //                                         First - Third
    //                                         -------------
    private StandardColorBox makeFirstColorBox() {
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("green"), new BoxSize(40, 50, 30));
        colorBox.getUpperSpace().setContent("おるぐどっくさいどすてーじ");
        colorBox.getMiddleSpace().setContent(null);
        colorBox.getLowerSpace().setContent(3);
        return colorBox;
    }

    private StandardColorBox makeSecondColorBox() {
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("red"), new BoxSize(50, 30, 40));
        colorBox.getUpperSpace().setContent(806);
        colorBox.getMiddleSpace().setContent(54L);
        colorBox.getLowerSpace().setContent("Waterfront");
        return colorBox;
    }

    private CompactColorBox makeThirdColorBox() {
        CompactColorBox colorBox = new CompactColorBox(new BoxColor("blue"), new BoxSize(50, 30, 40));
        colorBox.getUpperSpace().setContent(new File("/tmp/javatry/docksidestage.txt"));
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("1-Day Passport", 7400); // these are when 2019/04/15
        map.put("Starlight Passport", 5400);
        map.put("After 6 Passport", 4200);
        map.put("2-Day Passport", 13200);
        map.put("3-Day Magic Passport", 17800);
        map.put("4-Day Magic Passport", 22400);
        map.put("Land Annual Passport", 61000);
        map.put("Sea Annual Passport", 61000);
        // trap for plural top
        //map.put("2-Park Annual Passport", 89000);
        map.put("Group Passport", 6700);
        colorBox.getLowerSpace().setContent(map);
        return colorBox;
    }

    // -----------------------------------------------------
    //                                        Fourth - Sixth
    //                                        --------------
    private StandardColorBox makeFourthColorBox() {
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("yellow"), new BoxSize(40, 50, 30));
        colorBox.getUpperSpace().setContent(LocalDateTime.of(1983, 4, 15, 23, 59, 59));
        colorBox.getMiddleSpace().setContent(LocalDate.of(2001, 9, 4));
        List<Number> decimalList = new ArrayList<Number>();
        decimalList.add(new BigDecimal(2));
        decimalList.add(new BigDecimal("3.14159"));
        decimalList.add(new BigDecimal("4.753"));
        decimalList.add(new BigDecimal("2.0"));
        decimalList.add(new BigDecimal("1.4"));
        decimalList.add(24.8);
        decimalList.add(new BigInteger("1234567890"));
        colorBox.getLowerSpace().setContent(decimalList);
        return colorBox;
    }

    private StandardColorBox makeFifthColorBox() {
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("white"), new BoxSize(30, 40, 50));
        SecretBox upperBox = asPrivate("map:{ dockside = over ; hangar = mystic ; broadway = bbb }"); // simple map
        colorBox.getUpperSpace().setContent(upperBox);

        SecretBox middleBox = // second value is nested
                asPrivate("map:{ dockside = over ; hangar = map:{ mystic = performance ; shadow = musical } ; broadway = bbb }");
        colorBox.getMiddleSpace().setContent(middleBox);

        SecretBox lowerBox = // third value is nested
                asPrivate("map:{ dockside = over ; hangar = mystic ; broadway = map:{ encore! = musical ; bbb = review } }");
        colorBox.getLowerSpace().setContent(lowerBox);
        return colorBox;
    }

    private StandardColorBox makeSixthColorBox() {
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("pink"), new BoxSize(50, 40, 30));
        colorBox.getUpperSpace().setContent(asDevil("high tower"));
        colorBox.getMiddleSpace().setContent(asDevil(null));
        colorBox.getLowerSpace().setContent(asDevil("hotel"));
        return colorBox;
    }

    // -----------------------------------------------------
    //                                         Seventh - ...
    //                                         -------------
    private StandardColorBox makeSeventhColorBox() {
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("purple"), new BoxSize(50, 30, 40));
        colorBox.getUpperSpace().setContent(true);
        {
            Map<String, Object> middleMap = new LinkedHashMap<String, Object>();
            Map<String, Object> seaMap = new LinkedHashMap<String, Object>();
            seaMap.put("dockside", Arrays.asList("over", "table", "hello"));
            seaMap.put("hanger", Arrays.asList("mystic", "shadow", "mirage"));
            Map<String, String> harborMap = new LinkedHashMap<String, String>();
            harborMap.put("spring", "fashion"); // these are when 2018
            harborMap.put("summer", "pirates");
            harborMap.put("autumn", "vi");
            harborMap.put("winter", "jazz");
            seaMap.put("harbor", harborMap);
            middleMap.put("sea", seaMap);
            Map<String, List<String>> landMap = new LinkedHashMap<String, List<String>>();
            landMap.put("orleans", Arrays.asList("oh", "party"));
            landMap.put("showbase", Arrays.asList("oneman"));
            middleMap.put("land", landMap);
            colorBox.getMiddleSpace().setContent(middleMap);
        }
        {
            Map<String, Object> lowerMap = new LinkedHashMap<String, Object>();
            lowerMap.put("Small Coin Locker", 300); // these are when 2019/04/15
            lowerMap.put("Resort Line", 250);
            lowerMap.put("Cinema Piari", "1800"); // trap
            lowerMap.put("Middle Coin Locker", "4O0"); // more trap
            colorBox.getLowerSpace().setContent(lowerMap);
        }
        return colorBox;
    }

    private StandardColorBox makeEighthColorBox() { // duplicate color here
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("yellow"), new BoxSize(30, 50, 40));
        Set<String> set = new HashSet<String>();
        set.add("2019/04/22");
        set.add("2O19/04/22"); // super trap
        colorBox.getUpperSpace().setContent(set);
        colorBox.getMiddleSpace().setContent(new BittersweetMemorableException("nullsidestage", doyoulikeNullPointer()));
        colorBox.getLowerSpace().setContent((FavoriteProvider) () -> "mystic");
        return colorBox;
    }

    private NullPointerException doyoulikeNullPointer() {
        return new NullPointerException("Oh, yes");
    }

    private CompactColorBox makeNinthColorBox() {
        CompactColorBox colorBox = new CompactColorBox(new BoxColor("beige"), new BoxSize(50, 40, 30));
        colorBox.getUpperSpace().setContent(54.3); // trap
        List<BoxedResort> resortList = new ArrayList<>();
        resortList.add(new BoxedResort("nullhama", null));
        resortList.add(new BoxedResort("nullsea", new BoxedPark("sea", null)));
        resortList.add(new BoxedResort("nullside", new BoxedPark("sea", new BoxedStage("dockside", null))));
        resortList.add(new BoxedResort("maihama", new BoxedPark("sea", new BoxedStage("dockside", "dream cruise"))));
        colorBox.getLowerSpace().setContent(resortList);
        return colorBox;
    }

    private DoorColorBox makeTenthColorBox() {
        DoorColorBox colorBox = new DoorColorBox(new BoxColor("grey"), new BoxSize(50, 40, 30));
        for (DoorBoxSpace boxSpace : colorBox.getDoorSpaceList()) {
            boxSpace.openTheDoor();
        }
        colorBox.getUpperSpace().setContent(LocalTime.of(12, 48, 24));
        colorBox.getMiddleSpace().setContent(null); // spare
        colorBox.getLowerSpace().setContent(null); // spare
        for (DoorBoxSpace boxSpace : colorBox.getDoorSpaceList()) {
            boxSpace.closeTheDoor();
        }
        return colorBox;
    }

    // ===================================================================================
    //                                                                              Secret
    //                                                                              ======
    private SecretBox asPrivate(String mapString) {
        return new SecretBox(mapString);
    }

    public static class SecretBox {

        private final String text;

        public SecretBox(String text) {
            if (text == null) {
                throw new IllegalArgumentException("The argument 'text' should not be null.");
            }
            this.text = text;
        }

        /**
         * Get the text in the box.
         * @return The string of text. (NotNull)
         */
        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "{secret}";
        }
    }

    // ===================================================================================
    //                                                                               Devil
    //                                                                               =====
    private DevilBox asDevil(String mapString) {
        return new DevilBox(mapString);
    }

    public static class DevilBox {

        private final String text; // null allowed
        private boolean wakeup;
        private boolean allowed;
        private boolean open;

        public DevilBox(String text) {
            this.text = text;
        }

        public void wakeUp() {
            wakeup = true;
        }

        public void allowMe() {
            if (!wakeup) {
                String msg = "The devil is sleep now so call wakeUp() method before calling this.";
                throw new IllegalStateException(msg);
            }
            allowed = true;
        }

        public void open() {
            if (!allowed) {
                String msg = "You should call allowMe() method before calling this.";
                throw new IllegalStateException(msg);
            }
            open = true;
        }

        /**
         * Get the text in the box.
         * @return The string of text. (NotNull: exception if none)
         * @throws DevilBoxTextNotFoundException When the text is null.
         */
        public String getText() {
            if (!open) {
                String msg = "You should call open() method before calling this.";
                throw new IllegalStateException(msg);
            }
            if (text == null) {
                String msg = "Not found the text in the devil, meaning null value.";
                throw new DevilBoxTextNotFoundException(msg);
            }
            return text;
        }

        @Override
        public String toString() {
            return "{Ha ha ha!}";
        }
    }

    public static class DevilBoxTextNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public DevilBoxTextNotFoundException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                           Exception
    //                                                                           =========
    public static class BittersweetMemorableException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public BittersweetMemorableException(String msg) {
            super(msg);
        }

        public BittersweetMemorableException(String msg, Throwable cause) {
            super(msg, cause);
        }

        @Override
        public String toString() {
            return "{bitter...}"; // to be closet
        }
    }

    // ===================================================================================
    //                                                                           Interface
    //                                                                           =========
    @FunctionalInterface
    public static interface FavoriteProvider {

        String justHere();

        default String notHere() {
            throw new BittersweetMemorableException("No no no no no...");
        }
    }

    // ===================================================================================
    //                                                                           Interface
    //                                                                           =========
    public static class BoxedResort {

        private final String region; // not null
        private BoxedPark park; // null allowed

        public BoxedResort(String region, BoxedPark park) {
            this.region = region;
            this.park = park;
        }

        @Override
        public String toString() {
            return "{" + region + ", " + park + "}";
        }

        public String getRegion() {
            return region;
        }

        public Optional<BoxedPark> getPark() {
            return Optional.ofNullable(park);
        }
    }

    public static class BoxedPark {

        private final String theme; // not null
        private BoxedStage stage; // null allowed

        public BoxedPark(String theme, BoxedStage stage) {
            this.theme = theme;
            this.stage = stage;
        }

        @Override
        public String toString() {
            return "{" + theme + ", " + stage + "}";
        }

        public String getTheme() {
            return theme;
        }

        public Optional<BoxedStage> getStage() {
            return Optional.ofNullable(stage);
        }
    }

    public static class BoxedStage {

        private final String showName; // not null
        private String keyword; // null allowed

        public BoxedStage(String showName, String keyword) {
            this.showName = showName;
            this.keyword = keyword;
        }

        @Override
        public String toString() {
            return "{" + showName + ", " + keyword + "}";
        }

        public String getShowName() {
            return showName;
        }

        public String getKeyword() {
            return keyword;
        }
    }
}
