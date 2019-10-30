package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists;

import android.content.Context;

import androidx.annotation.NonNull;

public interface ListItemTarget {

    String getTitle(@NonNull Context context);

    int getPrimaryIconResId();
    int getAdditionalIconResId();

}
