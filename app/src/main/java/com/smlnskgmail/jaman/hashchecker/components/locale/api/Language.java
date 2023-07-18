package com.smlnskgmail.jaman.hashchecker.components.locale.api;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.ListItem;

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
    MY("မြန်မာ", "my"),
    NL("Nederlands", "nl"),
    PL("Polski", "pl"),
    PT("Português (Brasil)", "pt"),
    RO("Română", "ro"),
    RU("Русский", "ru"),
    SV("Svenska", "sv"),
    ZH("中文(简体)", "zh-rCN"),
    VI("Tiếng Việt", "vi"),
    JA("日本語", "ja"),
    HI("हिंदी", "hi");

    private final String originalName;
    private final String code;

    Language(@NonNull String originalName, @NonNull String code) {
        this.originalName = originalName;
        this.code = code;
    }

    @NonNull
    public String code() {
        return code;
    }

    @NonNull
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
