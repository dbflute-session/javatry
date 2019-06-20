JavaTry for education
======================
trial project of Java programming for education (with H2)


# Overview

This is the hands-on for Java beginners who only red Java books or wrote a little Java in business.  

Not instructional text so enhance your Java skill voluntarily by hands-on style.  

o Java basic syntax  
o basic programming skill, code reading skill  
o Java8 essence: Stream API, Optional  
o Framework essence: DI Container, Web Framework, O/R Mapper  
o also the ability to think

```
/**
 * What string is sea variable at the method end? <br>
 * (メソッド終了時の変数 sea の中身は？)
 */
public void test_variable_reassigned_basic() {
    String sea = "mystic";
    String land = "oneman";
    sea = land;
    land = land + "'s dreams";
    log(sea); // your answer? => 
}
```

```

/**
 * What is color name length of first color-box? <br>
 * (最初のカラーボックスの色の名前の文字数は？)
 */
public void test_length_basic() {
    List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
    String answer = colorBoxList.stream()
            .findFirst()
            .map(colorBox -> colorBox.getColor().getColorName())
            .map(colorName -> colorName.length() + " (" + colorName + ")")
            .orElse("*not found");
    log(answer);
}
```


# How-to
## set up enrivonment

1. fork this javatry project to your account

2. git clone your forked javatry project

3. open by IDE  
 o If Eclipse, menu's "File - Import..." and "Existing Projects into Workspace"  
 o If IntelliJ, menu's "Open" (to keep existing ".idea" directory, so use "Open" instead of "Import")  

4. confirm that compile works  
 o run Step01VariableTest.java's test_variable_basic() as trial    
  -> If Eclipse with Quick JUnit, put cursor in test method and control+0

5. set up special completion templates (not required)  
 o If Eclipse, "Preferences" and "Java - Editor - Templates", select all templates and remove them all and "Import..." and select "etc/tools/eclipse/java-editor-templates.xml" and apply  
 o If IntelliJ, sea: http://dbflute.seasar.org/ja/manual/topic/friends/intellij/index.html#dbflutelivetemplate

## do hands-on

1. open Step01VariableTest.java of org.docksidestage.javatry in src/test/java

2. read JavaDoc and operate hands-on exercises

```
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
```

※use todo comment not to forget your pendings if you skip exercise  
(you can complement by _todo if special completion templates)
    
```
// TODO jflute pending because I don't know how to ... (2019/06/14)
```


# やり方 (Japanese here)

## 環境構築
1. javatryプロジェクトを自分のアカウントにフォーク(fork)

2. フォークしたjavatryプロジェクトをgit clone (clone方法は任意)

3. IDEで開く  
 o Eclipseなら、メニューの File - Import... で Existing Projects into Workspace  
 o IntelliJなら、Open (.ideaをキープするために Import ではなく Open を使うこと推奨)  

4. コンパイルされていることを確認  
 o 試しに Step01VariableTest.java の test_variable_basic() のテストを実行    
  -> Eclipse で Quick JUnit があれば、テストメソッド内にカーソルを置いて control+0

5. 特製の補完テンプレートを設定 (任意)  
 o Eclipseなら、Preferences の Java - Editor - Templates にて、一度すべてを選択して Remove して空っぽにしてから、Import... で etc/tools/eclipse/java-editor-templates.xml を選択して適用  
 o IntelliJなら、こちらを参考に: http://dbflute.seasar.org/ja/manual/topic/friends/intellij/index.html#dbflutelivetemplate

## ハンズオン実施
1. src/test/java の org.docksidestage.javatry の Step01VariableTest.java を開く

2. それぞれの Step ごとに、JavaDoc を読んで実施していく

```
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
```

※飛ばして先に進む場合は、後で忘れてしまわないようにtodoコメントを入れましょう  
(補完テンプレートが入っていれば、_todo で補完できます)
    
```
// TODO jflute 何々のやり方がわからなくて時間がかかりそうなのでひとまず先に進む (2019/06/14)
```


# License
Apache License 2.0


# Special Thanks
supported by U-NEXT
