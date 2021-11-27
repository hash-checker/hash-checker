package com.smlnskgmail.jaman.hashchecker.calculator.jdk.exceptions;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.impl.jdk.JdkHashCalculator;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.Theme;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class JdkHashCalculatorExceptionsTest {

    @Test
    public void runTest() throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculator jdkHashCalculator = new JdkHashCalculator(
                new SettingsHelperMock()
        );
        jdkHashCalculator.setHashType(HashType.MD5);
        assertNull(
                jdkHashCalculator.fromFile(
                        new InputStreamMock()
                )
        );
        assertNull(jdkHashCalculator.fromFile(null));
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertNull(
                jdkHashCalculator.fromFile(
                        context,
                        Uri.fromFile(new File(""))
                )
        );
    }

}

class InputStreamMock extends InputStream {
    @Override
    public int read() throws IOException {
        throw new IOException();
    }
}

class SettingsHelperMock implements SettingsHelper {

    @Override
    public void saveHashTypeAsLast(@NonNull HashType hashType) {

    }

    @NonNull
    @Override
    public HashType getLastHashType() {
        return null;
    }

    @Override
    public boolean languageIsInitialized() {
        return false;
    }

    @Override
    public void saveLanguage(@NonNull Language language) {

    }

    @NonNull
    @Override
    public Language getLanguage() {
        return null;
    }

    @Override
    public boolean isUsingInnerFileManager() {
        return false;
    }

    @Override
    public void savePathForInnerFileManager(@Nullable String path) {

    }

    @Override
    public boolean isUsingMultilineHashFields() {
        return false;
    }

    @Override
    public boolean canSaveResultToHistory() {
        return false;
    }

    @Override
    public boolean getVibrateAccess() {
        return false;
    }

    @NonNull
    @Override
    public Theme getSelectedTheme() {
        return null;
    }

    @Override
    public void saveTheme(@NonNull Theme theme) {

    }

    @Override
    public boolean useUpperCase() {
        return false;
    }

    @Override
    public boolean isShortcutsIsCreated() {
        return false;
    }

    @Override
    public void saveShortcutsStatus(boolean value) {

    }

    @Override
    public boolean getGenerateFromShareIntentStatus() {
        return false;
    }

    @Override
    public void setGenerateFromShareIntentMode(boolean status) {

    }

    @Override
    public boolean refreshSelectedFile() {
        return false;
    }

    @Override
    public void setRefreshSelectedFileStatus(boolean status) {

    }

    @Override
    public boolean canShowRateAppDialog() {
        return false;
    }

    @Override
    public void increaseHashGenerationCount() {

    }

}
