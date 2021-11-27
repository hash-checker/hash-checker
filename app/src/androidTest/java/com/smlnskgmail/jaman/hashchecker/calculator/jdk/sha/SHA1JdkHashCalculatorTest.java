package com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class SHA1JdkHashCalculatorTest extends BaseJdkHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.SHA_1;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "984816fd329622876e14907634264e6f332e9fb3";
    }

    @NonNull
    @Override
    protected String getHashValueForTestFile() {
        return "13bda17771cb0bdb19d1640e9e68f2dcec583d39";
    }

}
