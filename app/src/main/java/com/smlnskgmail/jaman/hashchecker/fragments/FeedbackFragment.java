package com.smlnskgmail.jaman.hashchecker.fragments;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.support.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

public class FeedbackFragment extends BaseFragment {

    @BindView(R.id.add_device_info)
    protected CheckBox addDeviceInfo;

    @BindView(R.id.feedback_text)
    protected EditText feedbackEdit;

    @BindView(R.id.manufacturer_title)
    protected TextView manufacturerTitle;

    @BindView(R.id.manufacturer_value)
    protected TextView manufacturerValue;

    @BindView(R.id.model_title)
    protected TextView modelTitle;

    @BindView(R.id.model_value)
    protected TextView modelValue;

    private Context context;

    @OnCheckedChanged(R.id.add_device_info)
    public void addDeviceInfo(boolean addInfo) {
        int titleColor = addInfo ? UIUtils.getDarkTextColor(context)
                : UIUtils.getUnselectedColor(context);
        manufacturerTitle.setTextColor(titleColor);
        modelTitle.setTextColor(titleColor);

        manufacturerValue.setTextColor(titleColor);
        modelValue.setTextColor(titleColor);
    }

    @Override
    void initializeUI(@NonNull final View view) {
        context = getContext();
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setHomeAsUpIndicator(ContextCompat.getDrawable(context, R.drawable.ic_close));
        manufacturerValue.setText(Build.MANUFACTURER);
        modelValue.setText(Build.MODEL);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.send_feedback) {
            StringBuilder feedbackText = new StringBuilder(feedbackEdit.getText().toString());
            feedbackText.append("\n\n").append(String.format("%s (%s)", BuildConfig.VERSION_NAME,
                    BuildConfig.VERSION_CODE));
            if (addDeviceInfo.isChecked()) {
                feedbackText.append("\n\n").append(String.format("%s %s (%s)", Build.MANUFACTURER,
                        Build.MODEL,
                        Build.VERSION_CODES.class.getFields()
                                [android.os.Build.VERSION.SDK_INT].getName()));
            }
            AppUtils.sendFeedback(context, feedbackText.toString(), getString(R.string.common_email));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBack() {
        UIUtils.removeFragment(getActivity().getSupportFragmentManager(), this);
    }

    @Override
    int getActionBarTitleResId() {
        return R.string.menu_title_feedback;
    }

    @Override
    boolean setBackActionIcon() {
        return true;
    }

    @Override
    int getMenuResId() {
        return R.menu.menu_feedback;
    }

    @Override
    int[] getMenuItemsIds() {
        return new int[0];
    }

    @Override
    int getLayoutResId() {
        return R.layout.fragment_feedback;
    }

}
