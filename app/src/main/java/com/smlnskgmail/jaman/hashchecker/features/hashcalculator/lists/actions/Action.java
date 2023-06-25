package com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.types.UserActionType;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.ListItem;

public enum Action implements ListItem {

    TEXT(R.string.common_text, R.drawable.ic_from_text, UserActionType.ENTER_TEXT),
    FILE(R.string.common_file, R.drawable.ic_file, UserActionType.SEARCH_FILE),
    FOLDER(R.string.common_folder, R.drawable.ic_folder, UserActionType.SEARCH_FOLDER),
    GENERATE(R.string.action_generate, R.drawable.ic_generate, UserActionType.GENERATE_HASH),
    COMPARE(R.string.action_compare, R.drawable.ic_compare, UserActionType.COMPARE_HASHES),
    EXPORT_AS_TXT(R.string.action_export_to_txt, R.drawable.ic_export, UserActionType.EXPORT_AS_TXT);

    private final int titleResId;
    private final int iconResId;

    private final UserActionType userActionType;

    Action(int titleResId, int iconResId, UserActionType userActionType) {
        this.titleResId = titleResId;
        this.iconResId = iconResId;
        this.userActionType = userActionType;
    }

    @NonNull
    @Override
    public String getTitle(@NonNull Context context) {
        return context.getString(titleResId);
    }

    @Override
    public int getPrimaryIconResId() {
        return iconResId;
    }

    @Override
    public int getAdditionalIconResId() {
        return -1;
    }

    @NonNull
    public UserActionType getUserActionType() {
        return userActionType;
    }

}
