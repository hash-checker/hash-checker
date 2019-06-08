package com.smlnskgmail.jaman.hashchecker.generator.calculator.sha;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.generator.calculator.BaseHashCalculatorTest;

public class SHA224HashCalculatorTest extends BaseHashCalculatorTest {

    @NonNull
    @Override
    protected HashTypes getHashType() {
        return HashTypes.SHA_224;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "917ecca24f3e6ceaf52375d8083381f1f80a21e6e49fbadc40afeb8e";
    }

    @NonNull
    @Override
    protected String getHashValueForTesFile() {
        return "bf2483bff7724e6bc43ead3e247176a1855f137983e530d1c7b2dcfd";
    }

}
