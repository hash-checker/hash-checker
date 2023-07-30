package com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class FNV1A256HashCalculatorTest extends BaseJdkHashCalculatorTest {
    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.FNV_1A_256;
    }

    @Nullable
    @Override
    protected String getHashValueForTestText() {
        return "e46ddd4ed460b28b97ef66459f2a8e9d123f79d831721584cc463c5a449cb085";
    }

    @Nullable
    @Override
    protected String getHashValueForTestFile() {
        return "2af8fb0c00e358e3c67fadf7da9bb410102f443e9904b32bc8fd5f411f9def0d";
    }
}
