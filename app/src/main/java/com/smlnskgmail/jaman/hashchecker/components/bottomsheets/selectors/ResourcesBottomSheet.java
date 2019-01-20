package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.OnUserActionClickListener;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.UserActionTypes;

import butterknife.OnClick;

public class ResourcesBottomSheet extends BaseBottomSheet {

    private OnUserActionClickListener menuItemCallback;

    public void setMenuItemCallback(@NonNull OnUserActionClickListener menuItemCallback) {
        this.menuItemCallback = menuItemCallback;
    }

    @OnClick(R.id.button_select_text)
    public void fromText() {
        menuItemCallback.onUserActionClick(UserActionTypes.ENTER_TEXT);
        dismiss();
    }

    @OnClick(R.id.button_select_file)
    public void fromFile() {
        menuItemCallback.onUserActionClick(UserActionTypes.SEARCH_FILE);
        dismiss();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_from;
    }

}
