package com.smlnskgmail.jaman.hashchecker;

import com.smlnskgmail.jaman.hashchecker.appopen.OpenAppWithClipDataTest;
import com.smlnskgmail.jaman.hashchecker.appopen.OpenAppWithIntentTest;
import com.smlnskgmail.jaman.hashchecker.calculator.GenerateHashFromFileTest;
import com.smlnskgmail.jaman.hashchecker.calculator.GenerateHashFromTextTest;
import com.smlnskgmail.jaman.hashchecker.calculator.HashCalculatorTaskExceptionTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.crc.CRC32JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.exceptions.JdkHashCalculatorExceptionsTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.md.MD5JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA1JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA224JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA256JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA384JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.sha.SHA512JdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.calculator.jdk.zeroleads.MessageDigestZeroLeadsJdkHashCalculatorTest;
import com.smlnskgmail.jaman.hashchecker.database.DatabaseExporterTest;
import com.smlnskgmail.jaman.hashchecker.database.HelperFactoryTest;
import com.smlnskgmail.jaman.hashchecker.feedback.FeedbackTest;
import com.smlnskgmail.jaman.hashchecker.history.HistoryScrollTest;
import com.smlnskgmail.jaman.hashchecker.history.HistoryTest;
import com.smlnskgmail.jaman.hashchecker.rateapp.RateAppDialogTest;
import com.smlnskgmail.jaman.hashchecker.screenrunner.ScreenRunnerTest;

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
        JdkHashCalculatorExceptionsTest.class,
        MessageDigestZeroLeadsJdkHashCalculatorTest.class,
//        GenerateHashFromFileTest.class,
        GenerateHashFromTextTest.class,
        HashCalculatorTaskExceptionTest.class,
        OpenAppWithIntentTest.class,
        OpenAppWithClipDataTest.class,
        HistoryTest.class,
        HistoryScrollTest.class,
        FeedbackTest.class,
        RateAppDialogTest.class,
        DatabaseExporterTest.class,
//        HashValueInUpperCaseTest.class,
//        HashValueInLowerCaseTest.class,
        ScreenRunnerTest.class,
        HelperFactoryTest.class,
})
public class AndroidTestSuite {

}
