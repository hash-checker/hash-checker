package com.smlnskgmail.jaman.hashchecker.components.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.util.AttributeSet;

import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;

public class CustomSwitchPreference extends SwitchPreferenceCompat {

    public CustomSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr,
                                  int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSwitchPreference(Context context) {
        super(context);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        UIUtils.applyAdaptiveFont(getContext(), holder.itemView.findViewById(android.R.id.title),
                true);
        super.onBindViewHolder(holder);
    }

}
