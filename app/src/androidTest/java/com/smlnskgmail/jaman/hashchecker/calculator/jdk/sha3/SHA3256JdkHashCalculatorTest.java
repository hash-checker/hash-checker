package com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha3;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class SHA3256JdkHashCalculatorTest extends BaseJdkHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.SHA3_256;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "c0a5cca43b8aa79eb50e3464bc839dd6fd414fae0ddf928ca23dcebf8a8b8dd0";
    }

    @NonNull
    @Override
    protected String getHashValueForTestFile() {
        return "96ddd435f0a15446dfac5d49dfbc2c7ee404afd1e049609ef04347c657e228e3";
    }

}
