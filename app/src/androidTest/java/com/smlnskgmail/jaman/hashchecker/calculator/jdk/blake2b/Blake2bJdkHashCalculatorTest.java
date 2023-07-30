package com.smlnskgmail.jaman.hashchecker.calculator.jdk.blake2b;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class Blake2bJdkHashCalculatorTest extends BaseJdkHashCalculatorTest {
    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.BLAKE_2B;
    }

    @Nullable
    @Override
    protected String getHashValueForTestText() {
        return "5322bc39e200a6d2ef54ac6716376d5000f98a9715cb5293edd6e1e0f8865d3b22cb0fa92e09d52abef0cf58a2b067d4bc64fbee1e4bce0e9e642ce803dc6f99";
    }

    @Nullable
    @Override
    protected String getHashValueForTestFile() {
        return "d2368c0f213ec2c5e55f668dc6f7460b6c0aa48b1da990e5d5d2c7a9caa1a030a15ff1d22dcc5a80d2847e2346fb81338579b52e4c181867379c43d508aaf35e";
    }
}
