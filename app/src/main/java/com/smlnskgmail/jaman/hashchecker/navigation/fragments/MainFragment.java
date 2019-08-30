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

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.calculator.HashGenerator;
import com.smlnskgmail.jaman.hashchecker.calculator.support.HashGeneratorTarget;
import com.smlnskgmail.jaman.hashchecker.calculator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.Action;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.ActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.types.UserActionTarget;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.types.UserActionType;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.hashtypes.GenerateToBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.hashtypes.HashTypeSelectTarget;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.app.input.TextInputDialog;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.app.input.TextValueTarget;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppProgressDialog;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.manager.support.Requests;
import com.smlnskgmail.jaman.hashchecker.db.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.navigation.fragments.history.entities.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.support.logs.L;
import com.smlnskgmail.jaman.hashchecker.support.prefs.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.utils.TextUtils;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class MainFragment extends BaseFragment implements TextValueTarget, HashGeneratorTarget,
        UserActionTarget, HashTypeSelectTarget {

    private static final int TEXT_MULTILINE_LINES_COUNT = 3;
    private static final int TEXT_SINGLE_LINE_LINES_COUNT = 1;

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
    public void onUserActionSelect(@NonNull UserActionType userActionType) {
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
                saveGeneratedHashAsTextFile();
                break;
        }
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
            UIUtils.showSnackbar(context, mainScreen, getString(R.string.message_select_object));
        }
    }

    private void compareHashes() {
        if (TextUtils.fieldIsNotEmpty(etCustomHash) && TextUtils.fieldIsNotEmpty(etGeneratedHash)) {
            boolean equal = TextUtils.compareText(etCustomHash.getText().toString(),
                    etGeneratedHash.getText().toString());
            UIUtils.showSnackbar(context, mainScreen, equal ? getString(R.string.message_match_result) :
                    getString(R.string.message_do_not_match_result));
        } else {
            UIUtils.showSnackbar(context, mainScreen, getString(R.string.message_fill_fields));
        }
    }

    private void selectHashTypeFromList() {
        GenerateToBottomSheet generateToBottomSheet = new GenerateToBottomSheet();
        generateToBottomSheet.setItems(Arrays.asList(HashType.values()));
        generateToBottomSheet.setHashTypeSelectListener(this);
        generateToBottomSheet.showBottomSheet(getFragmentManager());
    }

    private void selectResourceToGenerateHash() {
        showBottomSheetWithActions(Action.TEXT, Action.FILE);
    }

    private void selectActionForHashes() {
        showBottomSheetWithActions(Action.GENERATE, Action.COMPARE, Action.EXPORT_AS_TXT);
    }


    private void saveGeneratedHashAsTextFile() {
        if ((fileUri != null || isTextSelected) && TextUtils.fieldIsNotEmpty(etGeneratedHash)) {
            String filename = getString(isTextSelected ? R.string.filename_hash_from_text
                    : R.string.filename_hash_from_file);
            AppUtils.saveTextFile(this, filename, mainScreen);
        } else {
            UIUtils.showSnackbar(context, mainScreen, getString(R.string.message_generate_hash_before_export));
        }
    }

    private void showBottomSheetWithActions(Action... actions) {
        ActionsBottomSheet actionsBottomSheet = new ActionsBottomSheet();
        actionsBottomSheet.setActions(Arrays.asList(actions));
        actionsBottomSheet.setUserActionTarget(MainFragment.this);
        actionsBottomSheet.showBottomSheet(fragmentManager);
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
        String uri = dataArguments.getString(MainActivity.URI_FROM_EXTERNAL_APP);
        if (uri != null) {
            validateSelectedFile(Uri.parse(uri));
            dataArguments.remove(MainActivity.URI_FROM_EXTERNAL_APP);
        }
    }

    private void checkShortcutActionPresence(@NonNull Bundle shortcutsArguments) {
        startWithTextSelection = shortcutsArguments
                .getBoolean(App.ACTION_START_WITH_TEXT, false);
        startWithFileSelection = shortcutsArguments
                .getBoolean(App.ACTION_START_WITH_FILE, false);

        shortcutsArguments.remove(App.ACTION_START_WITH_TEXT);
        shortcutsArguments.remove(App.ACTION_START_WITH_FILE);
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
    public void hashGenerationComplete(@Nullable String hashValue) {
        if (hashValue == null) {
            etGeneratedHash.setText("");
            UIUtils.showSnackbar(context, mainScreen, getString(R.string.message_invalid_selected_source));
        } else {
            etGeneratedHash.setText(hashValue);
            if (SettingsHelper.canSaveResultToHistory(context)) {
                Date date = Calendar.getInstance().getTime();
                String objectValue = tvSelectedObjectName.getText().toString();
                HashType hashType = HashType.parseHashTypeFromString(context,
                        tvSelectedHashType.getText().toString());
                HistoryItem historyItem = new HistoryItem(date, hashType, !isTextSelected, objectValue,
                        hashValue);
                HelperFactory.getHelper().addHistoryItem(historyItem);
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
    public void onBackClick() {
        UIUtils.showSnackbar(context, getView().findViewById(R.id.fl_main_screen),
                getString(R.string.message_exit), getString(R.string.action_exit_now),
                v -> AppUtils.closeApp(getActivity()));
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
            onUserActionSelect(UserActionType.ENTER_TEXT);
            startWithTextSelection = false;
        } else if (startWithFileSelection) {
            onUserActionSelect(UserActionType.SEARCH_FILE);
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
                            getString(R.string.common_again), v -> requestStoragePermission());
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
        checkFileManagerChanged();
        onHashTypeSelect(SettingsHelper.getLastHashType(context));
    }

    private void checkMultilinePreference() {
        if (SettingsHelper.isUsingMultilineHashFields(context)) {
            validateMultilineFields(etCustomHash,
                    TEXT_MULTILINE_LINES_COUNT, false);
            validateMultilineFields(etGeneratedHash,
                    TEXT_MULTILINE_LINES_COUNT, false);
        } else {
            validateMultilineFields(etCustomHash,
                    TEXT_SINGLE_LINE_LINES_COUNT, true);
            validateMultilineFields(etGeneratedHash,
                    TEXT_SINGLE_LINE_LINES_COUNT, true);
        }
    }

    private void validateMultilineFields(@NonNull EditText editText, int lines, boolean singleLine) {
        editText.setSingleLine(singleLine);
        editText.setMinLines(lines);
        editText.setMaxLines(lines);
        editText.setLines(lines);
    }

    private void checkFileManagerChanged() {
        if (SettingsHelper.refreshSelectedFile(context)) {
            if (!isTextSelected && fileUri != null) {
                fileUri = null;
                tvSelectedObjectName.setText(getString(R.string.message_select_object));
                btnGenerateFrom.setText(getString(R.string.action_from));
            }
            SettingsHelper.setRefreshSelectedFileStatus(context, false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            switch (requestCode) {
                case Requests.FILE_SELECT:
                    if (resultCode == Activity.RESULT_OK) {
                        selectFileFromSystemFileManager(data.getData());
                    }
                    break;
                case Requests.FILE_SELECT_FROM_FILE_MANAGER:
                    selectFileFromAppFileManager(data);
                    break;
                case Requests.FILE_CREATE:
                    try {
                        writeHashToFile(data.getData());
                    } catch (IOException e) {
                        L.e(e);
                    }
                    break;
            }
        }
    }

    private void selectFileFromSystemFileManager(@Nullable Uri uri) {
        validateSelectedFile(uri);
        SettingsHelper.setGenerateFromShareIntentMode(context, false);
    }

    private void selectFileFromAppFileManager(@NonNull Intent data) {
        String path = data.getStringExtra(Requests.FILE_SELECT_DATA);
        if (path != null) {
            Uri uri = Uri.fromFile(new File(path));
            validateSelectedFile(uri);
        }
    }

    private void writeHashToFile(@Nullable Uri uri) throws IOException {
        if (uri != null) {
            ParcelFileDescriptor fileDescriptor = getActivity().getApplicationContext().getContentResolver()
                    .openFileDescriptor(uri, "w");
            if (fileDescriptor != null) {
                FileOutputStream outputStream = new FileOutputStream(fileDescriptor.getFileDescriptor());
                outputStream.write(etGeneratedHash.getText().toString().getBytes());
                outputStream.close();
                fileDescriptor.close();
            }
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    public int getActionBarTitleResId() {
        return R.string.common_app_name;
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
