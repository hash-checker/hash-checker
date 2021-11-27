package com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class SHA384JdkHashCalculatorTest extends BaseJdkHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.SHA_384;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "4f37c49c0024445f91977dbc47bd4da9c4de8d173d03379ee19c2bb15435c2c7e624ea42f7cc1689" +
                "961cb7aca50c7d17";
    }

    @NonNull
    @Override
    protected String getHashValueForTestFile() {
        return "3ec289df7fe5d9071122785d994599dda0c5d7cca60db27d1e0640ab2f7e94bb93d4200d4565dfc3" +
                "aea98d12279bb1cb";
    }

}
