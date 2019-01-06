package com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.selectors.OnMenuItemClickListener;
import com.smlnskgmail.jaman.hashchecker.components.selectors.UserActionTypes;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.base.BaseBottomSheet;

import butterknife.OnClick;

public class ResourcesBottomSheet extends BaseBottomSheet {

    private OnMenuItemClickListener menuItemCallback;

    public void setMenuItemCallback(@NonNull OnMenuItemClickListener menuItemCallback) {
        this.menuItemCallback = menuItemCallback;
    }

    @OnClick(R.id.button_select_text)
    public void fromText() {
        menuItemCallback.onClick(UserActionTypes.ENTER_TEXT);
        dismiss();
    }

    @OnClick(R.id.button_select_file)
    public void fromFile() {
        menuItemCallback.onClick(UserActionTypes.SEARCH_FILE);
        dismiss();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_from;
    }

}
