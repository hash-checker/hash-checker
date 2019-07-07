package com.smlnskgmail.jaman.hashchecker.components.dialogs.app.input;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.app.BaseDialog;

public class TextInputDialog extends BaseDialog {

    private EditText etTextValue;

    private OnTextValueEnteredListener textValueCallback;

    private String textValue;

    public TextInputDialog(@NonNull Context context,
                           @NonNull OnTextValueEnteredListener textEnteredCallback,
                           @Nullable String textValue) {
        super(context);
        this.textValueCallback = textEnteredCallback;
        this.textValue = textValue;
    }

    @Override
    public void initUI() {
        super.initUI();
        etTextValue = findViewById(R.id.et_dialog_input_text);

        Button btnAddText = findViewById(R.id.btn_dialog_input_text_add);
        btnAddText.setOnClickListener(v -> {
            textValueCallback.onTextValueEntered(etTextValue.getText().toString());
            dismiss();
        });

        etTextValue.requestFocus();
        if (textValue == null) {
            etTextValue.setText("");
        } else {
            etTextValue.setText(textValue);
            etTextValue.setSelection(textValue.length());
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
