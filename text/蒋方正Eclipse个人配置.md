### 1、汉化

http://www.eclipse.org/babel/downloads.php

找到Babel Language Pack Zips，下面选自己版本点进去，找到如下类似的中文包：

BabelLanguagePack-eclipse-zh_4.7.0.v20171231020002.zip (85.5%)

然后解压，粘贴“features”和“plugins”文件夹到Eclipse的dropins目录下，重启即可。

### 2、字体设置

Windows -> Perspective -> Appearance -> Colors and Fonts -> Basic -> Text Font 修改为Yahei Consolas Hibrid

### 3、文件默认编码UTF-8

Preferences -> General -> Workspace 将"Text file encoding"选为"Other" - "UTF-8"

改变现有项目的编码的步骤: 右击项目属性, 选择"Text file encoding"的编码

Preferences -> Content Types 可以修改 Java Properties File 或者其他文件的默认编码格式等

### 4、增强自动代码提示

默认下, 只有按"."或"Alt + /"才出现代码提示功能, 不够强大, 作小小设置, 可将代码提示功能像VS的一样方便。进入：

Preferences -> Java -> Editor -> Content Assist

将"Auto Activation triggers for java"设置为".abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ(,@"

### 5、自动保存

Preferences -> General -> Editor -> Autosave

设置时间2s。

### 6、设置JDK本地JavaDOC API的路径 以及 源码

设置JDk：  Window -> Preferences -> Java -> Installed JREs

点击右侧的 Edit；全选 JRE system libraries 下的所有jar 包， 点击右侧的 Source Attachment，选择External Location, 选择JDK安装目录下的src.zip文件，点击OK即可！

有时候会遇到，非JDK的jar包没有源码，点击Attach Source，然后和JDK的类似，添加相关的src.zip即可。

### 7、智能输入分号和花括号

Preferences -> Java -> Editor -> Typing 打开Typing选项。选中Semicolons和Braces checkboxs。

### 8、设置Java格式化默认长度

强迫症来了，不喜欢一行太长的代码被格式化后换行。可以做如下设置避免换行：

Windows -> Preferences -> Java -> Code Style -> Formatter(格式化编辑) -> Edit -> Line Wrapping -> Maxmum line width  , 大小可以自己设置，个人习惯设置成512.

### 10、导出、导入个人配置

File > Export > General > Preferences，导入选择Import > General > 首选项 即可。

### 11、常用快捷键：

文档注释：Alt + Shift + J

折叠代码：Ctrl + Shift + Numpad_Divede(小键盘的/号)

展开代码：Ctrl + Shift + Numpad_Multiply(小键盘的*号)
