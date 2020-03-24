package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.languages;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItem;

public enum Language implements ListItem {

    EN("English", "en"),
    DE("Deutsch", "de"),
    EL("Ελληνικά", "el"),
    ES("Español", "es"),
    FA("Farsi", "fa"),
    FR("Français", "fr"),
    HU("Hungarian", "hu"),
    IT("Italiano", "it"),
    IW("ברית", "iw"),
    KO("한국어", "ko"),
    NL("Nederlands", "nl"),
    PL("Polski", "pl"),
    RU("Русский", "ru"),
    SV("Svenska", "sv"),
    ZH("中文(简体)", "zh-rCN");

    private final String originalName;
    private final String code;

    Language(
            @NonNull String originalName,
            @NonNull String code
    ) {
        this.originalName = originalName;
        this.code = code;
    }

    @NonNull
    public String code() {
        return code;
    }

    @Override
    public String getTitle(@NonNull Context context) {
        return originalName;
    }

    @Override
    public int getPrimaryIconResId() {
        return -1;
    }

    @Override
    public int getAdditionalIconResId() {
        return R.drawable.ic_done;
    }

}
