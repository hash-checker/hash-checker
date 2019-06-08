package com.smlnskgmail.jaman.hashchecker.fragments.functionality;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
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
import com.smlnskgmail.jaman.hashchecker.components.dialogs.inner.input.OnTextValueEnteredListener;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.inner.input.TextInputDialog;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppProgressDialog;
import com.smlnskgmail.jaman.hashchecker.components.preferences.Constants;
import com.smlnskgmail.jaman.hashchecker.components.preferences.Preferences;
import com.smlnskgmail.jaman.hashchecker.db.helper.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.fragments.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.functionality.history.data.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.generator.HashGenerator;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.generator.OnHashGeneratorCompleteListener;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.utils.TextUtils;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment implements OnTextValueEnteredListener,
        OnHashGeneratorCompleteListener, OnUserActionClickListener, OnHashTypeSelectListener {

    @BindView(R.id.fl_main_screen)
    protected View mainScreen;

    @BindView(R.id.ll_as_selector_hash_types)
    protected LinearLayout hashTypes;

    @BindView(R.id.et_field_custom_hash)
    protected EditText etCustomHash;

    @BindView(R.id.et_field_generated_hash)
    protected EditText etGeneratedHash;

    @BindView(R.id.tv_selected_object_name)
    protected TextView tvSelectedObjectName;

    @BindView(R.id.tv_selected_hash_type)
    protected TextView selectedHash;

    @BindView(R.id.btn_generate_from)
    protected Button btnGenerateFrom;

    private ProgressDialog progressDialog;

    private Uri fileUri;

    private Context context;
    private FragmentManager fragmentManager;

    private boolean isTextSelected;
    private boolean startWithTextSelection, startWithFileSelection;

    @Override
    public void onUserActionClick(@NonNull UserActionTypes userActionType) {
        switch (userActionType) {
            case ENTER_TEXT:
                enterText();
                break;
            case SEARCH_FILE:
                searchFile();
                break;
            case GENERATE_HASH:
                generateHash();
                break;
            case COMPARE_HASHES:
                compareHashes();
                break;
        }
    }

    private void searchFile() {
        if (Preferences.isUsingInnerFileManager(context)) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestStoragePermission();
            } else {
                AppUtils.openInnerFileManager(this);
            }
        } else {
            AppUtils.openSystemFileManager(this, mainScreen);
        }
    }

    @SuppressLint("ResourceType")
    private void generateHash() {
        if (fileUri != null || isTextSelected) {
            HashTypes hashType = HashTypes.parseHashTypeFromString(context, selectedHash.getText().toString());
            progressDialog = AppProgressDialog.getDialog(context, R.string.message_generate_dialog);
            progressDialog.show();
            if (isTextSelected) {
                new HashGenerator(hashType, context, tvSelectedObjectName.getText().toString(),
                        this).execute();
            } else {
                new HashGenerator(hashType, context, fileUri, this).execute();
            }
        } else {
            UIUtils.showSnackbar(context, mainScreen, getString(R.string.message_select_object),
                    Snackbar.LENGTH_LONG);
        }
    }

    private void compareHashes() {
        if (TextUtils.fieldIsNotEmpty(etCustomHash) && TextUtils.fieldIsNotEmpty(etGeneratedHash)) {
            boolean equal = TextUtils.compareText(etCustomHash.getText().toString(),
                    etGeneratedHash.getText().toString());
            UIUtils.showSnackbar(context, mainScreen, equal ? getString(R.string.message_match_result) :
                    getString(R.string.message_do_not_match_result), Snackbar.LENGTH_LONG);
        } else {
            UIUtils.showSnackbar(context, mainScreen, getString(R.string.message_fill_fields),
                    Snackbar.LENGTH_LONG);
        }
    }

    @OnClick(R.id.ll_as_selector_hash_types)
    public void selectHashTypeFromList() {
        GenerateToBottomSheet generateToBottomSheet = new GenerateToBottomSheet();
        generateToBottomSheet.setItems(Arrays.asList(HashTypes.values()));
        generateToBottomSheet.setHashTypeSelectListener(this);
        generateToBottomSheet.show(getFragmentManager(), Constants.Tags.CURRENT_BOTTOM_SHEET_TAG);
    }

    @OnClick(R.id.btn_generate_from)
    public void selectResourceToGenerateHash() {
        ResourcesBottomSheet resourcesBottomSheet = new ResourcesBottomSheet();
        resourcesBottomSheet.setMenuItemCallback(MainFragment.this);
        resourcesBottomSheet.show(fragmentManager, Constants.Tags.CURRENT_BOTTOM_SHEET_TAG);
    }

    @OnClick(R.id.btn_hash_actions)
    public void selectActionForHashes() {
        ActionsBottomSheet actionsBottomSheet = new ActionsBottomSheet();
        actionsBottomSheet.setMenuItemCallback(MainFragment.this);
        actionsBottomSheet.show(fragmentManager, Constants.Tags.CURRENT_BOTTOM_SHEET_TAG);
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
    public void onHashGeneratorComplete(@Nullable String hashValue) {
        if (hashValue == null) {
            etGeneratedHash.setText("");
            UIUtils.showSnackbar(context, mainScreen, getString(R.string.message_invalid_selected_source),
                    Snackbar.LENGTH_LONG);
        } else {
            etGeneratedHash.setText(hashValue);
            if (Preferences.canSaveResultToHistory(context)) {
                Date date = Calendar.getInstance().getTime();
                String objectValue = tvSelectedObjectName.getText().toString();
                HashTypes hashType = HashTypes.parseHashTypeFromString(context,
                        selectedHash.getText().toString());
                HistoryItem historyItem = new HistoryItem(date, hashType, !isTextSelected, objectValue,
                        hashValue);
                HelperFactory.getHelper().addGeneratorHistoryItem(historyItem);
            }
        }
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void setResult(@NonNull String text, boolean isText) {
        tvSelectedObjectName.setText(text);
        this.isTextSelected = isText;
        btnGenerateFrom.setText(getString(isText ? R.string.common_text : R.string.common_file));
    }

    private void enterText() {
        String currentText = !isTextSelected ? null : tvSelectedObjectName.getText().toString();
        new TextInputDialog(context, this, currentText).show();
    }

    @Override
    public void onBack() {
        UIUtils.showSnackbar(context, getView().findViewById(R.id.fl_main_screen),
                getString(R.string.message_exit), getString(R.string.action_exit_now),
                v -> AppUtils.closeApp(getActivity()), Snackbar.LENGTH_SHORT);
    }

    private void validateTextCase() {
        boolean useUpperCase = Preferences.useUpperCase(context);
        InputFilter[] fieldFilters = useUpperCase
                ? new InputFilter[]{new InputFilter.AllCaps()} : new InputFilter[]{};
        etCustomHash.setFilters(fieldFilters);
        etGeneratedHash.setFilters(fieldFilters);

        if (useUpperCase) {
            TextUtils.convertToUpperCase(etCustomHash);
            TextUtils.convertToUpperCase(etGeneratedHash);
        } else {
            TextUtils.convertToLowerCase(etCustomHash);
            TextUtils.convertToLowerCase(etGeneratedHash);
        }

        etCustomHash.setSelection(etCustomHash.getText().length());
        etGeneratedHash.setSelection(etGeneratedHash.getText().length());
    }

    @Override
    public void onHashTypeSelect(@NonNull HashTypes hashType) {
        selectedHash.setText(hashType.getTypeAsString(context));
        Preferences.saveHashTypeAsLast(context, hashType);
    }

    @Override
    public void initializeUI(@NonNull View contentView) {
        context = getContext();
        fragmentManager = getActivity().getSupportFragmentManager();
        selectedHash.setText(Preferences.getLastHashType(context).getTypeAsString(context));
        tvSelectedObjectName.setMovementMethod(new ScrollingMovementMethod());
        if (startWithTextSelection) {
            onUserActionClick(UserActionTypes.ENTER_TEXT);
            startWithTextSelection = false;
        } else if (startWithFileSelection) {
            onUserActionClick(UserActionTypes.SEARCH_FILE);
            startWithFileSelection = false;
        }
    }

    private void requestStoragePermission() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                Constants.Requests.PERMISSION_STORAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.Requests.PERMISSION_STORAGE_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                searchFile();
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    UIUtils.showSnackbar(context, mainScreen,
                            getString(R.string.message_request_storage_permission_error),
                            getString(R.string.common_again), v -> {
                                requestStoragePermission();
                            }, Snackbar.LENGTH_LONG);
                } else {
                    AppAlertDialog.show(context, R.string.title_permission_dialog,
                            R.string.message_request_storage_permission_denied,
                            R.string.menu_title_settings,
                            (dialog, which) -> AppUtils.openAppSettings(getActivity()));
                }
            }
        }
    }

    @Override
    public void onAppResume() {
        super.onAppResume();
        validateTextCase();
        if (Preferences.refreshSelectedFile(context)) {
            if (!isTextSelected && fileUri != null) {
                fileUri = null;
                tvSelectedObjectName.setText(getString(R.string.message_select_object));
                btnGenerateFrom.setText(getString(R.string.action_from));
                Preferences.setRefreshSelectedFileStatus(context, false);
            }
        }
        onHashTypeSelect(Preferences.getLastHashType(context));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.Requests.FILE_SELECT_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    selectFileFromSystemFileManager(data);
                }
                break;
            case Constants.Requests.FILE_SELECT_REQUEST_FROM_APP_FILE_MANAGER:
                selectFileFromAppFileManager(data);
                break;
        }
    }

    private void selectFileFromSystemFileManager(@Nullable Intent data) {
        if (data != null) {
            Uri uri = data.getData();
            validateSelectedFile(uri);
            Preferences.setGenerateFromShareIntentMode(context, false);
        }
    }

    private void selectFileFromAppFileManager(@Nullable Intent data) {
        if (data != null) {
            Uri uri = Uri.fromFile(new File(data
                    .getStringExtra(Constants.RequestData.FILE_SELECT_DATA)));
            validateSelectedFile(uri);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    public int getActionBarTitleResId() {
        return R.string.app_name;
    }

    @Override
    public int getMenuResId() {
        return R.menu.menu_main;
    }

    @Override
    public int[] getMenuItemsIds() {
        return new int[] {R.id.menu_main_section_settings, R.id.menu_main_section_feedback};
    }

    @Override
    public boolean setBackActionIcon() {
        return false;
    }

}
