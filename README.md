<img src="media/logo/ic_app.png" height="100px" />

# Hash Checker

<a href="https://github.com/fartem/hash-checker/releases">
  <img src="media/banners/bn_github.png" height="75px" />
</a>
<a href="https://play.google.com/store/apps/details?id=com.smlnskgmail.jaman.hashchecker">
  <img src="media/banners/bn_google_play.png" height="75px" />
</a>

[![Travis CI](https://travis-ci.org/fartem/parse-android-test-app.svg?branch=master)](https://travis-ci.org/fartem/hash-checker)
[![Codebeat](https://codebeat.co/badges/f50ffd5e-e62f-413c-b84a-4308a9399ae9)](https://codebeat.co/projects/github-com-fartem-hash-checker-master)
[![Codecov](https://codecov.io/gh/fartem/hash-checker/branch/master/graph/badge.svg)](https://codecov.io/gh/fartem/hash-checker)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Hash%20Checker-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/7854)

## About application

Fast and simple application for generating and comparison hashes from files or text.

## Supporting algorithms

| Name | Since version | Status |
| --- | --- | --- |
| [MD5](https://en.wikipedia.org/wiki/MD5) | 1.0.0 | `Supporting` |
| [SHA-1](https://en.wikipedia.org/wiki/SHA-1) | 1.0.0 | `Supporting` |
| [SHA-224](https://en.wikipedia.org/wiki/SHA-2) | 1.4.0 | `Supporting` |
| [SHA-256](https://en.wikipedia.org/wiki/SHA-2) | 1.0.0 | `Supporting` |
| [SHA-384](https://en.wikipedia.org/wiki/SHA-2) | 1.4.0 | `Supporting` |
| [SHA-512](https://en.wikipedia.org/wiki/SHA-2) | 1.0.0 | `Supporting` |
| [CRC-32](https://en.wikipedia.org/wiki/Cyclic_redundancy_check) | 2.9.0 | `Supporting` |

## Supporting languages

You can help with translation on [OneSky](https://osbvnmv.oneskyapp.com/collaboration/project?id=353871).

| Language | Since version | Status |
| --- | --- | --- |
| English | 1.0.0 | `Translated` |
| Español | 2.9.9 | [Help wanted](https://github.com/fartem/hash-checker/issues/9) |
| Deutsch | 2.9.6 | `Translated` |
| Français | 2.9.9 | [Help wanted](https://github.com/fartem/hash-checker/issues/11) |
| Italiano | 2.9.9 | [Help wanted](https://github.com/fartem/hash-checker/issues/12) |
| Magyar | 2.9.9 | [Help wanted](https://github.com/fartem/hash-checker/issues/13) |
| Nederlands | 2.9.9 | [Help wanted](https://github.com/fartem/hash-checker/issues/14) |
| Svenska | 2.9.9 | [Help wanted](https://github.com/fartem/hash-checker/issues/15) |
| Ελληνικά | 2.9.9 | [Help wanted](https://github.com/fartem/hash-checker/issues/16) |
| Русский | 2.9.6 | `Translated` |
| Polski | 2.9.9 | [Help wanted](https://github.com/fartem/hash-checker/issues/20) |
| 简体中文 | 2.9.9 | `Translated` |
| 한국어 | 2.9.9 | [Help wanted](https://github.com/fartem/hash-checker/issues/19) |
| עברית | 2.9.9 | [Help wanted](https://github.com/fartem/hash-checker/issues/17) |
| فارسی | 2.9.9 | `Translated` |

## Screenshots (Light theme)

<br/>
<p align="center">
  <img src="media/screenshots/screenshot_01.png" width="130" />
  <img src="media/screenshots/screenshot_02.png" width="130" />
  <img src="media/screenshots/screenshot_03.png" width="130" />
  <img src="media/screenshots/screenshot_04.png" width="130" />
  <img src="media/screenshots/screenshot_05.png" width="130" />
</p>

## Screenshots (Dark theme)

<br/>
<p align="center">
  <img src="media/screenshots/screenshot_06.png" width="130" />
  <img src="media/screenshots/screenshot_07.png" width="130" />
  <img src="media/screenshots/screenshot_08.png" width="130" />
  <img src="media/screenshots/screenshot_09.png" width="130" />
  <img src="media/screenshots/screenshot_10.png" width="130" />
</p>

## Videos

* [YouTube](https://www.youtube.com/watch?v=Q7Otn971kJk&list=PLOIwDRWd_SDdBz2aiVtMocFunaXaKSPMx)

## Articles

* [H2S Media](https://www.how2shout.com/how-to/how-to-calculate-the-hash-of-a-file-or-create-custom-hash-on-android.html)

## Black Duck Open Hub

* [Hash Checker (Android)](https://www.openhub.net/p/hash-checker)

## Privacy Policy

* [fartem.github.io/hash-checker-privacy-policy.io](https://fartem.github.io/hash-checker-privacy-policy.io/)

## How to build unsigned .apk from command line without IDE

From project directory run:

```shell
$ ./gradlew clean
$ ./gradlew assembleDebug
```

Go to `app` -> `build` -> `outputs` -> `apk` -> `debug` and find `hash-checker_VERSION.apk` where 'VERSION' is number of app version.

## How to contribute

Read [Commit Convention](https://github.com/fartem/repository-rules/blob/master/commit-convention/COMMIT_CONVENTION.md). Make sure your build is green before you contribute your pull request. Then:

```shell
$ ./gradlew clean
$ ./gradlew build
$ ./gradlew connectedCheck
```

If you don't see any error messages, submit your pull request.

## Contributors

* [@fartem](https://github.com/fartem) as Artem Fomchenkov
* [@Marwa-Eltayeb](https://github.com/Marwa-Eltayeb) as Marwa Said
