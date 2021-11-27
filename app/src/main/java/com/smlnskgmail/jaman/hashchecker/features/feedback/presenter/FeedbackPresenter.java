package com.smlnskgmail.jaman.hashchecker.features.feedback.presenter;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.features.feedback.view.FeedbackView;

public interface FeedbackPresenter {

    void init(@NonNull FeedbackView view);

    @NonNull
    Intent prepareEmailIntent(
            @NonNull String email,
            @NonNull String text,
            @NonNull String subject
    );

}
