package com.smlnskgmail.jaman.hashchecker;

import com.smlnskgmail.jaman.hashchecker.calculator.GenerateHashFromTextTest;
import com.smlnskgmail.jaman.hashchecker.calculator.HashCalculatorTaskExceptionTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.blake2b.Blake2bJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.crc.CRC32JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.exceptions.JdkHashCalculatorExceptionsTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a.FNV1A1024HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a.FNV1A128HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a.FNV1A256HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a.FNV1A32HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a.FNV1A512HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.fnv1a.FNV1A64HashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.md.MD5JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA1JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA224JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA256JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA384JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA512JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.zeroleads.MessageDigestZeroLeadsJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.database.LocalDataExporterTest;
import com.smlnskgmail.jaman.hashchecker.feedback.FeedbackTest;
import com.smlnskgmail.jaman.hashchecker.rateapp.RateAppDialogTest;
import com.smlnskgmail.jaman.hashchecker.screenrunner.ScreenRunnerTest;
import com.smlnskgmail.jaman.hashchecker.settings.HashValueInUpperCaseTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MD5JdkHashCalculatorTest.class,
        SHA1JdkHashCalculatorTest.class,
        SHA224JdkHashCalculatorTest.class,
        SHA256JdkHashCalculatorTest.class,
        SHA384JdkHashCalculatorTest.class,
        SHA512JdkHashCalculatorTest.class,
        CRC32JdkHashCalculatorTest.class,
        Blake2bJdkHashCalculatorTest.class,
        FNV1A32HashCalculatorTest.class,
        FNV1A64HashCalculatorTest.class,
        FNV1A128HashCalculatorTest.class,
        FNV1A256HashCalculatorTest.class,
        FNV1A512HashCalculatorTest.class,
        FNV1A1024HashCalculatorTest.class,
        JdkHashCalculatorExceptionsTest.class,
        MessageDigestZeroLeadsJdkHashCalculatorTest.class,
        GenerateHashFromTextTest.class,
        HashCalculatorTaskExceptionTest.class,
        FeedbackTest.class,
        RateAppDialogTest.class,
        LocalDataExporterTest.class,
        HashValueInUpperCaseTest.class,
        ScreenRunnerTest.class,
})
public class AndroidTestSuite {

}
