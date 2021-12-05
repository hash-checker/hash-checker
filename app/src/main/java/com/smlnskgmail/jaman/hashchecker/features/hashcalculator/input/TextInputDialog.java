package com.smlnskgmail.jaman.hashchecker.features.hashcalculator.input;

import android.content.Context;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.ui.dialogs.BaseDialog;

public class TextInputDialog extends BaseDialog {

    private EditText etTextValue;

    private final TextValueTarget textValueCallback;
    private final String textValue;

    public TextInputDialog(
            @NonNull Context context,
            @NonNull TextValueTarget textEnteredCallback,
            @Nullable String textValue
    ) {
        super(context);
        this.textValueCallback = textEnteredCallback;
        this.textValue = textValue;
    }

    @Override
    public void initializeDialog() {
        super.initializeDialog();
        etTextValue = findViewById(R.id.et_dialog_input_text);

        Button btnAddText = findViewById(R.id.btn_dialog_input_text_add);
        btnAddText.setOnClickListener(v -> {
            textValueCallback.textValueEntered(etTextValue.getText().toString());
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
