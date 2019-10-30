package com.smlnskgmail.jaman.hashchecker.logic.feedback;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.tools.UITools;

public class FeedbackFragment extends BaseFragment {

    private static final String TITLE_TEXT_PATTERN = "%s:";

    private CheckBox addDeviceInfo;

    private EditText feedbackEdit;

    private TextView manufacturerTitle;
    private TextView manufacturerValue;
    private TextView modelTitle;
    private TextView modelValue;

    private Context context;

    @Override
    public void initializeUI(@NonNull final View contentView) {
        context = getContext();

        addDeviceInfo = contentView.findViewById(R.id.cb_add_device_info);
        addDeviceInfo.setOnCheckedChangeListener((buttonView, isChecked) -> addDeviceInfo(isChecked));

        feedbackEdit = contentView.findViewById(R.id.et_feedback_message);
        manufacturerTitle = contentView.findViewById(R.id.tv_manufacturer_title);
        manufacturerValue = contentView.findViewById(R.id.tv_manufacturer_value);
        modelTitle = contentView.findViewById(R.id.tv_model_title);
        modelValue = contentView.findViewById(R.id.tv_model_value);

        ((TextView) contentView.findViewById(R.id.tv_device_info_title))
                .setText(String.format(TITLE_TEXT_PATTERN, getString(R.string.feedback_device)));
        manufacturerTitle.setText(String.format(TITLE_TEXT_PATTERN, getString(R.string.feedback_manufacturer)));
        modelTitle.setText(String.format(TITLE_TEXT_PATTERN, getString(R.string.feedback_model)));

        manufacturerValue.setText(Build.MANUFACTURER);
        modelValue.setText(Build.MODEL);
    }

    private void addDeviceInfo(boolean addInfo) {
        int titleColor = addInfo ? UITools.getDarkTextColor(context) : UITools.getUnselectedColor(context);
        manufacturerTitle.setTextColor(titleColor);
        modelTitle.setTextColor(titleColor);

        manufacturerValue.setTextColor(titleColor);
        modelValue.setTextColor(titleColor);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.menu_action_send_feedback) {
            StringBuilder feedbackText = new StringBuilder(feedbackEdit.getText().toString());
            feedbackText.append("\n\n").append(String.format("%s (%s)", BuildConfig.VERSION_NAME,
                    BuildConfig.VERSION_CODE));
            if (addDeviceInfo.isChecked()) {
                String osVersion = Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();
                feedbackText.append("\n\n").append(String.format("%s %s (%s)", Build.MANUFACTURER,
                        Build.MODEL, osVersion));
            }
            sendEmail(feedbackText.toString(), getString(R.string.common_email));
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendEmail(@NonNull String text, @NonNull String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.common_app_name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);
        String message = String.format("%s:", context.getString(R.string.message_email_app_chooser));
        context.startActivity(Intent.createChooser(emailIntent, message));
    }

    @Override
    public int getActionBarTitleResId() {
        return R.string.menu_title_feedback;
    }

    @Override
    public boolean setBackActionIcon() {
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
