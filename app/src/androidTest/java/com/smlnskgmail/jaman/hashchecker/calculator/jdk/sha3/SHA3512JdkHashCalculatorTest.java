package com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha3;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class SHA3512JdkHashCalculatorTest extends BaseJdkHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.SHA3_512;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "301bb421c971fbb7ed01dcc3a9976ce53df034022ba982b97d0f27d48c4f03883aabf7c6" +
                "bc778aa7c383062f6823045a6d41b8a720afbb8a9607690f89fbe1a7";
    }

    @NonNull
    @Override
    protected String getHashValueForTestFile() {
        return "705b40891f39d9659dc5e028be94e2e6cd2137339dabedbe57b0a00093f9d96d6228a6e2" +
                "6a4229a3d95fd029323d9a9e756926aff92344408a1aee7812443b15";
    }

}
