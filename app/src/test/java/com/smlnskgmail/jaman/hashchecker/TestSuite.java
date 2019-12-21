package com.smlnskgmail.jaman.hashchecker;

import com.smlnskgmail.jaman.hashchecker.calculator.HashTypeTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.JDKHashCalculatorDigestTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.JDKHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.JDKHashToolsTest;
import com.smlnskgmail.jaman.hashchecker.filemanager.FileItemTest;
import com.smlnskgmail.jaman.hashchecker.history.HistoryItemTest;
import com.smlnskgmail.jaman.hashchecker.tools.TextToolsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JDKHashToolsTest.class,
        HashTypeTest.class,
        JDKHashCalculatorDigestTest.class,
        JDKHashCalculatorTest.class,
        TextToolsTest.class,
        FileItemTest.class,
        HistoryItemTest.class
})
public class TestSuite {}
