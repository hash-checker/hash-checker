package com.smlnskgmail.jaman.hashchecker.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.R;

public abstract class BaseDialog extends Dialog {

    protected BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResId());
        initializeDialog();
        setupDialogStyle();
    }

    protected abstract int getLayoutResId();

    public void initializeDialog() {
        Button btnClose = findViewById(R.id.btn_dialog_input_text_close);
        btnClose.setOnClickListener(v -> dismiss());
    }

    public void setupDialogStyle() {
        getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
    }

}
