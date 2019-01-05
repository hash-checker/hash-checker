package com.smlnskgmail.jaman.hashchecker.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.TextInputDialog;
import com.smlnskgmail.jaman.hashchecker.components.selectors.OnMenuItemClickListener;
import com.smlnskgmail.jaman.hashchecker.components.selectors.UserActionTypes;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.ActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.ResourcesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items.generator.GenerateToBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items.generator.OnHashTypeSelectListener;
import com.smlnskgmail.jaman.hashchecker.generator.Generator;
import com.smlnskgmail.jaman.hashchecker.generator.HashCalculator;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.utils.Constants;
import com.smlnskgmail.jaman.hashchecker.utils.Preferences;
import com.smlnskgmail.jaman.hashchecker.utils.TextUtils;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

import java.io.File;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment implements TextInputDialog.OnTextValueEnteredListener,
        Generator.OnGeneratorCompleteListener, OnMenuItemClickListener, OnHashTypeSelectListener {

    private static final String TAG_OPENED_BOTTOM_SHEET = "";

    @BindView(R.id.main_screen) protected View mainScreen;
    @BindView(R.id.hash_types) protected LinearLayout hashTypes;
    @BindView(R.id.field_custom_hash) protected EditText fieldCustomHash;
    @BindView(R.id.field_generated_hash) protected EditText fieldGeneratedHash;
    @BindView(R.id.field_selected_object_name) protected TextView fieldSelectedObject;
    @BindView(R.id.selected_hash_type) protected TextView selectedHash;
    @BindView(R.id.button_generate_from) protected Button from;

    private ProgressDialog progressDialog;

    private Uri fileUri;

    private boolean isTextSelected;
    private boolean startWithTextSelection, startWithFileSelection;

    private void setResult(@NonNull String text, boolean isText) {
        fieldSelectedObject.setText(text);
        this.isTextSelected = isText;
        from.setText(getString(isText ? R.string.common_text : R.string.common_file));
    }

    private void enterText() {
        String currentText = !isTextSelected ? null : fieldSelectedObject.getText().toString();
        new TextInputDialog(getContext(), MainFragment.this, currentText).show();
    }

    @SuppressLint("ResourceType")
    private void generate() {
        if (fileUri != null || isTextSelected) {
            HashTypes hashType = HashTypes.parseHashTypeFromString(getContext(), selectedHash.getText().toString());
            progressDialog = UIUtils.getProgressDialog(getContext(), R.string.message_generate_dialog);
            progressDialog.show();
            if (isTextSelected) {
                new HashCalculator(hashType, getContext(), fieldSelectedObject.getText().toString(), MainFragment.this, isTextSelected).execute();
            } else {
                new HashCalculator(hashType, getContext(), fileUri, MainFragment.this, isTextSelected).execute();
            }
        } else {
            UIUtils.showSnackbar(getContext(), mainScreen, getString(R.string.message_select_object), Snackbar.LENGTH_LONG);
        }
    }

    private void compare() {
        if (TextUtils.fieldIsNotEmpty(fieldCustomHash) && TextUtils.fieldIsNotEmpty(fieldGeneratedHash)) {
            boolean equal = TextUtils.compareText(fieldCustomHash.getText().toString(), fieldGeneratedHash.getText().toString());
            UIUtils.showSnackbar(getContext(), mainScreen, equal ? getString(R.string.message_match_result) :
                    getString(R.string.message_do_not_match_result), Snackbar.LENGTH_LONG);
        } else {
            UIUtils.showSnackbar(getContext(), mainScreen, getString(R.string.message_fill_fields), Snackbar.LENGTH_LONG);
        }
    }

    @Override
    int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    int getTitleResId() {
        return R.string.app_name;
    }

    @Override
    int getMenuResId() {
        return R.menu.menu_main;
    }

    @Override
    int[] getMenuItemsIds() {
        return new int[] {R.id.menu_settings};
    }

    @Override
    boolean setBackActionIcon() {
        return false;
    }

    @OnClick(R.id.hash_types)
    public void selectHashFromList() {
        GenerateToBottomSheet generateToBottomSheet = new GenerateToBottomSheet(MainFragment.this, selectedHash.getText().toString());
        generateToBottomSheet.setBottomSheetItemsList(Arrays.asList(HashTypes.values()));
        generateToBottomSheet.show(getFragmentManager(), TAG_OPENED_BOTTOM_SHEET);
    }

    @OnClick(R.id.button_generate_from)
    public void selectResourceToGenerateHash() {
        ResourcesBottomSheet resourcesBottomSheet = new ResourcesBottomSheet();
        resourcesBottomSheet.setMenuItemCallback(MainFragment.this);
        resourcesBottomSheet.show(getActivity().getSupportFragmentManager(), TAG_OPENED_BOTTOM_SHEET);
    }

    @OnClick(R.id.button_hash_actions)
    public void selectActionForGeneratedHash() {
        ActionsBottomSheet actionsBottomSheet = new ActionsBottomSheet();
        actionsBottomSheet.setMenuItemCallback(MainFragment.this);
        actionsBottomSheet.show(getActivity().getSupportFragmentManager(), TAG_OPENED_BOTTOM_SHEET);
    }

    @Override
    void initUI(@NonNull View view) {
        selectedHash.setText(Preferences.getLastType(getContext()));
        fieldSelectedObject.setMovementMethod(new ScrollingMovementMethod());
        if (startWithTextSelection) {
            onClick(UserActionTypes.ENTER_TEXT);
            startWithTextSelection = false;
        } else if (startWithFileSelection) {
            onClick(UserActionTypes.SEARCH_FILE);
            startWithFileSelection = false;
        }
    }

    private void checkShortcutActionPresence() {
        Bundle shortcutsArguments = getArguments();
        if (shortcutsArguments != null) {
            startWithTextSelection = shortcutsArguments.getBoolean(Constants.ShortcutActions.ACTION_START_WITH_TEXT_SELECTION, false);
            startWithFileSelection = shortcutsArguments.getBoolean(Constants.ShortcutActions.ACTION_START_WITH_FILE_SELECTION, false);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkShortcutActionPresence();
    }

    private void validateSelectedFile(@Nullable Intent data) {
        if (data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                fileUri = uri;
                isTextSelected = false;
                String fileName = new File(uri.getPath()).getName();
                setResult(fileName, false);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.Requests.FILE_SELECT_REQUEST && resultCode == Activity.RESULT_OK) {
            validateSelectedFile(data);
        }
    }

    @Override
    public void onEntered(@NonNull String text) {
        setResult(text, true);
    }

    @Override
    public void onComplete(@NonNull String hashValue) {
        fieldGeneratedHash.setText(hashValue);
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onClick(@NonNull UserActionTypes userActionTypes) {
        switch (userActionTypes) {
            case ENTER_TEXT:
                enterText();
                break;
            case SEARCH_FILE:
                AppUtils.searchFile(this, mainScreen);
                break;
            case GENERATE_HASH:
                generate();
                break;
            case COMPARE_HASHES:
                compare();
                break;
        }
    }

    @Override
    public void onBack() {
        UIUtils.showSnackbar(getContext(), getView().findViewById(R.id.main_screen), getString(R.string.message_exit),
                getString(R.string.exit_now), v -> AppUtils.closeApp(getActivity()), Snackbar.LENGTH_SHORT);
    }

    private void validateTextCase() {
        boolean useUpperCase = Preferences.useUpperCase(getContext());
        InputFilter[] fieldFilters = useUpperCase ? new InputFilter[]{new InputFilter.AllCaps()} : new InputFilter[]{};
        fieldCustomHash.setFilters(fieldFilters);
        fieldGeneratedHash.setFilters(fieldFilters);

        if (useUpperCase) {
            TextUtils.convertToUpperCase(fieldCustomHash);
            TextUtils.convertToUpperCase(fieldGeneratedHash);
        } else {
            TextUtils.convertToLowerCase(fieldCustomHash);
            TextUtils.convertToLowerCase(fieldGeneratedHash);
        }

        fieldCustomHash.setSelection(fieldCustomHash.getText().length());
        fieldGeneratedHash.setSelection(fieldGeneratedHash.getText().length());
    }

    @Override
    public void resume() {
        super.resume();
        validateTextCase();
    }

    @Override
    public void onSelect(@NonNull HashTypes hashType) {
        selectedHash.setText(hashType.getTypeAsString(getContext()));
        Preferences.saveTypeAsLast(getContext(), hashType.getTypeAsString(getContext()));
    }

}
