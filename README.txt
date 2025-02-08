-------------------------------------------
Source installation information for modders
-------------------------------------------
This code follows the Minecraft Forge installation methodology. It will apply
some small patches to the vanilla MCP source code, giving you and it access 
to some of the data and functions you need to build a successful mod.

Note also that the patches are built against "unrenamed" MCP source code (aka
srgnames) - this means that you will not be able to read them directly against
normal code.

Source pack installation information:

Standalone source installation
==============================

See the Forge Documentation online for more detailed instructions:
http://mcforge.readthedocs.io/en/latest/gettingstarted/

Step 1: Open your command-line and browse to the folder where you extracted the zip file.

Step 2: You're left with a choice.
If you prefer to use Eclipse:
1. Run the following command: "gradlew genEclipseRuns" (./gradlew genEclipseRuns if you are on Mac/Linux)
2. Open Eclipse, Import > Existing Gradle Project > Select Folder 
   or run "gradlew eclipse" to generate the project.
(Current Issue)
4. Open Project > Run/Debug Settings > Edit runClient and runServer > Environment
5. Edit MOD_CLASSES to show [modid]%%[Path]; 2 times rather then the generated 4.

If you prefer to use IntelliJ:
1. Open IDEA, and import project.
2. Select your build.gradle file and have it import.
3. Run the following command: "gradlew genIntellijRuns" (./gradlew genIntellijRuns if you are on Mac/Linux)
4. Refresh the Gradle Project in IDEA if required.

If at any point you are missing libraries in your IDE, or you've run into problems you can run "gradlew --refresh-dependencies" to refresh the local cache. "gradlew clean" to reset everything {this does not affect your code} and then start the processs again.

Should it still not work, 
Refer to #ForgeGradle on EsperNet for more information about the gradle environment.
or the Forge Project Discord discord.gg/UvedJ9m

Forge source installation
=========================
MinecraftForge ships with this code and installs it as part of the forge
installation process, no further action is required on your part.

LexManos' Install Video
=======================
https://www.youtube.com/watch?v=8VEdtQLuLO0&feature=youtu.be

For more details update more often refer to the Forge Forums:
http://www.minecraftforge.net/forum/index.php/topic,14048.0.html

/*
百度翻译结果如下：

-------------------------------------------

模块的源安装信息

-------------------------------------------

此代码遵循Minecraft Forge安装方法。它将适用

一些普通MCP源代码的小补丁，让您和它都可以访问

构建成功的mod所需的一些数据和功能。



还要注意，补丁是根据“未命名”的MCP源代码（又名

srgnames）-这意味着你将无法直接阅读它们

正常代码。



源包安装信息：



独立源安装

==============================



有关更多详细说明，请参阅Forge文档：

http://mcforge.readthedocs.io/en/latest/gettingstarted/



步骤1：打开命令行并浏览到提取zip文件的文件夹。



第二步：你还有一个选择。

如果您喜欢使用Eclipse：

1.运行以下命令：“gradlew genEclipseRuns”（如果您在Mac/Linux上，则为./gradlew gen EclipseRun）

2.打开Eclipse，导入>现有渐变项目>选择文件夹

或者运行“gradlew eclipse”生成项目。

（当前版本）

4.打开项目>运行/调试设置>编辑runClient和runServer>环境

5.编辑MOD_CLASSES以显示[modid]%%[Path]；2倍，而不是生成的4倍。



如果您喜欢使用IntelliJ：

1.打开IDEA，导入项目。

2.选择build.gradle文件并将其导入。

3.运行以下命令：“gradlew genIntellijRuns”（如果您在Mac/Linux上，则为./gradlew gen IntellijRun）

4.如果需要，在IDEA中刷新Gradle项目。



如果在任何时候IDE中缺少库，或者遇到了问题，可以运行“gradlew-refresh dependencies”来刷新本地缓存。“gradlew clean”重置所有内容｛这不会影响代码｝，然后再次启动进程。



如果它仍然不起作用，

有关渐变环境的更多信息，请参阅EsperNet上的#ForgeGradle。

或Forge Project Discord不协调.gg/UvedJ9m



锻造源安装

=========================

MinecraftForge附带此代码，并将其作为锻造的一部分安装

安装过程中，您无需采取进一步的操作。



LexManos的安装视频

=======================

https://www.youtube.com/watch?v=8VEdtQLuLO0&feature=youtu.be



有关更多详细信息，请经常参阅Forge论坛：

http://www.minecraftforge.net/forum/index.php/topic，14048.0.html
*/
