package com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class FNV1A32HashCalculatorTest extends BaseJdkHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.FNV_1A_32;
    }

    @Nullable
    @Override
    protected String getHashValueForTestText() {
        return "b2d739e5";
    }

    @Nullable
    @Override
    protected String getHashValueForTestFile() {
        return "2dc2698d";
    }
}
