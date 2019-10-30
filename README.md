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
| [CRC32](https://en.wikipedia.org/wiki/Cyclic_redundancy_check) | 2.9.0 | `Supporting` |

### 1.2. Languages

| Language | Since version | Translate status |
| --- | --- | --- |
| English | 1.0.0 | `Full` |
| Russian | 2.9.6 | `Full` |
| German | 2.9.6 | `Full` |
| French | 2.9.9 | `Full` |
| Hebrew | 2.9.9 | `Full` |
| Italian | 2.9.9 | `Full` |
| Polish | 2.9.9 | `Full` |
| Spanish | 2.9.9 | `Full` |
| Korean | 2.9.9 | `Full` |
| Chinese | 2.9.9 | `Full` |
| Persian | 2.9.9 | `Full` |
| Hungarian | 2.9.9 | `Full` |
| Swedish | 2.9.9 | `Full` |
| Greek | 2.9.9 | `Full` |
| Dutch | 2.9.9 | `Full` |

## 2. Screenshots

<br/>
<p align="center">
  <img src="media/screenshots/1.png" width="170" />
  <img src="media/screenshots/2.png" width="170" />
  <img src="media/screenshots/3.png" width="170" />
  <img src="media/screenshots/4.png" width="170" />
  <img src="media/screenshots/5.png" width="170" />
</p>

## 3. Videos

- [YouTube](https://www.youtube.com/watch?v=Q7Otn971kJk&list=PLOIwDRWd_SDdBz2aiVtMocFunaXaKSPMx)

## 4. Alternative downloads

- [GitHub](https://github.com/fartem/hash-checker/releases)

## 5. Tests

### 5.1. Android Test

This tests must be run on Android device.

#### 5.1.1. Tests table

| Class | Description | Status |
| --- | --- | --- |
| MD5HashCalculatorTest | Check MD5 generation | `Pass` |
| SHA1HashCalculatorTest | Check SHA-1 generation | `Pass` |
| SHA224HashCalculatorTest | Check SHA-224 generation | `Pass` |
| SHA256HashCalculatorTest | Check SHA-256 generation | `Pass` |
| SHA384HashCalculatorTest | Check SHA-384 generation | `Pass` |
| SHA512HashCalculatorTest | Check SHA-512 generation | `Pass` |
| CRC32HashCalculatorTest | Check CRC-32 generation | `Pass` |
| MessageDigestZeroLeadsHashCalculatorTest | Check zero leads for result of MessageDigest | `Pass` |
| ScreenRunnerTest | Open UI components | `Pass` |
| GenerateHashFromTextTest | Generate hash from text | `Pass` |

#### 5.1.2. Tests Suits

| Class | Description | Status |
| --- | --- | --- |
| HashTestSuite | Run all tests for check hash generation | `In development` |
| FastTestSuite | Run only needed base UI tests | `Deprecated` |
| AndroidTestSuite | Run all tests in "Android Test" section | `In development` |

### 5.2. Test

This tests must be run on computer.

#### 5.2.1. Tests table

| Class | Description | Status |
| --- | --- | --- |
| FileItemTest | Check fields in File item | `Pass` |
| HistoryItemTest | Check fields in History item | `Pass` |
| HashToolsTest | Check hash utils | `Pass` |
| TextToolsTest | Check text utils | `Pass` |

#### 5.2.2. Tests Suits

| Class | Description | Status |
| --- | --- | --- |
| TestSuite | Run all tests in "Test" section | `In development` |


## 6. Contacts

- jaman.smlnsk@gmail.com

## 7. Website

- [fartem.github.io/hash-checker.io](https://fartem.github.io/hash-checker.io/)

## 8. Privacy Policy

- [fartem.github.io/hash-checker-privacy-policy.io](https://fartem.github.io/hash-checker-privacy-policy.io/)
