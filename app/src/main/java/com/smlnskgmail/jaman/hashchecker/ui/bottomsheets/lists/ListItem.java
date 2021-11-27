package com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists;

import android.content.Context;

import androidx.annotation.NonNull;

public interface ListItem {

    int DEFAULT_ICON_VALUE = -1;

    @NonNull
    String getTitle(@NonNull Context context);

    int getPrimaryIconResId();

    int getAdditionalIconResId();

}
