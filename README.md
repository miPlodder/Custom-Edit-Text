[![](https://jitpack.io/v/miPlodder/CustomEditText.svg)](https://jitpack.io/#miPlodder/CustomEditText)

# CustomEditText

This library provides custom visualizable and dynamic EditText which will be surely help you in many use-cases and are easy to use. 

## APK
[Sample APK](https://github.com/miPlodder/CustomEditText/blob/master/raw/app-debug.apk)

## Usage

**1) Gradle Dependency**
- **Add this to project level `build.gradle`**
```
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

- **Add this to app `build.gradle`**
```
dependencies {
       implementation 'com.github.miPlodder:CustomEditText:v0.0.2'
	}
```

**2) Usage**

- **Add the custom EditText in your xml layout.**

***a) For DynamicAmountEditText***
```
<com.example.library.customEditText.DynamicAmountEditText
        android:id="@+id/da_et"
        app:integerLimit="5"
        app:decimalLimit="2"
        android:hint="DynamicAmountEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
 ```
Use `app:integerLimit` to *"set integer characters limit"* and use `app:decimalLimit` to *"set decimal characters limit"*.

***b)  For BetterVisualizerEditText***
```
<com.example.library.customEditText.BetterVisualizerEditText
        android:id="@+id/bv_et"
        app:spaceAfter="3"
        app:wordLimit="9"
        android:hint="BetterVisualizerEditText"
        android:layout_width="match_parent"
android:layout_height="wrap_content" />
```
Use `app:spaceAfter` will be used to *"add space after that number of characters"* and use `app:wordLimit` to *"restrict the number of characters"*.

- **Accessing custom EditText in Java**

***a) For DynamicAmountEditText***
```
DynamicAmmountEditText dynamicAmountEditText = findViewById(R.id.da_et);
dynamicAmountEditText.getActualText() //used to get the text
```

***b) For BetterVisualizerEditText***
```
BetterVisualizerEditText betterVisualizerEditText = findViewById(R.id.bv_et);;
betterVisualizerEditText.getActualText() //used to get the text
```

## GIF

![GIF](https://github.com/miPlodder/CustomEditText/blob/master/raw/custom_edit_text.gif)

## Contributions

*  [Saksham Handu](https://github.com/miPlodder)

You are also welcome to contribute.

## License

Copyright [2018] [Saksham Handu]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
