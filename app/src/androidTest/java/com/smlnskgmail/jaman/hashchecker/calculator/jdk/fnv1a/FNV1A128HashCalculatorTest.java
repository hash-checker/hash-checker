package com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class FNV1A128HashCalculatorTest extends BaseJdkHashCalculatorTest {
    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.FNV_1A_128;
    }

    @Nullable
    @Override
    protected String getHashValueForTestText() {
        return "68e4c35185757277b806e94be3e6b9e5";
    }

    @Nullable
    @Override
    protected String getHashValueForTestFile() {
        return "1c9f4d8aca026b06aeb355ae668de3d";
    }
}
