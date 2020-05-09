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

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class
    );

    @Test
    public void validateFeedbackMessage() {
        String appVersionName = BuildConfig.VERSION_NAME;
        int appVersionCode = BuildConfig.VERSION_CODE;

        String osVersion = Build.VERSION.RELEASE;
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

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
                new Feedback(
                        appVersionName,
                        appVersionCode,
                        osVersion,
                        manufacturer,
                        model
                ).getConfiguredMessage(
                        message
                )
        );
    }

}
