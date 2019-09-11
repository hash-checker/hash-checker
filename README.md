![GitHub release](https://img.shields.io/github/release/fartem/hash-checker.svg?color=009688)
![Open issues](https://img.shields.io/github/issues-raw/fartem/hash-checker.svg)
![Last commit](https://img.shields.io/github/last-commit/fartem/hash-checker.svg)
![Repo size](https://img.shields.io/github/repo-size/fartem/hash-checker.svg)
[![License](https://img.shields.io/github/license/fartem/hash-checker.svg)](https://github.com/fartem/hash-checker/blob/master/LICENSE)

# Hash Checker | [Google Play](https://play.google.com/store/apps/details?id=com.smlnskgmail.jaman.hashchecker)

<p align="center"><img src="media/ic_app.png" height="200px"></p>

## 1. About application

Fast and simple application for generating and comparison hashes from files or text.

### 1.1. Supporting algorithms

| Name | Since version | Status |
| --- | --- | --- |
| [MD5](https://en.wikipedia.org/wiki/MD5) | `1.0.0` | Supporting |
| [SHA-1](https://en.wikipedia.org/wiki/SHA-1) | `1.0.0` | Supporting |
| [SHA-224](https://en.wikipedia.org/wiki/SHA-2) | `1.4.0` | Supporting |
| [SHA-256](https://en.wikipedia.org/wiki/SHA-2) | `1.0.0` | Supporting |
| [SHA-384](https://en.wikipedia.org/wiki/SHA-2) | `1.4.0` | Supporting |
| [SHA-512](https://en.wikipedia.org/wiki/SHA-2) | `1.0.0` | Supporting |
| [CRC-32](https://en.wikipedia.org/wiki/Cyclic_redundancy_check) | `2.9.0` | Supporting |

### 1.2. Languages

| Language | Since version | Translate status |
| --- | --- | --- |
| [English](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values/strings.xml) | `1.0.0` | Full |
| [Russian](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-ru/strings.xml) | `2.9.6` | Full |
| [German](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-de/strings.xml) | `2.9.6` | Full |
| [French](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-fr/strings.xml) | `2.9.9` | Full |
| [Hebrew](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-iw/strings.xml) | `2.9.9` | Full |
| [Italian](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-it/strings.xml) | `2.9.9` | Full |
| [Polish](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-pl/strings.xml) | `2.9.9` | Full |
| [Spanish](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-es/strings.xml) | `2.9.9` | Full |
| [Korean](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-kr/strings.xml) | `2.9.9` | Full |
| [Chinese](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-zh/strings.xml) | `2.9.9` | Full |
| [Persian](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-fa/strings.xml) | `2.9.9` | Full |
| [Hungarian](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-hu/strings.xml) | `2.9.9` | Full |
| [Swedish](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-sv/strings.xml) | `2.9.9` | Full |
| [Greek](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-el/strings.xml) | `2.9.9` | Full |
| [Dutch](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-nl/strings.xml) | `2.9.9` | Full |

## 2. Screenshots

<br/>
<p align="center">
  <img src="media/screenshots/1.png" width="150" />
  <img src="media/screenshots/2.png" width="150" />
  <img src="media/screenshots/3.png" width="150" />
  <img src="media/screenshots/4.png" width="150" />
  <img src="media/screenshots/5.png" width="150" />
</p>

## 3. Videos

[YouTube](https://www.youtube.com/watch?v=Q7Otn971kJk&list=PLOIwDRWd_SDdBz2aiVtMocFunaXaKSPMx)

## 4. Alternative downloads

[GitHub](https://github.com/fartem/hash-checker/releases)

## 5. Tests

### 5.1. Android Test

This tests must be run on Android device.

#### 5.1.1. Tests table

| Functionality | Status | Description |
| --- | --- | --- |
| [MD5HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/md/MD5HashCalculatorTest.java) | `Pass` | Check MD5 generation |
| [SHA1HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/sha/SHA1HashCalculatorTest.java) | `Pass` | Check SHA-1 generation |
| [SHA224HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/sha/SHA224HashCalculatorTest.java) | `Pass` | Check SHA-224 generation |
| [SHA256HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/sha/SHA256HashCalculatorTest.java) | `Pass` | Check SHA-256 generation |
| [SHA384HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/sha/SHA384HashCalculatorTest.java) | `Pass` | Check SHA-384 generation |
| [SHA512HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/sha/SHA512HashCalculatorTest.java) | `Pass` | Check SHA-512 generation |
| [CRC32HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/crc/CRC32HashCalculatorTest.java) | `Pass` | Check CRC-32 generation |
| [ScreenRunnerTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/ui/screenrunner/ScreenRunnerTest.java) | `Pass` | Open UI components |
| [GenerateHashFromTextTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/ui/functionality/GenerateHashFromTextTest.java) | `Pass` | Generate hash from text |

#### 5.1.2. Tests Suits

| Functionality | Status | Description |
| --- | --- | --- |
| [HashTestSuite](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/HashTestSuite.java) | `In development` | Run all tests for check hash generation |
| [FastTestSuite](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/ui/FastTestSuite.java) | `Deprecated` | Run only needed base UI tests |
| [AndroidTestSuite](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/TestSuite.java) | `In development` | Run all tests in "Android Test" section |

### 5.2. Test

This tests must be run on computer.

#### 5.2.1. Tests table

| Functionality | Status | Description |
| --- | --- | --- |
| [FileItemTest](https://github.com/fartem/hash-checker/blob/master/app/src/test/java/com/smlnskgmail/jaman/hashchecker/entities/FileItemTest.java) | `Pass` | Check fields in File item |
| [HistoryItemTest](https://github.com/fartem/hash-checker/blob/master/app/src/test/java/com/smlnskgmail/jaman/hashchecker/entities/HistoryItemTest.java) | `Pass` | Check fields in History item |
| [HashUtilsTest](https://github.com/fartem/hash-checker/blob/master/app/src/test/java/com/smlnskgmail/jaman/hashchecker/utils/HashUtilsTest.java) | `Pass` | Check hash utils |
| [TextUtilsTest](https://github.com/fartem/hash-checker/blob/master/app/src/test/java/com/smlnskgmail/jaman/hashchecker/utils/TextUtilsTest.java) | `Pass` | Check text utils |

#### 5.2.2. Tests Suits

| Functionality | Status | Description |
| --- | --- | --- |
| [TestSuite](https://github.com/fartem/hash-checker/blob/master/app/src/test/java/com/smlnskgmail/jaman/hashchecker/TestSuite.java) | `In development` | Run all tests in "Test" section |


## 6. Contacts

jaman.smlnsk@gmail.com

## 7. Website

[fartem.github.io/hash-checker.io](https://fartem.github.io/hash-checker.io/)

## 8. Privacy Policy

[fartem.github.io/hash-checker-privacy-policy.io](https://fartem.github.io/hash-checker-privacy-policy.io/)
