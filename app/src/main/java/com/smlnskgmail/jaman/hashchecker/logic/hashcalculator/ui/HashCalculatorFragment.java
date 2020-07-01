package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppProgressDialog;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppSnackbar;
import com.smlnskgmail.jaman.hashchecker.logic.database.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.ui.FileManagerActivity;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashCalculatorTask;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.input.TextInputDialog;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.input.TextValueTarget;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.types.UserActionTarget;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.types.UserActionType;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.ui.ActionSelectActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.ui.SourceSelectActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.hashtypes.GenerateToBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.hashtypes.HashTypeSelectTarget;
import com.smlnskgmail.jaman.hashchecker.logic.history.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.logic.logs.L;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.support.Clipboard;
import com.smlnskgmail.jaman.hashchecker.logic.support.Vibrator;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class HashCalculatorFragment extends BaseFragment
        implements TextValueTarget, UserActionTarget, HashTypeSelectTarget {

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

    private Vibrator vibrator;

    private boolean startWithTextSelection;
    private boolean startWithFileSelection;

    private boolean isTextSelected;

    private final HashCalculatorTask.HashCalculatorTaskTarget hashCalculatorTaskTarget = hashValue -> {
        if (hashValue == null) {
            etGeneratedHash.setText("");
            showSnackbarWithoutAction(
                    getString(R.string.message_invalid_selected_source)
            );
        } else {
            etGeneratedHash.setText(hashValue);
            if (SettingsHelper.canSaveResultToHistory(context)) {
                Date date = Calendar.getInstance().getTime();
                String objectValue = tvSelectedObjectName.getText().toString();
                HashType hashType = HashType.getHashTypeFromString(
                        tvSelectedHashType.getText().toString()
                );
                HistoryItem historyItem = new HistoryItem(
                        date,
                        hashType,
                        !isTextSelected,
                        objectValue,
                        hashValue
                );
                HelperFactory.getHelper().addHistoryItem(historyItem);
            }
        }
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    };

    private void showSnackbarWithoutAction(
            @NonNull String message
    ) {
        new AppSnackbar(
                context,
                mainScreen,
                message,
                vibrator,
                UIUtils.getAccentColor(
                        context
                )
        ).show();
    }

    @Override
    public void userActionSelect(
            @NonNull UserActionType userActionType
    ) {
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
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED) {
                requestStoragePermission();
            } else {
                openInnerFileManager();
            }
        } else {
            openSystemFileManager();
        }
    }

    private void openInnerFileManager() {
        Intent openExplorerIntent = new Intent(
                getContext(),
                FileManagerActivity.class
        );
        String lastPath = SettingsHelper.getLastPathForInnerFileManager(
                context
        );
        if (lastPath != null) {
            openExplorerIntent.putExtra(
                    FileManagerActivity.LAST_PATH,
                    lastPath
            );
        }
        startActivityForResult(
                openExplorerIntent,
                FileManagerActivity.FILE_SELECT_FROM_FILE_MANAGER
        );
    }

    private void openSystemFileManager() {
        try {
            Intent openExplorerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            openExplorerIntent.addCategory(Intent.CATEGORY_OPENABLE);
            openExplorerIntent.setType("*/*");
            startActivityForResult(
                    openExplorerIntent,
                    FileManagerActivity.FILE_SELECT
            );
        } catch (ActivityNotFoundException e) {
            L.e(e);
            showSnackbarWithoutAction(
                    getString(R.string.message_error_start_file_selector)
            );
        }
    }

    @SuppressLint("ResourceType")
    private void generateHash() {
        if (fileUri != null || isTextSelected) {
            HashType hashType = HashType.getHashTypeFromString(
                    tvSelectedHashType.getText().toString()
            );
            progressDialog = AppProgressDialog.getDialog(
                    context,
                    R.string.message_generate_dialog
            );
            progressDialog.show();
            if (isTextSelected) {
                new HashCalculatorTask(
                        hashType,
                        context,
                        tvSelectedObjectName.getText().toString(),
                        hashCalculatorTaskTarget
                ).execute();
            } else {
                new HashCalculatorTask(
                        hashType,
                        context,
                        fileUri,
                        hashCalculatorTaskTarget
                ).execute();
            }
        } else {
            showSnackbarWithoutAction(
                    getString(R.string.message_select_object)
            );
        }
    }

    private void compareHashes() {
        if (TextTools.fieldIsNotEmpty(etCustomHash)
                && TextTools.fieldIsNotEmpty(etGeneratedHash)) {
            boolean equal = TextTools.compareText(
                    etCustomHash.getText().toString(),
                    etGeneratedHash.getText().toString()
            );
            showSnackbarWithoutAction(
                    getString(
                            equal ? R.string.message_match_result
                                    : R.string.message_do_not_match_result
                    )
            );
        } else {
            showSnackbarWithoutAction(
                    getString(R.string.message_fill_fields)
            );
        }
    }

    private void selectHashTypeFromList() {
        GenerateToBottomSheet generateToBottomSheet = new GenerateToBottomSheet();
        generateToBottomSheet.show(
                getFragmentManager(),
                generateToBottomSheet.getClass().getCanonicalName()
        );
    }

    private void saveGeneratedHashAsTextFile() {
        if ((fileUri != null
                || isTextSelected) && TextTools.fieldIsNotEmpty(etGeneratedHash)) {
            String filename = getString(
                    isTextSelected
                            ? R.string.filename_hash_from_text
                            : R.string.filename_hash_from_file
            );
            saveTextFile(filename);
        } else {
            showSnackbarWithoutAction(
                    getString(R.string.message_generate_hash_before_export)
            );
        }
    }

    private void saveTextFile(@NonNull String filename) {
        try {
            Intent saveTextFileIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            saveTextFileIntent.addCategory(Intent.CATEGORY_OPENABLE);
            saveTextFileIntent.setType("text/plain");
            saveTextFileIntent.putExtra(
                    Intent.EXTRA_TITLE,
                    filename + ".txt"
            );
            startActivityForResult(
                    saveTextFileIntent,
                    SettingsHelper.FILE_CREATE
            );
        } catch (ActivityNotFoundException e) {
            L.e(e);
            showSnackbarWithoutAction(
                    getString(R.string.message_error_start_file_selector)
            );
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (checkArguments(bundle)) {
            checkShortcutActionPresence(bundle);
        }
    }

    private boolean checkArguments(@Nullable Bundle bundle) {
        return bundle != null;
    }

    private void checkShortcutActionPresence(
            @NonNull Bundle shortcutsArguments
    ) {
        startWithTextSelection = shortcutsArguments.getBoolean(
                App.ACTION_START_WITH_TEXT,
                false
        );
        startWithFileSelection = shortcutsArguments.getBoolean(
                App.ACTION_START_WITH_FILE,
                false
        );

        shortcutsArguments.remove(App.ACTION_START_WITH_TEXT);
        shortcutsArguments.remove(App.ACTION_START_WITH_FILE);
    }

    private void validateSelectedFile(@Nullable Uri uri) {
        if (uri != null) {
            fileUri = uri;
            isTextSelected = false;
            setResult(fileNameFromUri(fileUri), false);
        }
    }

    private String fileNameFromUri(@NonNull Uri uri) {
        String scheme = uri.getScheme();
        if (scheme != null && scheme.equals("content")) {
            try (Cursor cursor = context.getContentResolver()
                    .query(uri, null, null, null, null)) {
                assert cursor != null;
                cursor.moveToPosition(0);
                return cursor.getString(
                        cursor.getColumnIndex(
                                OpenableColumns.DISPLAY_NAME
                        )
                );
            } catch (Exception e) {
                L.e(e);
            }
        }
        return new File(uri.getPath()).getName();
    }

    @Override
    public void textValueEntered(@NonNull String text) {
        setResult(text, true);
    }

    private void setResult(
            @NonNull String text,
            boolean isText
    ) {
        tvSelectedObjectName.setText(text);
        this.isTextSelected = isText;
        btnGenerateFrom.setText(
                getString(
                        isText ? R.string.common_text : R.string.common_file
                )
        );
    }

    private void enterText() {
        String currentText = !isTextSelected
                ? null
                : tvSelectedObjectName.getText().toString();
        new TextInputDialog(
                context,
                this,
                currentText
        ).show();
    }

    @Override
    public void appBackClick() {
        showSnachbarWithAction(
                getView().findViewById(R.id.fl_main_screen),
                getString(R.string.message_exit),
                getString(R.string.action_exit_now),
                v -> getActivity().finish()
        );
    }

    private void showSnachbarWithAction(
            @NonNull View mainScreen,
            @NonNull String message,
            @NonNull String actionText,
            @NonNull View.OnClickListener action
    ) {
        new AppSnackbar(
                context,
                mainScreen,
                message,
                actionText,
                action,
                vibrator,
                UIUtils.getAccentColor(context)
        ).show();
    }

    private void validateTextCase() {
        boolean useUpperCase = SettingsHelper.useUpperCase(context);
        InputFilter[] fieldFilters = useUpperCase
                ? new InputFilter[]{new InputFilter.AllCaps()}
                : new InputFilter[]{};
        etCustomHash.setFilters(fieldFilters);
        etGeneratedHash.setFilters(fieldFilters);

        if (useUpperCase) {
            TextTools.convertToUpperCase(etCustomHash);
            TextTools.convertToUpperCase(etGeneratedHash);
        } else {
            TextTools.convertToLowerCase(etCustomHash);
            TextTools.convertToLowerCase(etGeneratedHash);
        }

        etCustomHash.setSelection(
                etCustomHash.getText().length()
        );
        etGeneratedHash.setSelection(
                etGeneratedHash.getText().length()
        );
    }

    @Override
    public void hashTypeSelect(
            @NonNull HashType hashType
    ) {
        tvSelectedHashType.setText(
                hashType.getTypeAsString()
        );
        SettingsHelper.saveHashTypeAsLast(
                context,
                hashType
        );
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        vibrator = new Vibrator(context);

        mainScreen = view.findViewById(R.id.fl_main_screen);

        etCustomHash = view.findViewById(R.id.et_field_custom_hash);
        etGeneratedHash = view.findViewById(R.id.et_field_generated_hash);

        ImageView ivCustomHashCopy = view.findViewById(R.id.iv_custom_hash_copy);
        ivCustomHashCopy.setOnClickListener(v -> copyHashFromEditText(etCustomHash));

        ImageView ivCustomHashClear = view.findViewById(R.id.iv_custom_hash_clear);
        ivCustomHashClear.setOnClickListener(v -> etCustomHash.setText(""));

        ImageView ivGeneratedHashCopy = view.findViewById(R.id.iv_generated_hash_copy);
        ivGeneratedHashCopy.setOnClickListener(v -> copyHashFromEditText(etGeneratedHash));

        ImageView ivGeneratedHashClear = view.findViewById(R.id.iv_generated_hash_clear);
        ivGeneratedHashClear.setOnClickListener(v -> etGeneratedHash.setText(""));

        etGeneratedHash.addTextChangedListener(
                watcherForInputField(
                        ivGeneratedHashCopy,
                        ivGeneratedHashClear
                )
        );
        etCustomHash.addTextChangedListener(
                watcherForInputField(
                        ivCustomHashCopy,
                        ivCustomHashClear
                )
        );

        tvSelectedObjectName = view.findViewById(R.id.tv_selected_object_name);

        tvSelectedHashType = view.findViewById(R.id.tv_selected_hash_type);
        tvSelectedHashType.setOnClickListener(v -> selectHashTypeFromList());

        btnGenerateFrom = view.findViewById(R.id.btn_generate_from);
        btnGenerateFrom.setOnClickListener(v -> showSourceSelectDialog());

        Button btnHashActions = view.findViewById(R.id.btn_hash_actions);
        btnHashActions.setOnClickListener(v -> showActionSelectDialog());

        fragmentManager = getActivity().getSupportFragmentManager();
        tvSelectedHashType.setText(
                SettingsHelper.getLastHashType(context).getTypeAsString()
        );
        tvSelectedObjectName.setMovementMethod(
                new ScrollingMovementMethod()
        );
        if (startWithTextSelection) {
            userActionSelect(UserActionType.ENTER_TEXT);
            startWithTextSelection = false;
        } else if (startWithFileSelection) {
            userActionSelect(UserActionType.SEARCH_FILE);
            startWithFileSelection = false;
        }

        Bundle bundle = getArguments();
        if (checkArguments(bundle)) {
            checkExternalDataPresence(bundle);
        }
    }

    private TextWatcher watcherForInputField(
            @NonNull ImageView copyButton,
            @NonNull ImageView clearButton
    ) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s,
                    int start,
                    int count,
                    int after
            ) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s,
                    int start,
                    int before,
                    int count
            ) {
                int writtenTextLength = s.toString().length();
                if (writtenTextLength > 0) {
                    copyButton.setVisibility(View.VISIBLE);
                    clearButton.setVisibility(View.VISIBLE);
                } else {
                    copyButton.setVisibility(View.INVISIBLE);
                    clearButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    private void copyHashFromEditText(
            @NonNull EditText editText
    ) {
        String hash = editText.getText().toString();
        if (!hash.isEmpty()) {
            new Clipboard(
                    context,
                    hash
            ).copy();
            showHashCopySnackbar();
        }
    }

    private void showSourceSelectDialog() {
        SourceSelectActionsBottomSheet sourceSelectActionsBottomSheet
                = new SourceSelectActionsBottomSheet();
        sourceSelectActionsBottomSheet.show(
                fragmentManager,
                sourceSelectActionsBottomSheet.getClass().getCanonicalName()
        );
    }

    private void showActionSelectDialog() {
        ActionSelectActionsBottomSheet actionSelectActionsBottomSheet
                = new ActionSelectActionsBottomSheet();
        actionSelectActionsBottomSheet.show(
                fragmentManager,
                actionSelectActionsBottomSheet.getClass().getCanonicalName()
        );
    }

    private void showHashCopySnackbar() {
        showSnackbarWithoutAction(
                getString(R.string.history_item_click_text)
        );
    }

    private void checkExternalDataPresence(@NonNull Bundle dataArguments) {
        String uri = dataArguments.getString(MainActivity.URI_FROM_EXTERNAL_APP);
        if (uri != null) {
            validateSelectedFile(Uri.parse(uri));
            dataArguments.remove(MainActivity.URI_FROM_EXTERNAL_APP);
        }
    }

    private void requestStoragePermission() {
        requestPermissions(
                new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE },
                FileManagerActivity.PERMISSION_STORAGE
        );
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FileManagerActivity.PERMISSION_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                searchFile();
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showSnachbarWithAction(
                            mainScreen,
                            getString(R.string.message_request_storage_permission_error),
                            getString(R.string.common_again),
                            v -> requestStoragePermission()
                    );
                } else {
                    AppAlertDialog.show(
                            context,
                            R.string.title_permission_dialog,
                            R.string.message_request_storage_permission_denied,
                            R.string.menu_title_settings,
                            (dialog, which) -> openAppSettings()
                    );
                }
            }
        }
    }

    private void openAppSettings() {
        try {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts(
                    "package",
                    context.getPackageName(),
                    null
            );
            intent.setData(uri);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            L.e(e);
            context.startActivity(
                    new Intent(android.provider.Settings.ACTION_SETTINGS)
            );
        }
    }

    @Override
    public void appResume() {
        super.appResume();
        validateTextCase();
        checkMultilinePreference();
        checkFileManagerChanged();
        hashTypeSelect(
                SettingsHelper.getLastHashType(context)
        );
    }

    private void checkMultilinePreference() {
        if (SettingsHelper.isUsingMultilineHashFields(context)) {
            validateMultilineFields(
                    etCustomHash,
                    TEXT_MULTILINE_LINES_COUNT,
                    false
            );
            validateMultilineFields(
                    etGeneratedHash,
                    TEXT_MULTILINE_LINES_COUNT,
                    false
            );
        } else {
            validateMultilineFields(
                    etCustomHash,
                    TEXT_SINGLE_LINE_LINES_COUNT,
                    true
            );
            validateMultilineFields(
                    etGeneratedHash,
                    TEXT_SINGLE_LINE_LINES_COUNT,
                    true
            );
        }
    }

    private void validateMultilineFields(
            @NonNull EditText editText,
            int lines,
            boolean singleLine
    ) {
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
    public void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data
    ) {
        if (data != null) {
            switch (requestCode) {
                case FileManagerActivity.FILE_SELECT:
                    if (resultCode == Activity.RESULT_OK) {
                        selectFileFromSystemFileManager(data.getData());
                    }
                    break;
                case FileManagerActivity.FILE_SELECT_FROM_FILE_MANAGER:
                    selectFileFromAppFileManager(data);
                    break;
                case SettingsHelper.FILE_CREATE:
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
        SettingsHelper.setGenerateFromShareIntentMode(
                context,
                false
        );
    }

    private void selectFileFromAppFileManager(@NonNull Intent data) {
        String path = data.getStringExtra(FileManagerActivity.FILE_SELECT_DATA);
        if (path != null) {
            String lastPath = data.getStringExtra(FileManagerActivity.LAST_PATH);
            if (lastPath != null) {
                SettingsHelper.savePathForInnerFileManager(
                        context,
                        lastPath
                );
            }
            Uri uri = Uri.fromFile(new File(path));
            validateSelectedFile(uri);
        }
    }

    private void writeHashToFile(@Nullable Uri uri) throws IOException {
        if (uri != null) {
            ParcelFileDescriptor fileDescriptor = getActivity().getApplicationContext()
                    .getContentResolver()
                    .openFileDescriptor(uri, "w");
            if (fileDescriptor != null) {
                FileOutputStream outputStream = new FileOutputStream(
                        fileDescriptor.getFileDescriptor()
                );
                outputStream.write(
                        etGeneratedHash.getText().toString().getBytes()
                );
                outputStream.close();
                fileDescriptor.close();
            }
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_hash_calculator;
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
    public boolean setAllowBackAction() {
        return false;
    }

}
