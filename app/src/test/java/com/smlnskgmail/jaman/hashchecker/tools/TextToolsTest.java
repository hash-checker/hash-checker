package com.smlnskgmail.jaman.hashchecker.tools;

import com.smlnskgmail.jaman.hashchecker.logic.calculator.ui.TextTools;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TextToolsTest {

    @Test
    public void validateEqualsStringsTest() {
        String singleString = "equals";

        assertTrue(TextTools.compareText(singleString, singleString));
    }

    @Test
    public void validateEqualsStringsWithDifferentCasesTest() {
        String firstString = "Blob";
        String secondString = "BLOB";

        assertTrue(TextTools.compareText(firstString, secondString));
    }

    @Test
    public void validateNotEqualsStringsTest() {
        String firstString = "equals";
        String secondString = "blob";

        assertFalse(TextTools.compareText(firstString, secondString));
    }

}
