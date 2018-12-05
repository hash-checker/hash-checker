package com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.selectors.IMenuItemCallback;
import com.smlnskgmail.jaman.hashchecker.components.selectors.UserActionTypes;

import butterknife.OnClick;

public class ActionsBottomSheet extends BaseBottomSheet {

    private IMenuItemCallback callback;

    public void setCallback(@NonNull IMenuItemCallback callback) {
        this.callback = callback;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_actions;
    }

    @OnClick(R.id.button_generate)
    public void generate() {
        callback.setClickFromDialog(UserActionTypes.GENERATE_HASH);
        dismiss();
    }

    @OnClick(R.id.button_compare)
    public void compare() {
        callback.setClickFromDialog(UserActionTypes.COMPARE_HASHES);
        dismiss();
    }

}
