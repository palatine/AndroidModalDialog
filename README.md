# AndroidModalDialog
## [中文](https://github.com/palatine/AndroidModalDialog/blob/master/README_CN.md)
This is an Android modal dialog, and very easy for using and extending! <br>
When AndroidModalDialog start to show, **Current UI thread will be blocked util user calls endModal to pass their chice(However, in this situation ANR WON'T happen**)!<br>
![](https://github.com/palatine/AndroidModalDialog/blob/master/imgs/androidmodaldialog.gif)
<br>
# USAGE
Step 1. Add the JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
dependencies {
	        compile 'com.github.palatine:AndroidModalDialog:0.2'
	}
```
There is a simple implementation of AndroidModalDialog called CommonSelectorDialog, you can use it directly like this:
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
or you can extend BaseDialog or PopupDialog to customize your modal dialog!
<br>
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
