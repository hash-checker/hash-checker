package com.smlnskgmail.jaman.hashchecker.calculator.jdk.md;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class MD5JdkHashCalculatorTest extends BaseJdkHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.MD5;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "033bd94b1168d7e4f0d644c3c95e35bf";
    }

    @NonNull
    @Override
    protected String getHashValueForTestFile() {
        return "6344db3a6c0cfdc4a99881b5b16127f8";
    }

}
