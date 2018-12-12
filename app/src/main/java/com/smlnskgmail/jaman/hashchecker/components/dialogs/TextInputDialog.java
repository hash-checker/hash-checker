package com.smlnskgmail.jaman.hashchecker.components.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.EditText;

import com.smlnskgmail.jaman.hashchecker.R;

import butterknife.BindView;
import butterknife.OnClick;

public class TextInputDialog extends BaseDialog {

    public interface ITextValueEntered {

        void onTextValueEntered(@NonNull String text);

    }

    @BindView(R.id.dialog_text_value) protected EditText fieldTextValue;

    private ITextValueEntered textValueEntered;
    private String textValue;

    public TextInputDialog(@NonNull Context context, @NonNull ITextValueEntered textValueEntered,
                           @Nullable String textValue) {
        super(context);
        this.textValueEntered = textValueEntered;
        this.textValue = textValue;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.dialog_text_input;
    }

    @OnClick(R.id.dialog_text_button_add)
    void addText() {
        textValueEntered.onTextValueEntered(fieldTextValue.getText().toString());
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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}
