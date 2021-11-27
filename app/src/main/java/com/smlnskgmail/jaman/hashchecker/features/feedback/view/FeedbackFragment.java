package com.smlnskgmail.jaman.hashchecker.features.feedback.view;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.features.feedback.presenter.FeedbackPresenter;
import com.smlnskgmail.jaman.hashchecker.features.feedback.presenter.FeedbackPresenterImpl;
import com.smlnskgmail.jaman.hashchecker.ui.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.utils.LogUtils;

import javax.inject.Inject;

public class FeedbackFragment extends BaseFragment implements FeedbackView {

    @Inject
    public LanguageConfig languageConfig;

    private FeedbackPresenter feedbackPresenter;

    private EditText etFeedbackMessage;
    private View contentView;

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        etFeedbackMessage = view.findViewById(R.id.et_feedback_message);
        this.contentView = view;
        feedbackPresenter = new FeedbackPresenterImpl();
        feedbackPresenter.init(this);
    }

    @NonNull
    @Override
    protected LanguageConfig langHelper() {
        return languageConfig;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Activity activity = getActivity();
            if (activity != null) {
                activity.onBackPressed();
            }
            return true;
        } else if (item.getItemId() == R.id.menu_action_send_feedback) {
            String feedbackMessage = etFeedbackMessage.getText().toString();
            if (!feedbackMessage.isEmpty()) {
                sendEmail(feedbackMessage, getString(R.string.common_email));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendEmail(@NonNull String text, @NonNull String email) {
        String subject = getString(R.string.common_app_name);
        String chooseMessage = String.format(
                "%s:",
                getString(R.string.message_email_app_chooser)
        );
        Intent emailIntent = feedbackPresenter.prepareEmailIntent(
                text,
                email,
                subject
        );
        try {
            startActivity(
                    Intent.createChooser(
                            emailIntent,
                            chooseMessage
                    )
            );
        } catch (ActivityNotFoundException e) {
            Activity activity = getActivity();
            if (activity != null) {
                LogUtils.e(e);
                ShareCompat.IntentBuilder
                        .from(getActivity())
                        .setText("message/rfc822")
                        .addEmailTo(email)
                        .setSubject(subject)
                        .setText(text)
                        .setChooserTitle(chooseMessage)
                        .startChooser();
            }
        }
    }

    @Override
    public void showAppInfo(@NonNull String appInfo) {
        ((TextView) contentView.findViewById(R.id.tv_hash_checker_value)).setText(appInfo);
    }

    @Override
    public void showAndroidVersion(@NonNull String version) {
        ((TextView) contentView.findViewById(R.id.tv_android_value)).setText(version);
    }

    @Override
    public void showManufacturer(@NonNull String manufacturer) {
        ((TextView) contentView.findViewById(R.id.tv_manufacturer_value)).setText(manufacturer);
    }

    @Override
    public void showModel(@NonNull String model) {
        ((TextView) contentView.findViewById(R.id.tv_model_value)).setText(model);
    }

    @Override
    public int getActionBarTitleResId() {
        return R.string.menu_title_feedback;
    }

    @Override
    public boolean setAllowBackAction() {
        return true;
    }

    @Override
    public int getBackActionIconResId() {
        return R.drawable.ic_close;
    }

    @Override
    public int getMenuResId() {
        return R.menu.menu_feedback;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_feedback;
    }

}
