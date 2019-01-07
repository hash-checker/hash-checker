package com.smlnskgmail.jaman.hashchecker.generator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class HashCalculatorTest {

    private static final String TESTING_TEXT = "TEST"; // or use "iwrupvqb346386" for test zero leads

    private static final String TESTING_TEXT_MD_5_VALUE = "033bd94b1168d7e4f0d644c3c95e35bf";
    private static final String TESTING_TEXT_SHA_1_VALUE = "984816fd329622876e14907634264e6f332e9fb3";
    private static final String TESTING_TEXT_SHA_224_VALUE = "917ecca24f3e6ceaf52375d8083381f1f80a21e6e49fbadc40afeb8e";
    private static final String TESTING_TEXT_SHA_256_VALUE = "94ee059335e587e501cc4bf90613e0814f00a7b08bc7c648fd865a2af6a22cc2";
    private static final String TESTING_TEXT_SHA_384_VALUE = "4f37c49c0024445f91977dbc47bd4da9c4de8d173d03379ee19c2bb15435c2c7e624ea42f7cc1689961cb7aca50c7d17";
    private static final String TESTING_TEXT_SHA_512_VALUE = "7bfa95a688924c47c7d22381f20cc926f524beacb13f84e203d4bd8cb6ba2fce81c57a5f059bf3d509926487bde925b3bcee0635e4f7baeba054e5dba696b2bf";

    private HashCalculator hashCalculator;
    private Context context;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void generateMD5() {
        hashCalculator = new HashCalculator(HashTypes.MD5.getTypeAsString(context));
        String hashFromString = hashCalculator.generateFromString(TESTING_TEXT);
        assertEquals(hashFromString, TESTING_TEXT_MD_5_VALUE);
    }

    @Test
    public void generateSHA1() {
        hashCalculator = new HashCalculator(HashTypes.SHA_1.getTypeAsString(context));
        String hashFromString = hashCalculator.generateFromString(TESTING_TEXT);
        assertEquals(hashFromString, TESTING_TEXT_SHA_1_VALUE);
    }

    @Test
    public void generateSHA224() {
        hashCalculator = new HashCalculator(HashTypes.SHA_224.getTypeAsString(context));
        String hashFromString = hashCalculator.generateFromString(TESTING_TEXT);
        assertEquals(hashFromString, TESTING_TEXT_SHA_224_VALUE);
    }

    @Test
    public void generateSHA256() {
        hashCalculator = new HashCalculator(HashTypes.SHA_256.getTypeAsString(context));
        String hashFromString = hashCalculator.generateFromString(TESTING_TEXT);
        assertEquals(hashFromString, TESTING_TEXT_SHA_256_VALUE);
    }

    @Test
    public void generateSHA384() {
        hashCalculator = new HashCalculator(HashTypes.SHA_384.getTypeAsString(context));
        String hashFromString = hashCalculator.generateFromString(TESTING_TEXT);
        assertEquals(hashFromString, TESTING_TEXT_SHA_384_VALUE);
    }

    @Test
    public void generateSHA512() {
        hashCalculator = new HashCalculator(HashTypes.SHA_512.getTypeAsString(context));
        String hashFromString = hashCalculator.generateFromString(TESTING_TEXT);
        assertEquals(hashFromString, TESTING_TEXT_SHA_512_VALUE);
    }

}