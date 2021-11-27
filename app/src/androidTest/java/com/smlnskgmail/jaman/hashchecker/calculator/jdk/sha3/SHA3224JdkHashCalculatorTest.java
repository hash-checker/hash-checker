package com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha3;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class SHA3224JdkHashCalculatorTest extends BaseJdkHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.SHA3_224;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "d40cc4f9630f21eef0b185bdd6a51eab1775c1cd6ae458066ecaf046";
    }

    @NonNull
    @Override
    protected String getHashValueForTestFile() {
        return "d73bab634f110cff9257d928f1b85047da1fbf6c2824871b55f87854";
    }

}
