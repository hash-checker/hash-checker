package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.OnUserActionClickListener;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.UserActionTypes;
import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;

import butterknife.BindView;

public class ResourcesBottomSheet extends BaseBottomSheet {

    @BindView(R.id.tv_as_btn_open_file_input_dialog)
    protected TextView buttonSelectText;

    @BindView(R.id.tv_as_btn_open_file_selector)
    protected TextView buttonSelectFile;

    private OnUserActionClickListener menuItemCallback;

    public void setMenuItemCallback(@NonNull OnUserActionClickListener menuItemCallback) {
        this.menuItemCallback = menuItemCallback;
    }

    @Override
    public void initUI() {
        Context context = getContext();
        UIUtils.colorizeImageSourceToAccentColor(context, buttonSelectText.getCompoundDrawablesRelative()[0]);
        UIUtils.colorizeImageSourceToAccentColor(context, buttonSelectFile.getCompoundDrawablesRelative()[0]);
        buttonSelectText.setOnClickListener(v -> selectAction(UserActionTypes.ENTER_TEXT));
        buttonSelectFile.setOnClickListener(v -> selectAction(UserActionTypes.SEARCH_FILE));
    }

    private void selectAction(@NonNull UserActionTypes userActionType) {
        menuItemCallback.onUserActionClick(userActionType);
        dismiss();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_from;
    }

}
