package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.OnUserActionClickListener;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.UserActionTypes;

import butterknife.OnClick;

public class ActionsBottomSheet extends BaseBottomSheet {

    private OnUserActionClickListener menuItemCallback;

    public void setMenuItemCallback(@NonNull OnUserActionClickListener menuItemCallback) {
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
        menuItemCallback.onUserActionClick(userActionType);
        dismiss();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_actions;
    }

}
