package com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.selectors.OnMenuItemClickListener;
import com.smlnskgmail.jaman.hashchecker.components.selectors.UserActionTypes;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.base.BaseBottomSheet;

import butterknife.OnClick;

public class ActionsBottomSheet extends BaseBottomSheet {

    private OnMenuItemClickListener menuItemCallback;

    public void setMenuItemCallback(@NonNull OnMenuItemClickListener menuItemCallback) {
        this.menuItemCallback = menuItemCallback;
    }

    @OnClick(R.id.button_generate)
    public void generate() {
        selectAction(UserActionTypes.GENERATE_HASH);
    }

    @OnClick(R.id.button_compare)
    public void compare() {
        selectAction(UserActionTypes.COMPARE_HASHES);
    }

    private void selectAction(@NonNull UserActionTypes userActionType) {
        menuItemCallback.onClick(userActionType);
        dismiss();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_actions;
    }

}
