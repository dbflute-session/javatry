JavaTry for education
======================
trial project of Java programming for education (with H2)


# Overview
This is the hands-on for Java beginners.  
You can try Java learning by hands-on.  

o Java basic syntax  
o Java8 essence: Stream API, Optional    
o basic programming skill  
o Framework essence: DI Container, Web Framework, O/R Mapper


# How-to
## set up enrivonment

TODO jflute comming soon...

## hands-on


# やり方 (Japanese here)

## 環境構築
1. javatryプロジェクトを自分のアカウントにフォーク(fork) 
2. フォークしたjavatryプロジェクトをgit clone (clone方法は任意)
3. IDEで開く (EclipseならImport, IntelliJならOpen)  
 o Eclipseなら、メニューの File - Import... で Existing Projects into Workspace  
 o IntelliJなら、Open (.ideaをキープするために Import ではなく Open を使うこと推奨)  
4. コンパイルされていることを確認  
 o 試しに Step01VariableTest.java の test_variable_basic() のテストを実行    
  -> Eclipse で Quick JUnit があれば、テストメソッド内にカーソルを置いて control+0
5. 特製の補完テンプレートを設定 (任意)  
 o Eclipseなら、Preferences の Java - Editor - Templates にて、一度すべてを選択して Remove して空っぽにしてから、Import... で etc/tools/eclipse/java-editor-templates.xml を選択して適用  
 o IntelliJなら、こちらを参考に: http://dbflute.seasar.org/ja/manual/topic/friends/intellij/index.html#dbflutelivetemplate

## ハンズオン
1. src/test/java の org.docksidestage.javatry の Step01VariableTest.java を開く
2. それぞれの Step ごとに、JavaDoc を読んで実施していく

※飛ばして先に進む場合は、後で忘れてしまわないようにtodoコメントを入れましょう  
(補完テンプレートが入っていれば、_todo で補完できます)
    
```
// TODO jflute 何々のやり方がわからなくて時間がかかりそうなのでひとまず先に進む (2019/06/14)
```

# License
Apache License 2.0
