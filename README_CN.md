# AndroidModalDialog
这是一个Android模态对话框的简单实现, 算是比较好用! <br>
当你创建一个模态对话框并展示的时候, **当前UI线程将会阻塞直到用户做出了关闭(程序内部经过处理不会导致ANR(Application Not Responding)发生**)!<br>
![](https://github.com/palatine/AndroidModalDialog/blob/master/imgs/androidmodaldialog.gif)
<br>
# USAGE
Step 1. 添加JitPack repository到你的项目build.gradle
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. 添加依赖
```
dependencies {
	        compile 'com.github.palatine:AndroidModalDialog:0.2'
	}
```
你可以直接使用 CommonSelectorDialog 来创建一个通用的对话框比如:
<br>
```Java
CommonSelectorDialog dialog = new CommonSelectorDialog.Builder(this).setTitle("This is title!")
                .setDesc("This is description!\nbalabalabalabalabalabalabalabalabalabalabalabalabalabala!")
                .setNeutralButtonString("NeutraButton")
                .setNegativeButtonString("NegativeButton")
                .setPositiveButtonString("PositiveButton")
                .setCancelWhenClickOutside(true)
                .build();
int result = dialog.doModal();
```
你也可以通过继承和扩展BaseDialog, PopupDialog来定制化你需要的对话框样式和功能!
# LICENSE
```
Copyright 2017 yzh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
