package com.smlnskgmail.jaman.hashchecker;

import com.smlnskgmail.jaman.hashchecker.calculator.jdk.md.MD5JDKHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.screenrunner.ScreenRunnerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MD5JDKHashCalculatorTest.class,
        ScreenRunnerTest.class
})
public class FastTestSuite {}
