package com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class FNV1A1024HashCalculatorTest extends BaseJdkHashCalculatorTest {
    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.FNV_1A_1024;
    }

    @Nullable
    @Override
    protected String getHashValueForTestText() {
        return "26f791f9147aedad1354bef7d238f3219005cbd6e8d664f6b4eefdbe94929e41548c1804684a0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001ba08046e07e0418fb7be0ec07b8ea87a61bb4f073e2bab740db8398ef60cb9b50bff66ae86fa1";
    }

    @Nullable
    @Override
    protected String getHashValueForTestFile() {
        return "39be0e770c1d2a1a48ada02bf4f8d3197be0c8ec099628a87b9c31b1e511b8d75de75b4f5f3a8527d8ee56f757602c1bf5d9a2afd24f41984cafcd68d2059a69aaae05d3b0398fd71345cf710902ec5a2c2a711078ab826595c10d98b668eeac46222d6049e8bb366f501973ae2cd9a1ec92529e5659c6061a0bd629ba7cb475";
    }
}
