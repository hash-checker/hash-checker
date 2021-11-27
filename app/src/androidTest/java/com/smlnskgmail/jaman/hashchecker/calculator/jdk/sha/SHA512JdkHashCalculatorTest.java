package com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.BaseJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

public class SHA512JdkHashCalculatorTest extends BaseJdkHashCalculatorTest {

    @NonNull
    @Override
    protected HashType getHashType() {
        return HashType.SHA_512;
    }

    @NonNull
    @Override
    protected String getHashValueForTestText() {
        return "7bfa95a688924c47c7d22381f20cc926f524beacb13f84e203d4bd8cb6ba2fce81c57a5f059bf3d5" +
                "09926487bde925b3bcee0635e4f7baeba054e5dba696b2bf";
    }

    @NonNull
    @Override
    protected String getHashValueForTestFile() {
        return "37c3308c934cd08cc66201666ade6501f8e0c788ae80c5d4553782bee20f89fd06dc277a2ecc7f8c" +
                "c51ce69c5ad2989b830b85a4584b1c9e84b76bf6ed9d4c2e";
    }

}
