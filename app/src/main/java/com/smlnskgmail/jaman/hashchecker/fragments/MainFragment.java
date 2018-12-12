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
import com.smlnskgmail.jaman.hashchecker.components.selectors.IMenuItemCallback;
import com.smlnskgmail.jaman.hashchecker.components.selectors.UserActionTypes;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.ActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.ResourcesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items.generator.GenerateToBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items.generator.IHashTypeSelectListener;
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

public class MainFragment extends BaseFragment implements TextInputDialog.ITextValueEntered,
        Generator.IGeneratorCompleteListener, IMenuItemCallback, IHashTypeSelectListener {

    private static final String TAG_OPENED_BOTTOM_SHEET = "";

    @BindView(R.id.list_hash_types) protected LinearLayout hashTypes;
    @BindView(R.id.field_custom_hash) protected EditText customHash;
    @BindView(R.id.field_generated_hash) protected EditText generatedHash;
    @BindView(R.id.button_from) protected Button from;
    @BindView(R.id.field_selected_object) protected TextView selectedObject;

    private View mainScreen;
    private TextView selectedHash;
    private ProgressDialog progressDialog;

    private Uri fileUri;

    private boolean isText;
    private boolean startWithTextSelection, startWithFileSelection;

    private void setResult(@NonNull String text, boolean isText) {
        selectedObject.setText(text);
        this.isText = isText;
        from.setText(getString(isText ? R.string.common_text : R.string.common_file));
    }

    private void enterText() {
        String currentText = !isText ? null : selectedObject.getText().toString();
        new TextInputDialog(getContext(), MainFragment.this, currentText).show();
    }

    @SuppressLint("ResourceType")
    private void generate() {
        if (fileUri != null || isText) {
            HashTypes hashType = HashTypes.parseHashTypeFromString(getContext(), selectedHash.getText().toString());
            progressDialog = UIUtils.getProgressDialog(getContext(), R.string.message_generate_dialog);
            progressDialog.show();
            if (isText) {
                new HashCalculator(hashType, getContext(), selectedObject.getText().toString(), MainFragment.this, isText).execute();
            } else {
                new HashCalculator(hashType, getContext(), fileUri, MainFragment.this, isText).execute();
            }
        } else {
            UIUtils.createSnackbar(getContext(), mainScreen, getString(R.string.message_select_object), Snackbar.LENGTH_LONG);
        }
    }

    private void compare() {
        if (TextUtils.fieldIsNotEmpty(customHash) && TextUtils.fieldIsNotEmpty(generatedHash)) {
            boolean equal = TextUtils.compareText(customHash.getText().toString(), generatedHash.getText().toString());
            UIUtils.createSnackbar(getContext(), mainScreen, equal ? getString(R.string.message_match_result) :
                    getString(R.string.message_do_not_match_result), Snackbar.LENGTH_LONG);
        } else {
            UIUtils.createSnackbar(getContext(), mainScreen, getString(R.string.message_fill_fields), Snackbar.LENGTH_LONG);
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
        return new int[] {R.id.settings};
    }

    @Override
    boolean setBackActionIcon() {
        return false;
    }

    @OnClick(R.id.list_hash_types)
    public void selectHashFromList() {
        GenerateToBottomSheet generateToBottomSheet = new GenerateToBottomSheet(MainFragment.this, selectedHash.getText().toString());
        generateToBottomSheet.setBottomSheetItemsList(Arrays.asList(HashTypes.values()));
        generateToBottomSheet.show(getFragmentManager(), TAG_OPENED_BOTTOM_SHEET);
    }

    @OnClick(R.id.button_from)
    public void selectResourceToGenerateHash() {
        ResourcesBottomSheet resourcesBottomSheet = new ResourcesBottomSheet();
        resourcesBottomSheet.setMenuItemCallback(MainFragment.this);
        resourcesBottomSheet.show(getActivity().getSupportFragmentManager(), TAG_OPENED_BOTTOM_SHEET);
    }

    @OnClick(R.id.button_actions)
    public void selectActionForGeneratedHash() {
        ActionsBottomSheet actionsBottomSheet = new ActionsBottomSheet();
        actionsBottomSheet.setMenuItemCallback(MainFragment.this);
        actionsBottomSheet.show(getActivity().getSupportFragmentManager(), TAG_OPENED_BOTTOM_SHEET);
    }

    @Override
    void initUI(@NonNull View view) {
        mainScreen = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);

        selectedHash = hashTypes.findViewById(R.id.selected_type);
        selectedHash.setText(Preferences.getLastType(getContext()));
        selectedObject.setMovementMethod(new ScrollingMovementMethod());

        if (startWithTextSelection) {
            setClickFromDialog(UserActionTypes.ENTER_TEXT);
            startWithTextSelection = false;
        } else if (startWithFileSelection) {
            setClickFromDialog(UserActionTypes.SEARCH_FILE);
            startWithFileSelection = false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle shortcutsArguments = getArguments();
        if (shortcutsArguments != null) {
            startWithTextSelection = shortcutsArguments.getBoolean(Constants.ShortcutActions.ACTION_TEXT, false);
            startWithFileSelection = shortcutsArguments.getBoolean(Constants.ShortcutActions.ACTION_FILE, false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.Requests.FILE_SELECT_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    fileUri = uri;
                    isText = false;
                    String fileName = new File(uri.getPath()).getName();
                    setResult(fileName, false);
                }
            }
        }
    }

    @Override
    public void onTextValueEntered(@NonNull String text) {
        setResult(text, true);
    }

    @Override
    public void onGeneratingComplete(@NonNull String hashValue) {
        generatedHash.setText(hashValue);
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void setClickFromDialog(@NonNull UserActionTypes userActionTypes) {
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
    public void back() {
        UIUtils.createSnackbar(getContext(), getView().findViewById(R.id.main_screen), getString(R.string.message_exit),
                getString(R.string.exit_now), v -> AppUtils.closeApp(getActivity()), Snackbar.LENGTH_SHORT);
    }

    @Override
    public void resume() {
        super.resume();
        boolean useUpperCase = Preferences.useUpperCase(getContext());
        InputFilter[] fieldFilters = useUpperCase ? new InputFilter[]{new InputFilter.AllCaps()} : new InputFilter[]{};
        customHash.setFilters(fieldFilters);
        generatedHash.setFilters(fieldFilters);

        if (useUpperCase) {
            TextUtils.convertToUpperCase(customHash);
            TextUtils.convertToUpperCase(generatedHash);
        } else {
            TextUtils.convertToLowerCase(customHash);
            TextUtils.convertToLowerCase(generatedHash);
        }

        customHash.setSelection(customHash.getText().length());
        generatedHash.setSelection(generatedHash.getText().length());
    }

    @Override
    public void onHashTypeSelect(@NonNull HashTypes hashType) {
        selectedHash.setText(hashType.getTypeAsString(getContext()));
        Preferences.saveTypeAsLast(getContext(), hashType.getTypeAsString(getContext()));
    }

}
