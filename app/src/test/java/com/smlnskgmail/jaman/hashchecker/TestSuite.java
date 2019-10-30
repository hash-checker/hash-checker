package com.smlnskgmail.jaman.hashchecker;

import com.smlnskgmail.jaman.hashchecker.entities.FileItemTest;
import com.smlnskgmail.jaman.hashchecker.entities.HistoryItemTest;
import com.smlnskgmail.jaman.hashchecker.tools.HashToolsTest;
import com.smlnskgmail.jaman.hashchecker.tools.TextToolsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HashToolsTest.class,
        TextToolsTest.class,
        FileItemTest.class,
        HistoryItemTest.class
})
public class TestSuite {}
