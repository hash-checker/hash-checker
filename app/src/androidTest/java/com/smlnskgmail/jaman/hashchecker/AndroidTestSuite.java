package com.smlnskgmail.jaman.hashchecker;

import com.smlnskgmail.jaman.hashchecker.hashcalculator.ZeroLeadsHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.hashcalculator.crc.CRC32HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.hashcalculator.md.MD5HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.hashcalculator.sha.SHA1HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.hashcalculator.sha.SHA224HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.hashcalculator.sha.SHA256HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.hashcalculator.sha.SHA384HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.hashcalculator.sha.SHA512HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.ui.functionality.GenerateHashFromTextTest;
import com.smlnskgmail.jaman.hashchecker.ui.screenrunner.ScreenRunnerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MD5HashCalculatorTest.class,
        SHA1HashCalculatorTest.class,
        SHA224HashCalculatorTest.class,
        SHA256HashCalculatorTest.class,
        SHA384HashCalculatorTest.class,
        SHA512HashCalculatorTest.class,
        CRC32HashCalculatorTest.class,
        ZeroLeadsHashCalculatorTest.class,
        ScreenRunnerTest.class,
        GenerateHashFromTextTest.class
})
public class AndroidTestSuite {}
