package com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.selectors.IMenuItemCallback;
import com.smlnskgmail.jaman.hashchecker.components.selectors.UserActionTypes;

import butterknife.OnClick;

public class ResourcesBottomSheet extends BaseBottomSheet {

    private IMenuItemCallback menuItemCallback;

    public void setMenuItemCallback(@NonNull IMenuItemCallback menuItemCallback) {
        this.menuItemCallback = menuItemCallback;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_from;
    }

    @OnClick(R.id.button_select_text)
    public void fromText() {
        menuItemCallback.setClickFromDialog(UserActionTypes.ENTER_TEXT);
        dismiss();
    }

    @OnClick(R.id.button_select_file)
    public void fromFile() {
        menuItemCallback.setClickFromDialog(UserActionTypes.SEARCH_FILE);
        dismiss();
    }

}
