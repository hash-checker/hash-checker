package com.smlnskgmail.jaman.hashchecker.hashcalculator.crc;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.hashcalculator.BaseHashCalculatorTest;

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
