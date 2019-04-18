package com.smlnskgmail.jaman.hashchecker.components.dialogs.inner;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.FrameLayout;

import com.smlnskgmail.jaman.hashchecker.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseDialog extends Dialog {

    BaseDialog(@NonNull Context context) {
        super(context);
    }

    @OnClick(R.id.dialog_button_close)
    void closeDialog() {
        dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initUI();
        setupDialogStyle();
    }

    public abstract int getLayoutResId();
    public abstract void initUI();

    public void setupDialogStyle() {
        getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
    }

}
