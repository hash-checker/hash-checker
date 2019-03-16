package com.smlnskgmail.jaman.hashchecker.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.GenerateToBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.OnHashTypeSelectListener;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.ActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.ResourcesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.OnUserActionClickListener;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.UserActionTypes;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.TextInputDialog;
import com.smlnskgmail.jaman.hashchecker.generator.HashCalculator;
import com.smlnskgmail.jaman.hashchecker.generator.HashGenerator;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.support.preferences.Constants;
import com.smlnskgmail.jaman.hashchecker.support.preferences.Preferences;
import com.smlnskgmail.jaman.hashchecker.support.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.support.utils.TextUtils;
import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;

import java.io.File;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment implements TextInputDialog.OnTextValueEnteredListener,
        HashCalculator.OnHashGeneratorCompleteListener, OnUserActionClickListener,
        OnHashTypeSelectListener {

    @BindView(R.id.main_screen)
    protected View mainScreen;

    @BindView(R.id.hash_types)
    protected LinearLayout hashTypes;

    @BindView(R.id.field_custom_hash)
    protected EditText fieldCustomHash;

    @BindView(R.id.field_generated_hash)
    protected EditText fieldGeneratedHash;

    @BindView(R.id.field_selected_object_name)
    protected TextView fieldSelectedObject;

    @BindView(R.id.selected_hash_type)
    protected TextView selectedHash;

    @BindView(R.id.button_generate_from)
    protected Button from;

    private ProgressDialog progressDialog;
    private Uri fileUri;

    private boolean isTextSelected;
    private boolean startWithTextSelection, startWithFileSelection;

    @Override
    public void onUserActionClick(@NonNull UserActionTypes userActionTypes) {
        switch (userActionTypes) {
            case ENTER_TEXT:
                enterText();
                break;
            case SEARCH_FILE:
                AppUtils.searchFile(this, mainScreen);
                break;
            case GENERATE_HASH:
                generateHash();
                break;
            case COMPARE_HASHES:
                compareHashes();
                break;
        }
    }

    @SuppressLint("ResourceType")
    private void generateHash() {
        Context context = getContext();
        if (fileUri != null || isTextSelected) {
            HashTypes hashType = HashTypes.parseHashTypeFromString(context,
                    selectedHash.getText().toString());
            progressDialog = UIUtils.getProgressDialog(context, R.string.message_generate_dialog);
            progressDialog.show();
            if (isTextSelected) {
                new HashGenerator(hashType, context, fieldSelectedObject.getText().toString(),
                        MainFragment.this, isTextSelected).execute();
            } else {
                new HashGenerator(hashType, context, fileUri, MainFragment.this,
                        isTextSelected).execute();
            }
        } else {
            UIUtils.showSnackbar(context, mainScreen, getString(R.string.message_select_object),
                    Snackbar.LENGTH_LONG);
        }
    }

    private void compareHashes() {
        Context context = getContext();
        if (TextUtils.fieldIsNotEmpty(fieldCustomHash) && TextUtils.fieldIsNotEmpty(fieldGeneratedHash)) {
            boolean equal = TextUtils.compareText(fieldCustomHash.getText().toString(),
                    fieldGeneratedHash.getText().toString());
            UIUtils.showSnackbar(context, mainScreen, equal ? getString(R.string.message_match_result) :
                    getString(R.string.message_do_not_match_result), Snackbar.LENGTH_LONG);
        } else {
            UIUtils.showSnackbar(context, mainScreen, getString(R.string.message_fill_fields),
                    Snackbar.LENGTH_LONG);
        }
    }

    @OnClick(R.id.hash_types)
    public void selectHashTypeFromList() {
        GenerateToBottomSheet generateToBottomSheet = new GenerateToBottomSheet();
        generateToBottomSheet.setListItems(Arrays.asList(HashTypes.values()));
        generateToBottomSheet.setOnHashTypeSelectListener(this::onHashTypeSelect);
        generateToBottomSheet.show(getFragmentManager(), Constants.Tags.CURRENT_BOTTOM_SHEET_TAG);
    }

    @OnClick(R.id.button_generate_from)
    public void selectResourceToGenerateHash() {
        ResourcesBottomSheet resourcesBottomSheet = new ResourcesBottomSheet();
        resourcesBottomSheet.setMenuItemCallback(MainFragment.this);
        resourcesBottomSheet.show(getActivity().getSupportFragmentManager(),
                Constants.Tags.CURRENT_BOTTOM_SHEET_TAG);
    }

    @OnClick(R.id.button_hash_actions)
    public void selectActionForHashes() {
        ActionsBottomSheet actionsBottomSheet = new ActionsBottomSheet();
        actionsBottomSheet.setMenuItemCallback(MainFragment.this);
        actionsBottomSheet.show(getActivity().getSupportFragmentManager(),
                Constants.Tags.CURRENT_BOTTOM_SHEET_TAG);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (checkArguments(bundle)) {
            checkShortcutActionPresence(bundle);
        }
    }

    @Override
    public void onPostInitialize() {
        Bundle bundle = getArguments();
        if (checkArguments(bundle)) {
            checkExternalDataPresence(bundle);
        }
    }

    private boolean checkArguments(@Nullable Bundle bundle) {
        return bundle != null;
    }

    private void checkExternalDataPresence(@NonNull Bundle dataArguments) {
        String uri = dataArguments.getString(Constants.Data.URI_FROM_EXTERNAL_APP);
        if (uri != null) {
            validateSelectedFile(Uri.parse(uri));
            dataArguments.remove(Constants.Data.URI_FROM_EXTERNAL_APP);
        }
    }

    private void checkShortcutActionPresence(@NonNull Bundle shortcutsArguments) {
        startWithTextSelection = shortcutsArguments
                .getBoolean(Constants.ShortcutActions.ACTION_START_WITH_TEXT_SELECTION, false);
        startWithFileSelection = shortcutsArguments
                .getBoolean(Constants.ShortcutActions.ACTION_START_WITH_FILE_SELECTION, false);

        shortcutsArguments.remove(Constants.ShortcutActions.ACTION_START_WITH_TEXT_SELECTION);
        shortcutsArguments.remove(Constants.ShortcutActions.ACTION_START_WITH_FILE_SELECTION);
    }

    private void validateSelectedFile(@Nullable Uri uri) {
        if (uri != null) {
            fileUri = uri;
            isTextSelected = false;
            String fileName = new File(uri.getPath()).getName();
            setResult(fileName, false);
        }
    }

    @Override
    public void onTextValueEntered(@NonNull String text) {
        setResult(text, true);
    }

    @Override
    public void onHashGeneratorComplete(@NonNull String hashValue) {
        fieldGeneratedHash.setText(hashValue);
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void setResult(@NonNull String text, boolean isText) {
        fieldSelectedObject.setText(text);
        this.isTextSelected = isText;
        from.setText(getString(isText ? R.string.common_text : R.string.common_file));
    }

    private void enterText() {
        String currentText = !isTextSelected ? null : fieldSelectedObject.getText().toString();
        new TextInputDialog(getContext(), MainFragment.this, currentText).show();
    }

    @Override
    public void onBack() {
        UIUtils.showSnackbar(getContext(), getView().findViewById(R.id.main_screen),
                getString(R.string.message_exit), getString(R.string.action_exit_now),
                v -> AppUtils.closeApp(getActivity()), Snackbar.LENGTH_SHORT);
    }

    private void validateTextCase() {
        boolean useUpperCase = Preferences.useUpperCase(getContext());
        InputFilter[] fieldFilters = useUpperCase
                ? new InputFilter[]{new InputFilter.AllCaps()} : new InputFilter[]{};
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
    public void onHashTypeSelect(@NonNull HashTypes hashType) {
        selectedHash.setText(hashType.getTypeAsString(getContext()));
        Preferences.saveTypeAsLast(getContext(), hashType.getTypeAsString(getContext()));
    }

    @Override
    void initializeUI(@NonNull View view) {
        selectedHash.setText(Preferences.getLastType(getContext()));
        fieldSelectedObject.setMovementMethod(new ScrollingMovementMethod());
        if (startWithTextSelection) {
            onUserActionClick(UserActionTypes.ENTER_TEXT);
            startWithTextSelection = false;
        } else if (startWithFileSelection) {
            onUserActionClick(UserActionTypes.SEARCH_FILE);
            startWithFileSelection = false;
        }
    }

    @Override
    public void resume() {
        super.resume();
        validateTextCase();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.Requests.FILE_SELECT_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                validateSelectedFile(uri);
            }
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
        return new int[] {R.id.menu_settings, R.id.menu_feedback};
    }

    @Override
    boolean setBackActionIcon() {
        return false;
    }

}
