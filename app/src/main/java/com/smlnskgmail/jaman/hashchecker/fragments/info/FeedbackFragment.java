package com.smlnskgmail.jaman.hashchecker.fragments.info;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.fragments.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.support.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

public class FeedbackFragment extends BaseFragment {

    @BindView(R.id.cb_add_device_info)
    protected CheckBox addDeviceInfo;

    @BindView(R.id.tv_feedback_message)
    protected EditText feedbackEdit;

    @BindView(R.id.tv_manufacturer_title)
    protected TextView manufacturerTitle;

    @BindView(R.id.tv_manufacturer_value)
    protected TextView manufacturerValue;

    @BindView(R.id.tv_model_title)
    protected TextView modelTitle;

    @BindView(R.id.tv_model_value)
    protected TextView modelValue;

    private Context context;

    @OnCheckedChanged(R.id.cb_add_device_info)
    public void addDeviceInfo(boolean addInfo) {
        int titleColor = addInfo ? UIUtils.getDarkTextColor(context)
                : UIUtils.getUnselectedColor(context);
        manufacturerTitle.setTextColor(titleColor);
        modelTitle.setTextColor(titleColor);

        manufacturerValue.setTextColor(titleColor);
        modelValue.setTextColor(titleColor);
    }

    @Override
    public void initializeUI(@NonNull final View view) {
        context = getContext();
        manufacturerValue.setText(Build.MANUFACTURER);
        modelValue.setText(Build.MODEL);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.menu_action_send_feedback) {
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
    public int[] getMenuItemsIds() {
        return new int[0];
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_feedback;
    }

}
