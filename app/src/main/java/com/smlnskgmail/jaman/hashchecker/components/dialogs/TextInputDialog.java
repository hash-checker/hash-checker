package com.smlnskgmail.jaman.hashchecker.components.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.EditText;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.generator.Generator;

import butterknife.BindView;
import butterknife.OnClick;

public class TextInputDialog extends BaseDialog {

    @BindView(R.id.text_value) EditText textValue;

    private Generator.IGeneratorResultAvailable result;
    private String text;

    public TextInputDialog(@NonNull Context context, @NonNull Generator.IGeneratorResultAvailable result,
                           @Nullable String text) {
        super(context);
        this.result = result;
        this.text = text;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.dialog_text_input;
    }

    @OnClick(R.id.add_text)
    void addText() {
        result.onResultAvailable(textValue.getText().toString());
        dismiss();
    }

    @Override
    public void initUI() {
        textValue.requestFocus();
        if (text == null) {
            textValue.setText("");
        } else {
            textValue.setText(text);
            textValue.setSelection(text.length());
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}
