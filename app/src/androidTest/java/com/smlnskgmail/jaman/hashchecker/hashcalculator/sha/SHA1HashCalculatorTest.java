package com.smlnskgmail.jaman.hashchecker.hashcalculator.sha;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.hashcalculator.BaseHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.hashgenerator.support.HashType;

public class SHA1HashCalculatorTest extends BaseHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.SHA_1;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "984816fd329622876e14907634264e6f332e9fb3";
    }

    @NonNull
    @Override
    protected String getHashValueForTesFile() {
        return "13bda17771cb0bdb19d1640e9e68f2dcec583d39";
    }

}
