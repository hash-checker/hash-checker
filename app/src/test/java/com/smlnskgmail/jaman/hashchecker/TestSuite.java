package com.smlnskgmail.jaman.hashchecker;

import com.smlnskgmail.jaman.hashchecker.calculator.HashTypeTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.JdkHashCalculatorDigestTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.JdkHashToolsTest;
import com.smlnskgmail.jaman.hashchecker.entities.DbEntityTest;
import com.smlnskgmail.jaman.hashchecker.filemanager.FileItemTest;
import com.smlnskgmail.jaman.hashchecker.history.HistoryItemTest;
import com.smlnskgmail.jaman.hashchecker.tools.TextToolsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JdkHashToolsTest.class,
        HashTypeTest.class,
        JdkHashCalculatorDigestTest.class,
        JdkHashCalculatorTest.class,
        TextToolsTest.class,
        DbEntityTest.class,
        FileItemTest.class,
        HistoryItemTest.class
})
public class TestSuite {}
