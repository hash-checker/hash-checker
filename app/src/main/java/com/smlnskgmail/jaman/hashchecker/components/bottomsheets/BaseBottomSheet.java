package com.smlnskgmail.jaman.hashchecker.components.bottomsheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.smlnskgmail.jaman.hashchecker.R;

public abstract class BaseBottomSheet extends BottomSheetDialogFragment {

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        getDialog().getWindow().getAttributes().windowAnimations
                = R.style.DialogAnimation;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                getLayoutResId(),
                container,
                false
        );
    }

    protected abstract int getLayoutResId();

}
