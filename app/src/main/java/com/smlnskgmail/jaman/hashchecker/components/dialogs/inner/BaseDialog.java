package com.smlnskgmail.jaman.hashchecker.components.dialogs.inner;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

import com.smlnskgmail.jaman.hashchecker.R;

public abstract class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResId());
        initUI();
        setupDialogStyle();
    }

    public abstract int getLayoutResId();

    public void initUI() {
        Button btnClose = findViewById(R.id.btn_dialog_input_text_close);
        btnClose.setOnClickListener(v -> dismiss());
    }

    public void setupDialogStyle() {
        getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
    }

}
