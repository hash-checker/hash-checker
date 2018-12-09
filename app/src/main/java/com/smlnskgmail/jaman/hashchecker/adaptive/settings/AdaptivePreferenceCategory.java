package com.smlnskgmail.jaman.hashchecker.adaptive.settings;

import android.content.Context;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;

import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public class AdaptivePreferenceCategory extends PreferenceCategory {

    public AdaptivePreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AdaptivePreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AdaptivePreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptivePreferenceCategory(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        UIUtils.applyAdaptiveFontWithBoldStyle(getContext(), holder.itemView.findViewById(android.R.id.title));
        super.onBindViewHolder(holder);
    }

}
