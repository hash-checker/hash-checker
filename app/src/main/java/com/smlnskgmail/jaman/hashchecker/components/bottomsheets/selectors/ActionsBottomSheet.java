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

public class ActionsBottomSheet extends BaseBottomSheet {

    @BindView(R.id.button_generate)
    protected TextView buttonGenerate;

    @BindView(R.id.button_compare)
    protected TextView buttonCompare;

    private OnUserActionClickListener menuItemCallback;

    public void setMenuItemCallback(@NonNull OnUserActionClickListener menuItemCallback) {
        this.menuItemCallback = menuItemCallback;
    }

    @Override
    public void initUI() {
        Context context = getContext();
        UIUtils.colorizeImageSourceToAccentColor(context, buttonGenerate.getCompoundDrawablesRelative()[0]);
        UIUtils.colorizeImageSourceToAccentColor(context, buttonCompare.getCompoundDrawablesRelative()[0]);
        buttonGenerate.setOnClickListener(v -> selectAction(UserActionTypes.GENERATE_HASH));
        buttonCompare.setOnClickListener(v -> selectAction(UserActionTypes.COMPARE_HASHES));
    }

    private void selectAction(@NonNull UserActionTypes userActionType) {
        menuItemCallback.onUserActionClick(userActionType);
        dismiss();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_actions;
    }

}
