package com.smlnskgmail.jaman.hashchecker;

import com.smlnskgmail.jaman.hashchecker.calculator.HashCalculatorDigestTest;
import com.smlnskgmail.jaman.hashchecker.calculator.HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.HashTypeTest;
import com.smlnskgmail.jaman.hashchecker.calculator.tools.HashToolsTest;
import com.smlnskgmail.jaman.hashchecker.filemanager.FileItemTest;
import com.smlnskgmail.jaman.hashchecker.history.HistoryItemTest;
import com.smlnskgmail.jaman.hashchecker.tools.TextToolsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HashToolsTest.class,
        HashTypeTest.class,
        HashCalculatorDigestTest.class,
        HashCalculatorTest.class,
        TextToolsTest.class,
        FileItemTest.class,
        HistoryItemTest.class
})
public class TestSuite {}
