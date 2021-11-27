package com.smlnskgmail.jaman.hashchecker.features.hashcalculator.presenter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashCalculatorTask;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.models.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view.HashCalculatorView;

import java.util.Calendar;
import java.util.Date;

public class HashCalculatorPresenterImpl implements HashCalculatorPresenter {

    private HashCalculatorView view;

    private LocalDataStorage localDataStorage;
    private Settings settings;

    private HashType hashType;
    private String objectValue;

    private Uri fileUri;

    private boolean startWithTextSelection;
    private boolean startWithFileSelection;

    private boolean isTextIsSelected;

    private final HashCalculatorTask.HashCalculatorTaskTarget hashCalculatorTaskTarget = hashValue -> {
        if (hashValue == null) {
            view.setGeneratedHash(Pair.create(HashCalculatorView.HashGenerationResult.error, null));
        } else {
            view.setGeneratedHash(Pair.create(HashCalculatorView.HashGenerationResult.done, hashValue));
            if (settings.canSaveResultToHistory()) {
                Date date = Calendar.getInstance().getTime();
                HistoryItem historyItem = new HistoryItem(
                        date,
                        hashType,
                        !isTextIsSelected,
                        objectValue,
                        hashValue
                );
                localDataStorage.addHistoryItem(historyItem);
            }
            if (settings.canShowRateAppDialog()) {
                settings.increaseHashGenerationCount();
                view.showRateDialog();
            } else {
                settings.increaseHashGenerationCount();
            }
        }
        view.hideProgress();
    };

    @Override
    public void init(
            @NonNull HashCalculatorView view,
            @NonNull LocalDataStorage localDataStorage,
            @NonNull Settings settings,
            @Nullable Bundle shortcutArguments
    ) {
        if (shortcutArguments != null) {
            checkShortcutActionPresence(shortcutArguments);
        }
//        if (startWithTextSelection) {
//            userActionSelect(UserActionType.ENTER_TEXT);
//            startWithTextSelection = false;
//        } else if (startWithFileSelection) {
//            userActionSelect(UserActionType.SEARCH_FILE);
//            startWithFileSelection = false;
//        }
    }

    private void checkExternalDataPresence(@NonNull Bundle dataArguments) {
        String uri = dataArguments.getString(MainActivity.URI_FROM_EXTERNAL_APP);
        if (uri != null) {
            Uri file = Uri.parse(uri);
            if (file != null) {
                fileUri = file;
                isTextIsSelected = false;
//                setResult(fileNameFromUri(file), false);
            }
            dataArguments.remove(MainActivity.URI_FROM_EXTERNAL_APP);
        }
    }

//    @Override
//    public void compareHashes() {
//        if (fieldIsNotEmpty(etCustomHash) && fieldIsNotEmpty(etGeneratedHash)) {
//            boolean equal = compareText(
//                    etCustomHash.getText().toString(),
//                    etGeneratedHash.getText().toString()
//            );
//            showSnackbarWithoutAction(
//                    equal ? R.string.message_match_result
//                            : R.string.message_do_not_match_result
//            );
//        } else {
//            showSnackbarWithoutAction(R.string.message_fill_fields);
//        }
//    }

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

    @Override
    public void generateHash(@NonNull Context context) {
        if (fileUri != null || isTextIsSelected) {
            view.showProgress();
            if (isTextIsSelected) {
                new HashCalculatorTask(
                        context,
                        hashType,
                        objectValue,
                        hashCalculatorTaskTarget
                ).execute();
            } else {
                new HashCalculatorTask(
                        context,
                        hashType,
                        fileUri,
                        hashCalculatorTaskTarget
                ).execute();
            }
        } else {
            view.showNoObjectSelected();
        }
    }

    @Override
    public void setHashType(@NonNull HashType hashType) {
        this.hashType = hashType;
        settings.saveHashTypeAsLast(hashType);
    }

    @Override
    public void setObjectValue(@NonNull String objectValue, boolean isText) {
        this.objectValue = objectValue;
        isTextIsSelected = isText;
    }

    @Override
    public boolean isTextIsSelected() {
        return isTextIsSelected;
    }

    @Override
    public void exportAsFile() {
        view.saveTextFile(fileUri, isTextIsSelected);
    }

}
