package com.smlnskgmail.jaman.hashchecker;

import com.smlnskgmail.jaman.hashchecker.calculator.GenerateHashFromTextTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.crc.CRC32JDKHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.md.MD5JDKHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA1JDKHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA224JDKHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA256JDKHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA384JDKHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA512JDKHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.zeroleads.MessageDigestZeroLeadsJDKHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.screenrunner.ScreenRunnerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MD5JDKHashCalculatorTest.class,
        SHA1JDKHashCalculatorTest.class,
        SHA224JDKHashCalculatorTest.class,
        SHA256JDKHashCalculatorTest.class,
        SHA384JDKHashCalculatorTest.class,
        SHA512JDKHashCalculatorTest.class,
        CRC32JDKHashCalculatorTest.class,
        MessageDigestZeroLeadsJDKHashCalculatorTest.class,
        GenerateHashFromTextTest.class,
        ScreenRunnerTest.class
})
public class AndroidTestSuite {}
