package com.smlnskgmail.jaman.hashchecker.features.hashcalculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
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
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.clipboard.Clipboard;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashCalculatorTask;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.models.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.input.TextInputDialog;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.input.TextValueTarget;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.types.UserActionTarget;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.types.UserActionType;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.ui.ActionSelectActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.ui.SourceSelectActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.hashtypes.GenerateToBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.hashtypes.HashTypeSelectTarget;
import com.smlnskgmail.jaman.hashchecker.ui.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.ui.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.ui.dialogs.system.AppProgressDialog;
import com.smlnskgmail.jaman.hashchecker.ui.dialogs.system.AppSnackbar;
import com.smlnskgmail.jaman.hashchecker.ui.watchers.AppTextWatcher;
import com.smlnskgmail.jaman.hashchecker.utils.LogUtils;
import com.smlnskgmail.jaman.hashchecker.utils.WebUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

public class HashCalculatorFragment extends BaseFragment
        implements TextValueTarget, UserActionTarget, HashTypeSelectTarget {

    private static final int FILE_SELECT = 193;
    private static final int FOLDER_SELECT = 194;

    private static final int TEXT_MULTILINE_LINES_COUNT = 3;
    private static final int TEXT_SINGLE_LINE_LINES_COUNT = 1;

    @Inject
    public LocalDataStorage localDataStorage;

    @Inject
    public Settings settings;

    @Inject
    public LanguageConfig languageConfig;

    @Inject
    public ThemeConfig themeConfig;

    private View mainScreen;

    private EditText etCustomHash;
    private EditText etGeneratedHash;

    private TextView tvSelectedObjectName;
    private TextView tvSelectedHashType;

    private Button btnGenerateFrom;

    private ProgressDialog progressDialog;

    private Uri fileUri;
    private Uri folderUri;

    private Context context;
    private FragmentManager fragmentManager;

    private boolean startWithTextSelection;
    private boolean startWithFileSelection;
    private boolean startWithFolderSelection;

    private boolean isTextSelected;

    private boolean isBackButtonDoubleTap = false;

    private final HashCalculatorTask.HashCalculatorTaskTarget hashCalculatorTaskTarget = hashValue -> {
        if (hashValue == null) {
            etGeneratedHash.setText("");
            showSnackbarWithoutAction(R.string.message_invalid_selected_source);
        } else {
            boolean useUpperCase = settings.useUpperCase();
            etGeneratedHash.setText(useUpperCase ? hashValue.toUpperCase() : hashValue);
            if (settings.canSaveResultToHistory()) {
                Date date = Calendar.getInstance().getTime();
                String objectValue = tvSelectedObjectName.getText().toString();
                HashType hashType = HashType.getHashTypeFromString(tvSelectedHashType.getText().toString());
                HistoryItem historyItem = new HistoryItem(
                        date,
                        hashType,
                        !isTextSelected,
                        objectValue,
                        hashValue
                );
                localDataStorage.addHistoryItem(historyItem);
            }
            if (settings.canShowRateAppDialog()) {
                settings.increaseHashGenerationCount();
                new AppAlertDialog(
                        context,
                        R.string.settings_title_rate_app,
                        R.string.rate_app_message,
                        R.string.rate_app_action,
                        (dialog, which) -> WebUtils.openGooglePlay(context, getView(), settings, themeConfig),
                        themeConfig
                ).show();
            } else {
                settings.increaseHashGenerationCount();
            }
        }
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    };

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    private void showSnackbarWithoutAction(@StringRes int messageResId) {
        new AppSnackbar(context, mainScreen, messageResId, settings, themeConfig).show();
    }

    @Override
    public void userActionSelect(@NonNull UserActionType userActionType) {
        switch (userActionType) {
            case ENTER_TEXT:
                enterText();
                break;
            case SEARCH_FILE:
                openSystemFileManager();
                break;
            case SEARCH_FOLDER:
                openSystemFolderManager();
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
            default:
                throw new IllegalArgumentException("Unknown UserActionType");
        }
    }

    private void openSystemFileManager() {
        try {
            Intent openExplorerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            openExplorerIntent.addCategory(Intent.CATEGORY_OPENABLE);
            openExplorerIntent.setType("*/*");
            startActivityForResult(openExplorerIntent, FILE_SELECT);
        } catch (ActivityNotFoundException e) {
            LogUtils.e(e);
            showSnackbarWithoutAction(R.string.message_error_start_file_selector);
        }
    }

    private void openSystemFolderManager() {
        try {
            Intent openExplorerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            openExplorerIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(openExplorerIntent, FOLDER_SELECT);
        } catch (ActivityNotFoundException e) {
            LogUtils.e(e);
            showSnackbarWithoutAction(R.string.message_error_start_file_selector);
        }
    }

    @SuppressLint("ResourceType")
    private void generateHash() {
        if (fileUri != null || isTextSelected || folderUri != null) {
            HashType hashType = HashType.getHashTypeFromString(tvSelectedHashType.getText().toString());
            progressDialog = new AppProgressDialog(context, R.string.message_generate_dialog).getDialog();
            progressDialog.show();
            if (isTextSelected) {
                new HashCalculatorTask(
                        context,
                        hashType,
                        tvSelectedObjectName.getText().toString(),
                        hashCalculatorTaskTarget
                ).execute();
            } else if (fileUri != null) {
                new HashCalculatorTask(
                        context,
                        hashType,
                        fileUri,
                        folderUri,
                        hashCalculatorTaskTarget
                ).execute();
            } else {
                new HashCalculatorTask(
                        context,
                        hashType,
                        fileUri,
                        folderUri,
                        hashCalculatorTaskTarget
                ).execute();
            }
        } else {
            showSnackbarWithoutAction(R.string.message_select_object);
        }
    }

    private void compareHashes() {
        if (fieldIsNotEmpty(etCustomHash) && fieldIsNotEmpty(etGeneratedHash)) {
            boolean equal = compareText(etCustomHash.getText().toString(), etGeneratedHash.getText().toString());
            showSnackbarWithoutAction(
                    equal ? R.string.message_match_result : R.string.message_do_not_match_result
            );
        } else {
            showSnackbarWithoutAction(R.string.message_fill_fields);
        }
    }

    private void selectHashTypeFromList() {
        GenerateToBottomSheet generateToBottomSheet = new GenerateToBottomSheet();
        generateToBottomSheet.show(getFragmentManager(), generateToBottomSheet.getTag());
    }

    private void saveGeneratedHashAsTextFile() {
        if ((fileUri != null || isTextSelected || folderUri != null) && fieldIsNotEmpty(etGeneratedHash)) {
            saveTextFile(tvSelectedObjectName.getText().toString());
        } else {
            showSnackbarWithoutAction(R.string.message_generate_hash_before_export);
        }
    }

    private void saveTextFile(@NonNull String filename) {
        try {
            Intent saveTextFileIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            saveTextFileIntent.addCategory(Intent.CATEGORY_OPENABLE);
            saveTextFileIntent.setType("text/plain");
            saveTextFileIntent.putExtra(
                    Intent.EXTRA_TITLE,
                    String.format(
                            "%s.%s.txt",
                            filename,
                            settings.getLastHashType().getFileExtension()
                    )
            );
            startActivityForResult(saveTextFileIntent, Settings.FILE_CREATE);
        } catch (ActivityNotFoundException e) {
            LogUtils.e(e);
            showSnackbarWithoutAction(R.string.message_error_start_file_selector);
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

    private void checkShortcutActionPresence(@NonNull Bundle shortcutsArguments) {
        startWithTextSelection = shortcutsArguments.getBoolean(App.ACTION_START_WITH_TEXT, false);
        startWithFileSelection = shortcutsArguments.getBoolean(App.ACTION_START_WITH_FILE, false);
        startWithFolderSelection = shortcutsArguments.getBoolean(App.ACTION_START_WITH_FOLDER, false);

        shortcutsArguments.remove(App.ACTION_START_WITH_TEXT);
        shortcutsArguments.remove(App.ACTION_START_WITH_FILE);
        shortcutsArguments.remove(App.ACTION_START_WITH_FOLDER);
    }

    private void validateSelectedFile(@Nullable Uri uri) {
        if (uri != null) {
            fileUri = uri;
            folderUri = null;
            isTextSelected = false;
            setResult(fileNameFromUri(fileUri), false);
        }
    }

    private void validateSelectedFolder(@Nullable Uri uri) {
        if (uri != null) {
            folderUri = uri;
            fileUri = null;
            isTextSelected = false;
            setResult(folderNameFromUri(folderUri), false);
        }
    }

    @NonNull
    private String fileNameFromUri(@NonNull Uri uri) {
        String scheme = uri.getScheme();
        if (scheme != null && scheme.equals("content")) {
            try (Cursor cursor = context.getContentResolver().query(uri, null, null, null, null)) {
                assert cursor != null;
                cursor.moveToPosition(0);
                return cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME));
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return new File(uri.getPath()).getName();
    }

    private String folderNameFromUri(@NonNull Uri uri) {
        return new File(uri.getPath()).getName();
    }

    @Override
    public void textValueEntered(@NonNull String text) {
        setResult(text, true);
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
    public void appBackClick() {
        Activity activity = getActivity();
        if (activity != null) {
            if (!isBackButtonDoubleTap) {
                isBackButtonDoubleTap = true;
                View view = getView();
                if (view != null) {
                    showSnachbarWithAction(
                            view.findViewById(R.id.fl_main_screen),
                            R.string.message_exit,
                            getString(R.string.action_exit_now),
                            v -> activity.finish()
                    );
                    new Handler().postDelayed(() -> isBackButtonDoubleTap = false, 300);
                }
            } else {
                activity.finish();
            }
        }
    }

    private void showSnachbarWithAction(
            @NonNull View mainScreen,
            @StringRes int messageResId,
            @NonNull String actionText,
            @NonNull View.OnClickListener action
    ) {
        new AppSnackbar(
                context,
                mainScreen,
                messageResId,
                actionText,
                action,
                settings,
                themeConfig
        ).show();
    }

    private void validateTextCase() {
        boolean useUpperCase = settings.useUpperCase();
        InputFilter[] fieldFilters = useUpperCase
                ? new InputFilter[]{new InputFilter.AllCaps()}
                : new InputFilter[]{};
        etCustomHash.setFilters(fieldFilters);
        etGeneratedHash.setFilters(fieldFilters);

        if (useUpperCase) {
            convertToUpperCase(etCustomHash);
            convertToUpperCase(etGeneratedHash);
        } else {
            convertToLowerCase(etCustomHash);
            convertToLowerCase(etGeneratedHash);
        }

        etCustomHash.setSelection(etCustomHash.getText().length());
        etGeneratedHash.setSelection(etGeneratedHash.getText().length());
    }

    @Override
    public void hashTypeSelect(@NonNull HashType hashType) {
        tvSelectedHashType.setText(hashType.getTypeAsString());
        settings.saveHashTypeAsLast(hashType);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

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
        btnGenerateFrom.setOnClickListener(v -> {
            SourceSelectActionsBottomSheet sourceSelectActionsBottomSheet = new SourceSelectActionsBottomSheet();
            sourceSelectActionsBottomSheet.show(fragmentManager, sourceSelectActionsBottomSheet.getTag());
        });

        Button btnHashActions = view.findViewById(R.id.btn_hash_actions);
        btnHashActions.setOnClickListener(v -> {
            ActionSelectActionsBottomSheet actionSelectActionsBottomSheet = new ActionSelectActionsBottomSheet();
            actionSelectActionsBottomSheet.show(fragmentManager, actionSelectActionsBottomSheet.getTag());
        });

        fragmentManager = getActivity().getSupportFragmentManager();
        tvSelectedHashType.setText(settings.getLastHashType().getTypeAsString());
        tvSelectedObjectName.setMovementMethod(new ScrollingMovementMethod());
        if (startWithTextSelection) {
            userActionSelect(UserActionType.ENTER_TEXT);
            startWithTextSelection = false;
        } else if (startWithFileSelection) {
            userActionSelect(UserActionType.SEARCH_FILE);
            startWithFileSelection = false;
        } else if (startWithFolderSelection) {
            userActionSelect(UserActionType.SEARCH_FOLDER);
            startWithFolderSelection = false;
        }

        Bundle bundle = getArguments();
        if (checkArguments(bundle)) {
            checkExternalDataPresence(bundle);
        }
    }

    @NonNull
    @Override
    protected LanguageConfig langHelper() {
        return languageConfig;
    }

    @NonNull
    @SuppressWarnings("MethodParametersAnnotationCheck")
    private TextWatcher watcherForInputField(@NonNull ImageView copyButton, @NonNull ImageView clearButton) {
        return new AppTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int writtenTextLength = s.toString().length();
                if (writtenTextLength > 0) {
                    copyButton.setVisibility(View.VISIBLE);
                    clearButton.setVisibility(View.VISIBLE);
                } else {
                    copyButton.setVisibility(View.INVISIBLE);
                    clearButton.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    private void copyHashFromEditText(@NonNull EditText editText) {
        String hash = editText.getText().toString();
        if (!hash.isEmpty()) {
            new Clipboard(context, hash).copy();
            showSnackbarWithoutAction(R.string.history_item_click_text);
        }
    }

    private void checkExternalDataPresence(@NonNull Bundle dataArguments) {
        String uri = dataArguments.getString(MainActivity.URI_FROM_EXTERNAL_APP);
        if (uri != null) {
            validateSelectedFile(Uri.parse(uri));
            dataArguments.remove(MainActivity.URI_FROM_EXTERNAL_APP);
        }
    }

    @Override
    public void appResume() {
        super.appResume();
        validateTextCase();
        checkMultilinePreference();
        checkFileManagerChanged();
        hashTypeSelect(settings.getLastHashType());
    }

    private void checkMultilinePreference() {
        if (settings.isUsingMultilineHashFields()) {
            validateMultilineFields(etCustomHash, TEXT_MULTILINE_LINES_COUNT, false);
            validateMultilineFields(etGeneratedHash, TEXT_MULTILINE_LINES_COUNT, false);
        } else {
            validateMultilineFields(etCustomHash, TEXT_SINGLE_LINE_LINES_COUNT, true);
            validateMultilineFields(etGeneratedHash, TEXT_SINGLE_LINE_LINES_COUNT, true);
        }
    }

    private void validateMultilineFields(@NonNull EditText editText, int lines, boolean singleLine) {
        editText.setSingleLine(singleLine);
        editText.setMinLines(lines);
        editText.setMaxLines(lines);
        editText.setLines(lines);
    }

    private void checkFileManagerChanged() {
        if (settings.refreshSelectedFile()) {
            if (!isTextSelected && fileUri != null) {
                fileUri = null;
                tvSelectedObjectName.setText(getString(R.string.message_select_object));
                btnGenerateFrom.setText(getString(R.string.action_from));
            }
            settings.setRefreshSelectedFileStatus(false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            switch (requestCode) {
                case FILE_SELECT:
                    if (resultCode == Activity.RESULT_OK) {
                        selectFileFromSystemFileManager(data.getData());
                    }
                    break;
                case Settings.FILE_CREATE:
                    try {
                        writeHashToFile(data.getData());
                    } catch (IOException e) {
                        LogUtils.e(e);
                    }
                    break;
                case FOLDER_SELECT:
                    if (resultCode == Activity.RESULT_OK && data != null) {
                        selectFolderFromSystemFileManager(data.getData());
                    }
                    break;
            }
        }
    }

    private void selectFileFromSystemFileManager(@Nullable Uri uri) {
        settings.setGenerateFromShareIntentMode(false);
        validateSelectedFile(uri);
    }

    private void selectFolderFromSystemFileManager(@Nullable Uri uri) {
        settings.setGenerateFromShareIntentMode(false);
        validateSelectedFolder(uri);
    }

    private void writeHashToFile(@Nullable Uri uri) throws IOException {
        if (uri != null) {
            ParcelFileDescriptor fileDescriptor = getActivity().getApplicationContext()
                    .getContentResolver()
                    .openFileDescriptor(uri, "w");
            if (fileDescriptor != null) {
                FileOutputStream outputStream = new FileOutputStream(fileDescriptor.getFileDescriptor());
                outputStream.write(etGeneratedHash.getText().toString().getBytes());
                outputStream.close();
                fileDescriptor.close();
            }
        }
    }

    private boolean compareText(@NonNull String firstValue, @NonNull String secondValue) {
        return firstValue.equalsIgnoreCase(secondValue);
    }

    private boolean fieldIsNotEmpty(@NonNull EditText fieldToCheck) {
        return !fieldToCheck.getText().toString().equals("");
    }

    private void convertToUpperCase(@NonNull EditText editText) {
        editText.setText(editText.getText().toString().toUpperCase());
    }

    private void convertToLowerCase(@NonNull EditText editText) {
        editText.setText(editText.getText().toString().toLowerCase());
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
