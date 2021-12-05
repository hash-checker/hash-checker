package com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class SHA224JdkHashCalculatorTest extends BaseJdkHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.SHA_224;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "917ecca24f3e6ceaf52375d8083381f1f80a21e6e49fbadc40afeb8e";
    }

    @NonNull
    @Override
    protected String getHashValueForTestFile() {
        return "bf2483bff7724e6bc43ead3e247176a1855f137983e530d1c7b2dcfd";
    }

}
