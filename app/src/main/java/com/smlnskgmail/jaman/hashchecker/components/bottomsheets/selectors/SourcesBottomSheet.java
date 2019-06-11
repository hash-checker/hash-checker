package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.OnUserActionClickListener;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.UserActionType;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public class SourcesBottomSheet extends BaseBottomSheet {

    private OnUserActionClickListener menuItemCallback;

    public void setMenuItemCallback(@NonNull OnUserActionClickListener menuItemCallback) {
        this.menuItemCallback = menuItemCallback;
    }

    @Override
    public void initUI(@NonNull View contentView) {
        initializeSourceButton(contentView, R.id.tv_as_btn_open_file_input_dialog,
                UserActionType.ENTER_TEXT);
        initializeSourceButton(contentView, R.id.tv_as_btn_open_file_selector,
                UserActionType.SEARCH_FILE);
    }

    private void initializeSourceButton(@NonNull View contentView, int buttonResId,
                                        @NonNull UserActionType userActionType) {
        TextView btnSelectFile = contentView.findViewById(buttonResId);
        btnSelectFile.setOnClickListener(v -> selectAction(userActionType));
        UIUtils.colorizeImageSourceToAccentColor(contentView.getContext(),
                btnSelectFile.getCompoundDrawablesRelative()[0]);
    }

    private void selectAction(@NonNull UserActionType userActionType) {
        menuItemCallback.onUserActionClick(userActionType);
        dismiss();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_from;
    }

}
