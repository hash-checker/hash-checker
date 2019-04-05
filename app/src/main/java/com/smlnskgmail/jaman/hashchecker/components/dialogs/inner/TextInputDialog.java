package com.smlnskgmail.jaman.hashchecker.components.dialogs.inner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.EditText;

import com.smlnskgmail.jaman.hashchecker.R;

import butterknife.BindView;
import butterknife.OnClick;

public class TextInputDialog extends BaseDialog {

    @BindView(R.id.dialog_text_value)
    protected EditText fieldTextValue;

    private OnTextValueEnteredListener textValueCallback;

    private String textValue;

    public TextInputDialog(@NonNull Context context,
                           @NonNull OnTextValueEnteredListener textValueCallback,
                           @Nullable String textValue) {
        super(context);
        this.textValueCallback = textValueCallback;
        this.textValue = textValue;
    }

    @OnClick(R.id.dialog_text_button_add)
    void addText() {
        textValueCallback.onTextValueEntered(fieldTextValue.getText().toString());
        dismiss();
    }

    @Override
    public void initUI() {
        fieldTextValue.requestFocus();
        if (textValue == null) {
            fieldTextValue.setText("");
        } else {
            fieldTextValue.setText(textValue);
            fieldTextValue.setSelection(textValue.length());
        }
    }
    @Override
    public void setupDialogStyle() {
        super.setupDialogStyle();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.dialog_text_input;
    }

}
