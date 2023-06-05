package com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha3;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class SHA3384JdkHashCalculatorTest extends BaseJdkHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.SHA3_384;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "da73bfcba560692a019f52c37de4d5e3ab49ca39c6a75594e3c39d805388c4de9d0ff3927eb9e197536f5b0b3a515f0a";
    }

    @NonNull
    @Override
    protected String getHashValueForTestFile() {
        return "f09d080e29cf76147af10932836ba90a3d1f3205140dd302ae947b54ceb162ea3a4f1c30ded1de4b1de3174048728cb6";
    }

}
