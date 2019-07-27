package com.smlnskgmail.jaman.hashchecker.ui;

import com.smlnskgmail.jaman.hashchecker.hashcalculator.md.MD5HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.ui.screenrunner.ScreenRunnerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MD5HashCalculatorTest.class,
        ScreenRunnerTest.class
})
public class FastTestSuite {}
