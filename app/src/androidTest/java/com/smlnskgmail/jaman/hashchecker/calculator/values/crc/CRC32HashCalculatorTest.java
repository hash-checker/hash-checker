package com.smlnskgmail.jaman.hashchecker.calculator.values.crc;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.values.BaseHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.HashType;

public class CRC32HashCalculatorTest extends BaseHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.CRC_32;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "eeea93b8";
    }

    @NonNull
    @Override
    protected String getHashValueForTestFile() {
        return "60c7400a";
    }

}
