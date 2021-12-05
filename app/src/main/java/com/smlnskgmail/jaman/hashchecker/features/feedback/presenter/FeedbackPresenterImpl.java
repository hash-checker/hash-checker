package com.smlnskgmail.jaman.hashchecker.features.feedback.presenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;
import com.smlnskgmail.jaman.hashchecker.features.feedback.Feedback;
import com.smlnskgmail.jaman.hashchecker.features.feedback.view.FeedbackView;

public class FeedbackPresenterImpl implements FeedbackPresenter {

    private static final Feedback feedback = new Feedback(
            BuildConfig.VERSION_NAME,
            BuildConfig.VERSION_CODE,
            Build.VERSION.RELEASE,
            Build.MANUFACTURER,
            Build.MODEL
    );

    private FeedbackView view;

    @Override
    public void init(@NonNull FeedbackView view) {
        this.view = view;
        view.showAppInfo(feedback.getAppInfo());
        view.showAndroidVersion(feedback.osVersion());
        view.showManufacturer(feedback.manufacturer());
        view.showModel(feedback.model());
    }

    @Override
    public void sendFeedback(@NonNull String email, @NonNull String text, @NonNull String subject) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, feedback.getConfiguredMessage(text));

        Intent selectorIntent = new Intent(Intent.ACTION_SENDTO);
        selectorIntent.setData(Uri.parse("mailto:"));
        emailIntent.setSelector(selectorIntent);

        view.sendEmail(emailIntent);
    }

}
