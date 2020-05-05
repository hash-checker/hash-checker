package com.smlnskgmail.jaman.hashchecker.feedback;

import android.os.Build;

import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;
import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.logic.feedback.Feedback;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FeedbackTest {

    private String appVersionName = BuildConfig.VERSION_NAME;
    private int appVersionCode = BuildConfig.VERSION_CODE;

    private String osVersion = Build.VERSION.RELEASE;
    private String manufacturer = Build.MANUFACTURER;
    private String model = Build.MODEL;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class
    );

    @Test
    public void validateFeedbackData() {
        Feedback feedback = new Feedback();

        assertEquals(
                String.format(
                        "%s (%s)",
                        appVersionName,
                        appVersionCode
                ),
                feedback.getAppInfo()
        );
        assertEquals(
                osVersion,
                feedback.getOsVersion()
        );
        assertEquals(
                manufacturer,
                feedback.getManufacturer()
        );
        assertEquals(
                model,
                feedback.getModel()
        );
    }

    @Test
    public void validateFeedbackMessage() {
        String message = "Feedback";
        String feedbackMessage = String.format(
                "%s" +
                        "\n\n\n%s (%s)" +
                        "\nAndroid %s" +
                        "\n%s" +
                        "\n%s",
                message,
                appVersionName,
                appVersionCode,
                osVersion,
                manufacturer,
                model
        );

        assertEquals(
                feedbackMessage,
                new Feedback().getConfiguredMessage(
                        message
                )
        );
    }

}
