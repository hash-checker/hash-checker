package com.smlnskgmail.jaman.hashchecker.navigation.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.Action;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.ActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.types.OnUserActionClickListener;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.types.UserActionType;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.hashtypes.GenerateToBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.hashtypes.OnHashTypeSelectListener;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.app.input.OnTextValueEnteredListener;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.app.input.TextInputDialog;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppProgressDialog;
import com.smlnskgmail.jaman.hashchecker.db.helper.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.hashgenerator.HashGenerator;
import com.smlnskgmail.jaman.hashchecker.hashgenerator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.hashgenerator.support.OnHashGeneratorComplete;
import com.smlnskgmail.jaman.hashchecker.navigation.fragments.history.entities.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.support.logger.L;
import com.smlnskgmail.jaman.hashchecker.support.params.Constants;
import com.smlnskgmail.jaman.hashchecker.support.params.Requests;
import com.smlnskgmail.jaman.hashchecker.support.params.Shortcuts;
import com.smlnskgmail.jaman.hashchecker.support.params.Tags;
import com.smlnskgmail.jaman.hashchecker.support.prefs.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.utils.TextUtils;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class MainFragment extends BaseFragment implements OnTextValueEnteredListener,
        OnHashGeneratorComplete, OnUserActionClickListener, OnHashTypeSelectListener {

    private View mainScreen;

    private EditText etCustomHash;
    private EditText etGeneratedHash;

    private TextView tvSelectedObjectName;
    private TextView tvSelectedHashType;

    private Button btnGenerateFrom;

    private ProgressDialog progressDialog;

    private Uri fileUri;

    private Context context;
    private FragmentManager fragmentManager;

    private boolean isTextSelected;
    private boolean startWithTextSelection, startWithFileSelection;

    @Override
    public void onUserActionClick(@NonNull UserActionType userActionType) {
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
            case EXPORT_AS_TXT:
                saveGeneratedHashAsTxtFile();
                break;
        }
    }

    private void saveGeneratedHashAsTxtFile() {
        String filename = getString(isTextSelected ? R.string.filename_hash_from_text
                : R.string.filename_hash_from_file);
        AppUtils.saveTextFile(this, filename, mainScreen);
    }

    private void searchFile() {
        if (SettingsHelper.isUsingInnerFileManager(context)) {
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
            HashType hashType = HashType.parseHashTypeFromString(context, tvSelectedHashType.getText().toString());
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

    private void selectHashTypeFromList() {
        GenerateToBottomSheet generateToBottomSheet = new GenerateToBottomSheet();
        generateToBottomSheet.setItems(Arrays.asList(HashType.values()));
        generateToBottomSheet.setHashTypeSelectListener(this);
        generateToBottomSheet.show(getFragmentManager(), Tags.CURRENT_BOTTOM_SHEET_TAG);
    }

    private void selectResourceToGenerateHash() {
        showBottomSheetWithActions(Action.TEXT, Action.FILE);
    }

    private void selectActionForHashes() {
        showBottomSheetWithActions(Action.GENERATE, Action.COMPARE, Action.EXPORT_AS_TXT);
    }

    private void showBottomSheetWithActions(Action... actions) {
        ActionsBottomSheet actionsBottomSheet = new ActionsBottomSheet();
        actionsBottomSheet.setActions(Arrays.asList(actions));
        actionsBottomSheet.setOnUserActionClickListener(MainFragment.this);
        actionsBottomSheet.show(fragmentManager, Tags.CURRENT_BOTTOM_SHEET_TAG);
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
        String uri = dataArguments.getString(Constants.URI_FROM_EXTERNAL_APP);
        if (uri != null) {
            validateSelectedFile(Uri.parse(uri));
            dataArguments.remove(Constants.URI_FROM_EXTERNAL_APP);
        }
    }

    private void checkShortcutActionPresence(@NonNull Bundle shortcutsArguments) {
        startWithTextSelection = shortcutsArguments
                .getBoolean(Shortcuts.ACTION_START_WITH_TEXT_SELECTION, false);
        startWithFileSelection = shortcutsArguments
                .getBoolean(Shortcuts.ACTION_START_WITH_FILE_SELECTION, false);

        shortcutsArguments.remove(Shortcuts.ACTION_START_WITH_TEXT_SELECTION);
        shortcutsArguments.remove(Shortcuts.ACTION_START_WITH_FILE_SELECTION);
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
            if (SettingsHelper.canSaveResultToHistory(context)) {
                Date date = Calendar.getInstance().getTime();
                String objectValue = tvSelectedObjectName.getText().toString();
                HashType hashType = HashType.parseHashTypeFromString(context,
                        tvSelectedHashType.getText().toString());
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
        boolean useUpperCase = SettingsHelper.useUpperCase(context);
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
    public void onHashTypeSelect(@NonNull HashType hashType) {
        tvSelectedHashType.setText(hashType.getTypeAsString(context));
        SettingsHelper.saveHashTypeAsLast(context, hashType);
    }

    @Override
    public void initializeUI(@NonNull View contentView) {
        context = getContext();

        mainScreen = contentView.findViewById(R.id.fl_main_screen);

        etCustomHash = contentView.findViewById(R.id.et_field_custom_hash);
        etGeneratedHash = contentView.findViewById(R.id.et_field_generated_hash);

        tvSelectedObjectName = contentView.findViewById(R.id.tv_selected_object_name);

        tvSelectedHashType = contentView.findViewById(R.id.tv_selected_hash_type);
        tvSelectedHashType.setOnClickListener(v -> selectHashTypeFromList());

        btnGenerateFrom = contentView.findViewById(R.id.btn_generate_from);
        btnGenerateFrom.setOnClickListener(v -> selectResourceToGenerateHash());

        Button btnHashActions = contentView.findViewById(R.id.btn_hash_actions);
        btnHashActions.setOnClickListener(v -> selectActionForHashes());

        fragmentManager = getActivity().getSupportFragmentManager();
        tvSelectedHashType.setText(SettingsHelper.getLastHashType(context).getTypeAsString(context));
        tvSelectedObjectName.setMovementMethod(new ScrollingMovementMethod());
        if (startWithTextSelection) {
            onUserActionClick(UserActionType.ENTER_TEXT);
            startWithTextSelection = false;
        } else if (startWithFileSelection) {
            onUserActionClick(UserActionType.SEARCH_FILE);
            startWithFileSelection = false;
        }
    }

    private void requestStoragePermission() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                Requests.PERMISSION_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Requests.PERMISSION_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                searchFile();
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    UIUtils.showSnackbar(context, mainScreen,
                            getString(R.string.message_request_storage_permission_error),
                            getString(R.string.common_again), v -> requestStoragePermission(),
                            Snackbar.LENGTH_LONG);
                } else {
                    AppAlertDialog.show(context, R.string.title_permission_dialog,
                            R.string.message_request_storage_permission_denied,
                            R.string.menu_title_settings,
                            (dialog, which) -> AppUtils.openAppSettings(context));
                }
            }
        }
    }

    @Override
    public void onAppResume() {
        super.onAppResume();
        validateTextCase();
        checkMultilinePreference();
        if (SettingsHelper.refreshSelectedFile(context)) {
            if (!isTextSelected && fileUri != null) {
                fileUri = null;
                tvSelectedObjectName.setText(getString(R.string.message_select_object));
                btnGenerateFrom.setText(getString(R.string.action_from));
                SettingsHelper.setRefreshSelectedFileStatus(context, false);
            }
        }
        onHashTypeSelect(SettingsHelper.getLastHashType(context));
    }

    private void checkMultilinePreference() {
        if (SettingsHelper.isUsingMultilineHashFields(context)) {
            validateEditTextWithMultilineSupport(etCustomHash,
                    Constants.Text.TEXT_MULTILINE_LINES_COUNT, false);
            validateEditTextWithMultilineSupport(etGeneratedHash,
                    Constants.Text.TEXT_MULTILINE_LINES_COUNT, false);
        } else {
            validateEditTextWithMultilineSupport(etCustomHash,
                    Constants.Text.TEXT_SINGLE_LINE_LINES_COUNT, true);
            validateEditTextWithMultilineSupport(etGeneratedHash,
                    Constants.Text.TEXT_SINGLE_LINE_LINES_COUNT, true);
        }
    }

    private void validateEditTextWithMultilineSupport(@NonNull EditText editText, int lines,
                                                      boolean singleLine) {
        editText.setSingleLine(singleLine);
        editText.setMinLines(lines);
        editText.setMaxLines(lines);
        editText.setLines(lines);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri uri = data.getData();
            switch (requestCode) {
                case Requests.FILE_SELECT:
                    if (resultCode == Activity.RESULT_OK) {
                        selectFileFromSystemFileManager(uri);
                    }
                    break;
                case Requests.FILE_SELECT_FROM_FILE_MANAGER:
                    selectFileFromAppFileManager(uri);
                    break;
                case Requests.FILE_CREATE:
                    writeHashToFile(uri);
                    break;
            }
        }
    }

    private void selectFileFromSystemFileManager(@NonNull Uri uri) {
        validateSelectedFile(uri);
        SettingsHelper.setGenerateFromShareIntentMode(context, false);
    }

    private void selectFileFromAppFileManager(@NonNull Uri uri) {
        validateSelectedFile(uri);
    }

    private void writeHashToFile(@NonNull Uri uri) {
        try {
            ParcelFileDescriptor fileDescriptor = getActivity().getApplicationContext().getContentResolver()
                    .openFileDescriptor(uri, "w");
            if (fileDescriptor != null) {
                FileOutputStream outputStream = new FileOutputStream(fileDescriptor.getFileDescriptor());
                outputStream.write(etGeneratedHash.getText().toString().getBytes());
                outputStream.close();
                fileDescriptor.close();
            }
        } catch (FileNotFoundException e) {
            L.e(e);
        } catch (IOException e) {
            L.e(e);
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
