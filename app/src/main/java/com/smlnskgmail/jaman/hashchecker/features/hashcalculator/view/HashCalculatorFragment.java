package com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view;

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
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Pair;
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
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.clipboard.Clipboard;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.presenter.HashCalculatorPresenter;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view.input.TextInputDialog;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view.input.TextValueTarget;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view.lists.actions.types.UserActionTarget;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view.lists.actions.types.UserActionType;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view.lists.actions.ui.ActionSelectActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view.lists.actions.ui.SourceSelectActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view.lists.hashtypes.GenerateToBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view.lists.hashtypes.HashTypeSelectTarget;
import com.smlnskgmail.jaman.hashchecker.ui.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.ui.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.ui.dialogs.system.AppSnackbar;
import com.smlnskgmail.jaman.hashchecker.ui.watchers.AppTextWatcher;
import com.smlnskgmail.jaman.hashchecker.utils.LogUtils;
import com.smlnskgmail.jaman.hashchecker.utils.WebUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

public class HashCalculatorFragment extends BaseFragment
        implements HashCalculatorView, TextValueTarget, UserActionTarget, HashTypeSelectTarget {

    private static final int FILE_SELECT = 197;

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

    private HashCalculatorPresenter hashCalculatorPresenter;

    private View mainScreen;

    private EditText etCustomHash;
    private EditText etGeneratedHash;

    private TextView tvSelectedObjectName;
    private TextView tvSelectedHashType;

    private Button btnGenerateFrom;

    private ProgressDialog progressDialog;

    private Context context;
    private FragmentManager fragmentManager;

    private boolean isBackButtonDoubleTap = false;

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    @Override
    public void userActionSelect(@NonNull UserActionType userActionType) {
        switch (userActionType) {
            case ENTER_TEXT:
                enterText();
                break;
            case SEARCH_FILE:
                searchFile();
                break;
            case GENERATE_HASH:
                hashCalculatorPresenter.generateHash(getContext());
                break;
            case COMPARE_HASHES:
//                compareHashes();
                break;
            case EXPORT_AS_TXT:
                hashCalculatorPresenter.exportAsFile();
//                saveGeneratedHashAsTextFile();
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown UserActionType"
                );
        }
    }

    private void searchFile() {
        try {
            Intent openExplorerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            openExplorerIntent.addCategory(Intent.CATEGORY_OPENABLE);
            openExplorerIntent.setType("*/*");
            startActivityForResult(
                    openExplorerIntent,
                    FILE_SELECT
            );
        } catch (ActivityNotFoundException e) {
            LogUtils.e(e);
            showSnackbarWithoutAction(R.string.message_error_start_file_selector);
        }
    }

    private void showSnackbarWithoutAction(
            @StringRes int messageResId
    ) {
        new AppSnackbar(
                context,
                mainScreen,
                messageResId,
                settings,
                themeConfig
        ).show();
    }

    private void selectHashTypeFromList() {
        GenerateToBottomSheet generateToBottomSheet = new GenerateToBottomSheet();
        generateToBottomSheet.show(
                getFragmentManager(),
                generateToBottomSheet.key()
        );
    }

    @Override
    public void saveTextFile(@Nullable Uri uri, boolean isTextSelected) {
        if (uri != null && !etGeneratedHash.getText().toString().isEmpty()) {
            String filename = getString(
                    isTextSelected
                            ? R.string.filename_hash_from_text
                            : R.string.filename_hash_from_file
            );
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
                        Settings.FILE_CREATE
                );
            } catch (ActivityNotFoundException e) {
                LogUtils.e(e);
                showSnackbarWithoutAction(R.string.message_error_start_file_selector);
            }
        } else {
            showSnackbarWithoutAction(R.string.message_generate_hash_before_export);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
//        if (checkArguments(bundle)) {
//            checkShortcutActionPresence(bundle);
//        }
    }

    private void validateSelectedFile(@Nullable Uri uri) {

    }

    @NonNull
    private String fileNameFromUri(@NonNull Uri uri) {
        String scheme = uri.getScheme();
        if (scheme != null && scheme.equals("content")) {
            try (Cursor cursor = context.getContentResolver()
                    .query(uri, null, null, null, null)) {
                assert cursor != null;
                cursor.moveToPosition(0);
                return cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                OpenableColumns.DISPLAY_NAME
                        )
                );
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return new File(uri.getPath()).getName();
    }

    @Override
    public void textValueEntered(@NonNull String text) {
        tvSelectedObjectName.setText(text);
        hashCalculatorPresenter.setObjectValue(text, true);
        btnGenerateFrom.setText(getString(R.string.common_text));
    }

    private void enterText() {
        String currentText = !hashCalculatorPresenter.isTextIsSelected()
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
        if (!isBackButtonDoubleTap) {
            isBackButtonDoubleTap = true;
            showSnachbarWithAction(
                    getView().findViewById(R.id.fl_main_screen),
                    R.string.message_exit,
                    getString(R.string.action_exit_now),
                    v -> getActivity().finish()
            );
            new Handler().postDelayed(
                    () -> isBackButtonDoubleTap = false,
                    300
            );
        } else {
            getActivity().finish();
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


    @Override
    public void hashTypeSelect(@NonNull HashType hashType) {
        tvSelectedHashType.setText(hashType.getTypeAsString());
        hashCalculatorPresenter.setHashType(hashType);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
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
        btnGenerateFrom.setOnClickListener(v -> showSourceSelectDialog());

        view.findViewById(R.id.btn_hash_actions).setOnClickListener(v -> showActionSelectDialog());

        fragmentManager = getActivity().getSupportFragmentManager();
        tvSelectedHashType.setText(
                settings.getLastHashType().getTypeAsString()
        );
        tvSelectedObjectName.setMovementMethod(new ScrollingMovementMethod());
//        Bundle bundle = getArguments();
//        if (checkArguments(bundle)) {
//            checkExternalDataPresence(bundle);
//        }
    }

    @NonNull
    @Override
    protected LanguageConfig langHelper() {
        return languageConfig;
    }

    @NonNull
    @SuppressWarnings("MethodParametersAnnotationCheck")
    private TextWatcher watcherForInputField(
            @NonNull ImageView copyButton,
            @NonNull ImageView clearButton
    ) {
        return new AppTextWatcher() {
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
        };
    }

    private void copyHashFromEditText(
            @NonNull EditText editText
    ) {
        String hash = editText.getText().toString();
        if (!hash.isEmpty()) {
            new Clipboard(context, hash).copy();
            showHashCopySnackbar();
        }
    }

    private void showSourceSelectDialog() {
        SourceSelectActionsBottomSheet sourceSelectActionsBottomSheet = new SourceSelectActionsBottomSheet();
        sourceSelectActionsBottomSheet.show(
                fragmentManager,
                sourceSelectActionsBottomSheet.key()
        );
    }

    private void showActionSelectDialog() {
        ActionSelectActionsBottomSheet actionSelectActionsBottomSheet
                = new ActionSelectActionsBottomSheet();
        actionSelectActionsBottomSheet.show(
                fragmentManager,
                actionSelectActionsBottomSheet.key()
        );
    }

    private void showHashCopySnackbar() {
        showSnackbarWithoutAction(R.string.history_item_click_text);
    }

    @Override
    public void appResume() {
        super.appResume();
        checkMultilinePreference();
        hashTypeSelect(settings.getLastHashType());
    }

    private void checkMultilinePreference() {
        if (settings.isUsingMultilineHashFields()) {
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

    @Override
    public void onActivityResult(
            int requestCode,
            int resultCode,
            @Nullable Intent data
    ) {
        if (data != null) {
            if (requestCode == Settings.FILE_CREATE) {
                try {
                    writeHashToFile(data.getData());
                } catch (IOException e) {
                    LogUtils.e(e);
                }
            }
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
    public void setGeneratedHash(@NonNull Pair<HashGenerationResult, String> result) {

    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showRateDialog() {
        View view = getView();
        if (view != null) {
            new AppAlertDialog(
                    context,
                    R.string.settings_title_rate_app,
                    R.string.rate_app_message,
                    R.string.rate_app_action,
                    (dialog, which) -> WebUtils.openGooglePlay(
                            context,
                            view,
                            settings,
                            themeConfig
                    ),
                    themeConfig
            ).show();
        }
    }

    @Override
    public void showNoObjectSelected() {
        showSnackbarWithoutAction(R.string.message_select_object);
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
