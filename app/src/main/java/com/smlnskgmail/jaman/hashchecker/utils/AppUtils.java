package com.smlnskgmail.jaman.hashchecker.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.manager.support.Requests;
import com.smlnskgmail.jaman.hashchecker.logic.history.db.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.logic.history.db.utils.DatabaseExporter;
import com.smlnskgmail.jaman.hashchecker.logs.L;

public class AppUtils {

    public static void saveUserData(@NonNull Fragment fragment, @NonNull View view) {
        if (HelperFactory.getHelper().isHistoryItemsListIsEmpty()) {
            Context context = view.getContext();
            try {
                Intent saveFileIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                saveFileIntent.addCategory(Intent.CATEGORY_OPENABLE);
                saveFileIntent.setType("application/zip");
                saveFileIntent.putExtra(Intent.EXTRA_TITLE, DatabaseExporter.EXPORT_FILE);
                fragment.startActivityForResult(saveFileIntent, Requests.FILE_CREATE);
            } catch (ActivityNotFoundException e) {
                String errorMessage = fragment.getString(R.string.message_error_start_file_selector);
                UIUtils.showSnackbar(context, view, errorMessage);
                L.e(e);
            }
        } else {
            Context context = view.getContext();
            UIUtils.showSnackbar(context, view, context.getString(R.string.history_empty_view_message));
        }
    }

    public static void openWebLink(@NonNull Context context, @NonNull String link) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
        } catch (ActivityNotFoundException e) {
            L.e(e);
        }
    }

}
