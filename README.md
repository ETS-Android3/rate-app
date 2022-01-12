# rate-app
RateApp is a small library that allows you to rate the app on Play Store.

[![Release](https://jitpack.io/v/ibrahimlee/rate-app.svg)](https://jitpack.io/#ibrahimlee/rate-app)

# Usage
**Get RateApp library**

Add the JitPack repository to your build file.
Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency
```groovy
dependencies {
     implementation 'com.github.ibrahimlee:rate-app:TAG'
}
```

Params
```groovy

/* This is application id which must same on Play Store app id. */
private String appId = BuildConfig.APPLICATION_ID;

/* This defines buttons' text and stars' color. */
private String colorString = "#2244ED";

/* This defines how many times the rate giving dialog is shown on start. */
private int showAt = 3;

```

Initialize
```groovy

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    RateApp.initialize(this, appId, colorString, showAt);
}

```

**NOTE**

It evaluates itself up to 4 stars. Redirects to Play Store at 4 and 5 stars.


---

[Example gif 1](https://github.com/ibrahimlee/rate-app/blob/master/EXAMPLE_GIF_3_STAR.gif)

[Example gif 2](https://github.com/ibrahimlee/rate-app/blob/master/EXAMPLE_GIF_5_STAR.gif)

-----

### Enjoy!

# Contract

Please let us know, if you use the library in your applications. 
We want to collect and publish this list.

# License

    MIT License

    Copyright (c) 2021 Ilkin Ibrahimli

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
