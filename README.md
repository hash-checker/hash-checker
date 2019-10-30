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
| [MD5](https://en.wikipedia.org/wiki/MD5) | 1.0.0 | `Supporting` |
| [SHA-1](https://en.wikipedia.org/wiki/SHA-1) | 1.0.0 | `Supporting` |
| [SHA-224](https://en.wikipedia.org/wiki/SHA-2) | 1.4.0 | `Supporting` |
| [SHA-256](https://en.wikipedia.org/wiki/SHA-2) | 1.0.0 | `Supporting` |
| [SHA-384](https://en.wikipedia.org/wiki/SHA-2) | 1.4.0 | `Supporting` |
| [SHA-512](https://en.wikipedia.org/wiki/SHA-2) | 1.0.0 | `Supporting` |
| [CRC-32](https://en.wikipedia.org/wiki/Cyclic_redundancy_check) | 2.9.0 | `Supporting` |

### 1.2. Languages

| Language | Since version | Translate status |
| --- | --- | --- |
| [English](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values/strings.xml) | 1.0.0 | `Full` |
| [Russian](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-ru/strings.xml) | 2.9.6 | `Full` |
| [German](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-de/strings.xml) | 2.9.6 | `Full` |
| [French](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-fr/strings.xml) | 2.9.9 | `Full` |
| [Hebrew](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-iw/strings.xml) | 2.9.9 | `Full` |
| [Italian](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-it/strings.xml) | 2.9.9 | `Full` |
| [Polish](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-pl/strings.xml) | 2.9.9 | `Full` |
| [Spanish](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-es/strings.xml) | 2.9.9 | `Full` |
| [Korean](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-kr/strings.xml) | 2.9.9 | `Full` |
| [Chinese](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-zh/strings.xml) | 2.9.9 | `Full` |
| [Persian](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-fa/strings.xml) | 2.9.9 | `Full` |
| [Hungarian](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-hu/strings.xml) | 2.9.9 | `Full` |
| [Swedish](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-sv/strings.xml) | 2.9.9 | `Full` |
| [Greek](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-el/strings.xml) | 2.9.9 | `Full` |
| [Dutch](https://github.com/fartem/hash-checker/blob/master/app/src/main/res/values-nl/strings.xml) | 2.9.9 | `Full` |

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

| Functionality | Description | Status |
| --- | --- | --- |
| [MD5HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/md/MD5HashCalculatorTest.java) | Check MD5 generation | `Pass` |
| [SHA1HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/sha/SHA1HashCalculatorTest.java) | Check SHA-1 generation | `Pass` |
| [SHA224HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/sha/SHA224HashCalculatorTest.java) | Check SHA-224 generation | `Pass` |
| [SHA256HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/sha/SHA256HashCalculatorTest.java) | Check SHA-256 generation | `Pass` |
| [SHA384HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/sha/SHA384HashCalculatorTest.java) | Check SHA-384 generation | `Pass` |
| [SHA512HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/sha/SHA512HashCalculatorTest.java) | Check SHA-512 generation | `Pass` |
| [CRC32HashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/crc/CRC32HashCalculatorTest.java) | Check CRC-32 generation | `Pass` |
| [MessageDigestZeroLeadsHashCalculatorTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/zeroleads/MessageDigestZeroLeadsHashCalculatorTest.java) | Check zero leads for result of MessageDigest | `Pass` |
| [ScreenRunnerTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/ui/screenrunner/ScreenRunnerTest.java) | Open UI components | `Pass` |
| [GenerateHashFromTextTest](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/ui/functionality/GenerateHashFromTextTest.java) | Generate hash from text | `Pass` |

#### 5.1.2. Tests Suits

| Functionality | Description | Status |
| --- | --- | --- |
| [HashTestSuite](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/hashcalculator/HashTestSuite.java) | Run all tests for check hash generation | `In development` |
| [FastTestSuite](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/ui/FastTestSuite.java) | Run only needed base UI tests | `Deprecated` |
| [AndroidTestSuite](https://github.com/fartem/hash-checker/blob/master/app/src/androidTest/java/com/smlnskgmail/jaman/hashchecker/TestSuite.java) | Run all tests in "Android Test" section | `In development` |

### 5.2. Test

This tests must be run on computer.

#### 5.2.1. Tests table

| Functionality | Description | Status |
| --- | --- | --- |
| [FileItemTest](https://github.com/fartem/hash-checker/blob/master/app/src/test/java/com/smlnskgmail/jaman/hashchecker/entities/FileItemTest.java) | Check fields in File item | `Pass` |
| [HistoryItemTest](https://github.com/fartem/hash-checker/blob/master/app/src/test/java/com/smlnskgmail/jaman/hashchecker/entities/HistoryItemTest.java) | Check fields in History item | `Pass` |
| [HashToolsTest](https://github.com/fartem/hash-checker/blob/master/app/src/test/java/com/smlnskgmail/jaman/hashchecker/utils/HashToolsTest.java) | Check hash utils | `Pass` |
| [TextToolsTest](https://github.com/fartem/hash-checker/blob/master/app/src/test/java/com/smlnskgmail/jaman/hashchecker/utils/TextToolsTest.java) | Check text utils | `Pass` |

#### 5.2.2. Tests Suits

| Functionality | Description | Status |
| --- | --- | --- |
| [TestSuite](https://github.com/fartem/hash-checker/blob/master/app/src/test/java/com/smlnskgmail/jaman/hashchecker/TestSuite.java) | Run all tests in "Test" section | `In development` |


## 6. Contacts

jaman.smlnsk@gmail.com

## 7. Website

[fartem.github.io/hash-checker.io](https://fartem.github.io/hash-checker.io/)

## 8. Privacy Policy

[fartem.github.io/hash-checker-privacy-policy.io](https://fartem.github.io/hash-checker-privacy-policy.io/)
