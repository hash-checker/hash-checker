package com.smlnskgmail.jaman.hashchecker.components.preferences;

import android.content.Context;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;

import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;

public class CustomPreference extends Preference {

    public CustomPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPreference(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        UIUtils.applyAdaptiveFont(getContext(), holder.itemView.findViewById(android.R.id.title));
        UIUtils.applyAdaptiveFont(getContext(), holder.itemView.findViewById(android.R.id.summary));
        super.onBindViewHolder(holder);
    }

}
