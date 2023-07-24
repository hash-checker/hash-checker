package com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class FNV1A512HashCalculatorTest extends BaseJdkHashCalculatorTest {
    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.FNV_1A_512;
    }

    @Nullable
    @Override
    protected String getHashValueForTestText() {
        return "f9fe9eefe38ca43fcf36c8fbc0d25bef517a2b362c00000000002a5259a146c7f24cae042d99828e5baba0a28b18bf530de9c3137ca2a36973f8d078ba143155";
    }

    @Nullable
    @Override
    protected String getHashValueForTestFile() {
        return "5eedfc37e4ce8df68bf0978a79c81f095ce8a2ffd1a5c692288d8215676ce63e99453c012c4098ba2757309d704cc74ee3bb76b506194f58ae56711cc024825d";
    }
}
