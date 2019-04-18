package com.smlnskgmail.jaman.hashchecker.generator.calculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import com.smlnskgmail.jaman.hashchecker.generator.HashCalculator;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class HashCalculatorTest {

    private static final String TESTING_TEXT = "TEST"; // or use "iwrupvqb346386" for test zero leads
    private static final String TESTING_FILE = "test.zip";

    private static final String TESTING_TEXT_MD_5_VALUE
            = "033bd94b1168d7e4f0d644c3c95e35bf";
    private static final String TESTING_TEXT_SHA_1_VALUE
            = "984816fd329622876e14907634264e6f332e9fb3";
    private static final String TESTING_TEXT_SHA_224_VALUE
            = "917ecca24f3e6ceaf52375d8083381f1f80a21e6e49fbadc40afeb8e";
    private static final String TESTING_TEXT_SHA_256_VALUE
            = "94ee059335e587e501cc4bf90613e0814f00a7b08bc7c648fd865a2af6a22cc2";
    private static final String TESTING_TEXT_SHA_384_VALUE
            = "4f37c49c0024445f91977dbc47bd4da9c4de8d173d03379ee19c2bb15435c2c7e624ea42f7cc1689961cb7aca50c7d17";
    private static final String TESTING_TEXT_SHA_512_VALUE
            = "7bfa95a688924c47c7d22381f20cc926f524beacb13f84e203d4bd8cb6ba2fce81c57a5f059bf3d509926487bde925b3bcee0635e4f7baeba054e5dba696b2bf";

    private static final String TESTING_FILE_MD_5_VALUE
            = "6344db3a6c0cfdc4a99881b5b16127f8";
    private static final String TESTING_FILE_SHA_1_VALUE
            = "13bda17771cb0bdb19d1640e9e68f2dcec583d39";
    private static final String TESTING_FILE_SHA_224_VALUE
            = "bf2483bff7724e6bc43ead3e247176a1855f137983e530d1c7b2dcfd";
    private static final String TESTING_FILE_SHA_256_VALUE
            = "55c7691d1c7fe593284981b58bc0d6268f086aa91d325aec9f8970ac523ea0ff";
    private static final String TESTING_FILE_SHA_384_VALUE
            = "3ec289df7fe5d9071122785d994599dda0c5d7cca60db27d1e0640ab2f7e94bb93d4200d4565dfc3aea98d12279bb1cb";
    private static final String TESTING_FILE_SHA_512_VALUE
            = "37c3308c934cd08cc66201666ade6501f8e0c788ae80c5d4553782bee20f89fd06dc277a2ecc7f8cc51ce69c5ad2989b830b85a4584b1c9e84b76bf6ed9d4c2e";

    private HashCalculator hashCalculator;
    private Context context;
    private Context targetContext;

    @Before
    public void setUp() {
        if (context == null) {
            context = InstrumentationRegistry.getContext();
            targetContext = InstrumentationRegistry.getTargetContext();
        }
    }

    @Test
    public void generateMD5() throws Exception {
        initializeHashCalculator(HashTypes.MD5);
        testTest(TESTING_TEXT_MD_5_VALUE);
        testFile(TESTING_FILE_MD_5_VALUE);
    }

    @Test
    public void generateSHA1() throws Exception {
        initializeHashCalculator(HashTypes.SHA_1);
        testTest(TESTING_TEXT_SHA_1_VALUE);
        testFile(TESTING_FILE_SHA_1_VALUE);
    }

    @Test
    public void generateSHA224() throws Exception {
        initializeHashCalculator(HashTypes.SHA_224);
        testTest(TESTING_TEXT_SHA_224_VALUE);
        testFile(TESTING_FILE_SHA_224_VALUE);
    }

    @Test
    public void generateSHA256() throws Exception {
        initializeHashCalculator(HashTypes.SHA_256);
        testTest(TESTING_TEXT_SHA_256_VALUE);
        testFile(TESTING_FILE_SHA_256_VALUE);
    }

    @Test
    public void generateSHA384() throws Exception {
        initializeHashCalculator(HashTypes.SHA_384);
        testTest(TESTING_TEXT_SHA_384_VALUE);
        testFile(TESTING_FILE_SHA_384_VALUE);
    }

    @Test
    public void generateSHA512() throws Exception {
        initializeHashCalculator(HashTypes.SHA_512);
        testTest(TESTING_TEXT_SHA_512_VALUE);
        testFile(TESTING_FILE_SHA_512_VALUE);
    }

    private void initializeHashCalculator(@NonNull HashTypes hashType) {
        hashCalculator = new HashCalculator(hashType.getTypeAsString(targetContext));
    }

    private void testTest(@NonNull String value) {
        String hashFromString = hashCalculator.generateFromString(TESTING_TEXT);
        assertEquals(hashFromString, value);
    }

    private void testFile(@NonNull String value) throws Exception {
        String hashFromFile = hashCalculator.generateFromFile(context.getResources().getAssets().open(TESTING_FILE));
        assertEquals(hashFromFile, value);
    }

}