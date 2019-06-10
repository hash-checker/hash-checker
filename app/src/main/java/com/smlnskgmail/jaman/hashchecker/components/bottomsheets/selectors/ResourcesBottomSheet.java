package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.OnUserActionClickListener;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.UserActionTypes;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public class ResourcesBottomSheet extends BaseBottomSheet {

    protected TextView btnSelectText;
    protected TextView btnSelectFile;

    private OnUserActionClickListener menuItemCallback;

    public void setMenuItemCallback(@NonNull OnUserActionClickListener menuItemCallback) {
        this.menuItemCallback = menuItemCallback;
    }

    @Override
    public void initUI(@NonNull View contentView) {
        Context context = getContext();

        btnSelectText = contentView.findViewById(R.id.tv_as_btn_open_file_input_dialog);
        btnSelectText.setOnClickListener(v -> selectAction(UserActionTypes.ENTER_TEXT));
        UIUtils.colorizeImageSourceToAccentColor(context, btnSelectText.getCompoundDrawablesRelative()[0]);

        btnSelectFile = contentView.findViewById(R.id.tv_as_btn_open_file_selector);
        btnSelectFile.setOnClickListener(v -> selectAction(UserActionTypes.SEARCH_FILE));
        UIUtils.colorizeImageSourceToAccentColor(context, btnSelectFile.getCompoundDrawablesRelative()[0]);
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
