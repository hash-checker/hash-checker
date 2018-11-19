package com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.selectors.IMenuItemCallback;
import com.smlnskgmail.jaman.hashchecker.components.selectors.UserActionTypes;

import butterknife.OnClick;

public class ResourcesBottomSheet extends BaseBottomSheet {

    private IMenuItemCallback callback;

    public void setCallback(@NonNull IMenuItemCallback callback) {
        this.callback = callback;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_from;
    }

    @OnClick(R.id.from_text)
    public void fromText() {
        callback.setClickFromDialog(UserActionTypes.ENTER_TEXT);
        dismiss();
    }

    @OnClick(R.id.from_file)
    public void fromFile() {
        callback.setClickFromDialog(UserActionTypes.SEARCH_FILE);
        dismiss();
    }

}
