package com.smlnskgmail.jaman.hashchecker.calculator.values.zeroleads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.calculator.values.BaseHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.HashType;

public class MessageDigestZeroLeadsHashCalculatorTest extends BaseHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.MD5;
    }

    @Nullable
    @Override
    protected String getHashValueForTestText() {
        return "0000045c5e2b3911eb937d9d8c574f09";
    }

    @Override
    public String getTestingText() {
        return "iwrupvqb346386";
    }

    @Override
    public void checkFile() {}

    @Nullable
    @Override
    protected String getHashValueForTestFile() {
        return null;
    }

}
