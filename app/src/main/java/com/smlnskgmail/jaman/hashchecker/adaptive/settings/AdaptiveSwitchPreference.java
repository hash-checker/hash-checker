package com.smlnskgmail.jaman.hashchecker.adaptive.settings;

import android.content.Context;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.util.AttributeSet;

import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public class AdaptiveSwitchPreference extends SwitchPreferenceCompat {

    public AdaptiveSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AdaptiveSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AdaptiveSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptiveSwitchPreference(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        UIUtils.applyAdaptiveFont(getContext(), holder.itemView.findViewById(android.R.id.title));
        super.onBindViewHolder(holder);
    }

}
