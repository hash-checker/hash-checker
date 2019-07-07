package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.OnUserActionClickListener;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.UserActionType;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public class ActionsBottomSheet extends BaseBottomSheet {

    private OnUserActionClickListener menuItemCallback;

    public void setMenuItemCallback(@NonNull OnUserActionClickListener menuItemCallback) {
        this.menuItemCallback = menuItemCallback;
    }

    @Override
    public void initUI(@NonNull View contentView) {
        Context context = getContext();
        TextView btnGenerate = contentView.findViewById(R.id.tv_as_btn_generate_hash_value);
        btnGenerate.setOnClickListener(v -> selectAction(UserActionType.GENERATE_HASH));
        UIUtils.colorizeImageSourceToAccentColor(context, btnGenerate.getCompoundDrawablesRelative()[0]);

        TextView btnCompare = contentView.findViewById(R.id.tv_as_btn_compare_hash_values);
        btnCompare.setOnClickListener(v -> selectAction(UserActionType.COMPARE_HASHES));
        UIUtils.colorizeImageSourceToAccentColor(context, btnCompare.getCompoundDrawablesRelative()[0]);
    }

    private void selectAction(@NonNull UserActionType userActionType) {
        menuItemCallback.onUserActionClick(userActionType);
        dismiss();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_actions;
    }

}
