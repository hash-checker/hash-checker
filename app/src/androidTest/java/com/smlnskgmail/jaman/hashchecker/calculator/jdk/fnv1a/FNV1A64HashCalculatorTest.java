package com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class FNV1A64HashCalculatorTest extends BaseJdkHashCalculatorTest {
    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.FNV_1A_64;
    }

    @Nullable
    @Override
    protected String getHashValueForTestText() {
        return "ed8a7fa7d902e25";
    }

    @Nullable
    @Override
    protected String getHashValueForTestFile() {
        return "634cf7c2f7fff24d";
    }
}
