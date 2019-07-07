package com.smlnskgmail.jaman.hashchecker;

import com.smlnskgmail.jaman.hashchecker.entities.FileItemTest;
import com.smlnskgmail.jaman.hashchecker.entities.HistoryItemTest;
import com.smlnskgmail.jaman.hashchecker.utils.HashUtilsTest;
import com.smlnskgmail.jaman.hashchecker.utils.TextUtilsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HashUtilsTest.class,
        TextUtilsTest.class,
        FileItemTest.class,
        HistoryItemTest.class
})
public class TestSuite {}
