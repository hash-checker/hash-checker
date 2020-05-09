package com.smlnskgmail.jaman.hashchecker.logic.feedback;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.logic.logs.L;

public class FeedbackFragment extends BaseFragment {

    private final String osVersion = Build.VERSION.RELEASE;
    private final String manufacturer = Build.MANUFACTURER;
    private final String model = Build.MODEL;

    private EditText feedbackEdit;

    private final Feedback feedback = new Feedback(
            BuildConfig.VERSION_NAME,
            BuildConfig.VERSION_CODE,
            osVersion,
            manufacturer,
            model
    );

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        feedbackEdit = view.findViewById(R.id.et_feedback_message);

        applyInfoToTextView(
                view,
                R.id.tv_hash_checker_value,
                feedback.getAppInfo()
        );
        applyInfoToTextView(
                view,
                R.id.tv_android_value,
                osVersion
        );
        applyInfoToTextView(
                view,
                R.id.tv_manufacturer_value,
                manufacturer
        );
        applyInfoToTextView(
                view,
                R.id.tv_model_value,
                model
        );
    }

    private void applyInfoToTextView(
            @NonNull View contentView,
            int textViewResId,
            @NonNull String info
    ) {
        ((TextView) contentView.findViewById(textViewResId)).setText(info);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.menu_action_send_feedback) {
            sendEmail(
                    feedback.getConfiguredMessage(
                            feedbackEdit.getText().toString()
                    ),
                    getString(R.string.common_email)
            );
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendEmail(
            @NonNull String text,
            @NonNull String email
    ) {
        Intent emailIntent = new Intent(
                Intent.ACTION_SEND
        );
        emailIntent.putExtra(
                Intent.EXTRA_EMAIL,
                new String[] { email }
        );

        String subject = getString(R.string.common_app_name);
        emailIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                subject
        );
        emailIntent.putExtra(
                Intent.EXTRA_TEXT,
                text
        );

        Intent selectorIntent = new Intent(
                Intent.ACTION_SENDTO
        );
        selectorIntent.setData(
                Uri.parse(
                        "mailto:"
                )
        );
        emailIntent.setSelector(
            selectorIntent
        );

        String chooseMessage = String.format(
                "%s:",
                getString(R.string.message_email_app_chooser)
        );

        try {
            startActivity(
                    Intent.createChooser(
                            emailIntent,
                            chooseMessage
                    )
            );
        } catch (ActivityNotFoundException e) {
            L.e(e);
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
