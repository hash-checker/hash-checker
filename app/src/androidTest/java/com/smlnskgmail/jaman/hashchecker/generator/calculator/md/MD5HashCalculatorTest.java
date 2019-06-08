package com.smlnskgmail.jaman.hashchecker.generator.calculator.md;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.generator.calculator.BaseHashCalculatorTest;

public class MD5HashCalculatorTest extends BaseHashCalculatorTest {

    @NonNull
    @Override
    protected HashTypes getHashType() {
        return HashTypes.MD5;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "033bd94b1168d7e4f0d644c3c95e35bf";
    }

    @NonNull
    @Override
    protected String getHashValueForTesFile() {
        return "6344db3a6c0cfdc4a99881b5b16127f8";
    }

}
