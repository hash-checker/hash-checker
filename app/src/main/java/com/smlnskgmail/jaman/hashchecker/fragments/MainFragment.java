package com.smlnskgmail.jaman.hashchecker.fragments;

import android.app.Activity;
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

import com.smlnskgmail.jaman.hashchecker.HashCheckerApplication;
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
import com.smlnskgmail.jaman.hashchecker.utils.Preferences;
import com.smlnskgmail.jaman.hashchecker.utils.TextUtils;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

import java.io.File;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment implements Generator.IGeneratorResultAvailable,
        Generator.IGeneratorCompleteListener, IMenuItemCallback, IHashTypeSelectListener {

    private static final String TAG_OPENED_BOTTOM_SHEET = "";

    @BindView(R.id.hash_types) protected LinearLayout hashTypes;
    @BindView(R.id.custom_hash) protected EditText customHash;
    @BindView(R.id.generated_hash) protected EditText generatedHash;
    @BindView(R.id.button_from) protected Button from;
    @BindView(R.id.selected_object) protected TextView selectedObject;

    private View mainScreen;
    private TextView selectedHash;

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

    private void generate() {
        if (fileUri != null || isText) {
            HashTypes hashType = HashTypes.parseHashTypeFromString(selectedHash.getText().toString());
            if (isText) {
                new HashCalculator(hashType, getContext(), selectedObject.getText().toString(), MainFragment.this, isText).execute();
            } else {
                new HashCalculator(hashType, getContext(), fileUri, MainFragment.this, isText).execute();
            }
        } else {
            UIUtils.createSnackbar(mainScreen, R.id.main_screen, getString(R.string.message_select_object), Snackbar.LENGTH_LONG);
        }
    }

    private void compare() {
        if (TextUtils.fieldIsNotEmpty(customHash) && TextUtils.fieldIsNotEmpty(generatedHash)) {
            boolean equal = TextUtils.compareText(customHash.getText().toString(), generatedHash.getText().toString());
            UIUtils.createSnackbar(mainScreen, R.id.main_screen, equal ? getString(R.string.message_match_result) :
                    getString(R.string.message_do_not_match_result), Snackbar.LENGTH_LONG);
        } else {
            UIUtils.createSnackbar(mainScreen, R.id.main_screen, getString(R.string.message_fill_fields), Snackbar.LENGTH_LONG);
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
    int[] getMenuItemIds() {
        return new int[] {R.id.settings};
    }

    @Override
    boolean setBackArrow() {
        return false;
    }

    @OnClick(R.id.hash_types)
    public void selectHashFromList() {
        GenerateToBottomSheet generateToBottomSheet = new GenerateToBottomSheet(MainFragment.this, selectedHash.getText().toString());
        generateToBottomSheet.setItems(Arrays.asList(HashTypes.values()));
        generateToBottomSheet.show(getFragmentManager(), TAG_OPENED_BOTTOM_SHEET);
    }

    @OnClick(R.id.button_from)
    public void selectResourceToGenerateHash() {
        ResourcesBottomSheet resourcesBottomSheet = new ResourcesBottomSheet();
        resourcesBottomSheet.setCallback(MainFragment.this);
        resourcesBottomSheet.show(getActivity().getSupportFragmentManager(), TAG_OPENED_BOTTOM_SHEET);
    }

    @OnClick(R.id.actions)
    public void selectActionForGeneratedHash() {
        ActionsBottomSheet actionsBottomSheet = new ActionsBottomSheet();
        actionsBottomSheet.setCallback(MainFragment.this);
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
            startWithTextSelection = shortcutsArguments.getBoolean(HashCheckerApplication.ACTION_TEXT, false);
            startWithFileSelection = shortcutsArguments.getBoolean(HashCheckerApplication.ACTION_FILE, false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppUtils.FILE_SELECT_REQUEST && resultCode == Activity.RESULT_OK) {
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
    public void onResultAvailable(@NonNull String text) {
        setResult(text, true);
    }

    @Override
    public void onGeneratingComplete(@NonNull String hashValue) {
        generatedHash.setText(hashValue);
    }

    @Override
    public void setClickFromDialog(@NonNull UserActionTypes clickType) {
        switch (clickType) {
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
        UIUtils.createSnackbar(getView(), R.id.main_screen, getString(R.string.message_exit),
                getString(R.string.exit_now), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppUtils.closeApp(getActivity());
                    }
                }, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void onResume() {
        super.onResume();
        InputFilter[] fieldFilters;
        if (Preferences.useUpperCase(getContext())) {
            fieldFilters = new InputFilter[]{new InputFilter.AllCaps()};
            TextUtils.convertToUpperCase(customHash);
            TextUtils.convertToUpperCase(generatedHash);
        } else {
            fieldFilters = new InputFilter[]{};
            TextUtils.convertToLowerCase(customHash);
            TextUtils.convertToLowerCase(generatedHash);
        }
        customHash.setFilters(fieldFilters);
        generatedHash.setFilters(fieldFilters);
    }

    @Override
    public void onSelect(@NonNull HashTypes hashType) {
        selectedHash.setText(hashType.getTypeAsString());
        Preferences.saveTypeAsLast(getContext(), hashType.getTypeAsString());
    }

}
