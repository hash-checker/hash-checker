package com.smlnskgmail.jaman.hashchecker.hashcalculator.sha;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.generator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.hashcalculator.BaseHashCalculatorTest;

public class SHA256HashCalculatorTest extends BaseHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.SHA_256;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "94ee059335e587e501cc4bf90613e0814f00a7b08bc7c648fd865a2af6a22cc2";
    }

    @NonNull
    @Override
    protected String getHashValueForTesFile() {
        return "55c7691d1c7fe593284981b58bc0d6268f086aa91d325aec9f8970ac523ea0ff";
    }

}
