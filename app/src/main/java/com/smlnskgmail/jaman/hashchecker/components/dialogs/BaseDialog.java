package com.smlnskgmail.jaman.hashchecker.components.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.Window;

import com.smlnskgmail.jaman.hashchecker.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseDialog extends Dialog {

    public abstract int getLayoutResId();
    public abstract void initUI();

    BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initUI();
        getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
    }

    @OnClick(R.id.dialog_button_close)
    void closeDialog() {
        dismiss();
    }

}
