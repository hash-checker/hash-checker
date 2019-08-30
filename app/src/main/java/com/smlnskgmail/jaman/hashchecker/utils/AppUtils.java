package com.smlnskgmail.jaman.hashchecker.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.manager.FileManagerActivity;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.manager.support.Requests;
import com.smlnskgmail.jaman.hashchecker.db.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.db.utils.DatabaseExporter;
import com.smlnskgmail.jaman.hashchecker.support.logs.L;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AppUtils {

    public static void openSystemFileManager(@NonNull Fragment fragment, @NonNull View view) {
        try {
            Intent openExplorerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            openExplorerIntent.addCategory(Intent.CATEGORY_OPENABLE);
            openExplorerIntent.setType("*/*");
            fragment.startActivityForResult(openExplorerIntent, Requests.FILE_SELECT);
        } catch (ActivityNotFoundException e) {
            UIUtils.showSnackbar(view.getContext(), view,
                    fragment.getString(R.string.message_error_start_file_selector));
            L.e(e);
        }
    }

    public static void saveTextFile(@NonNull Fragment fragment, @NonNull String filename, @NonNull View view) {
        try {
            Intent saveTextFileIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            saveTextFileIntent.addCategory(Intent.CATEGORY_OPENABLE);
            saveTextFileIntent.setType("text/plain");
            saveTextFileIntent.putExtra(Intent.EXTRA_TITLE, filename + ".txt");
            fragment.startActivityForResult(saveTextFileIntent, Requests.FILE_CREATE);
        } catch (ActivityNotFoundException e) {
            String errorMessage = fragment.getString(R.string.message_error_start_file_selector);
            UIUtils.showSnackbar(view.getContext(), view, errorMessage);
            L.e(e);
        }
    }

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

    public static void copyUserDataToUserFolder(@NonNull Context context, @Nullable Uri uri) {
        if (uri != null) {
            try {
                DatabaseExporter.exportDatabase(context);
                ParcelFileDescriptor descriptor = context.getApplicationContext().getContentResolver()
                        .openFileDescriptor(uri, "w");
                if (descriptor != null) {
                    FileOutputStream outputStream = new FileOutputStream(descriptor.getFileDescriptor());
                    AppUtils.copyFile(new File(DatabaseExporter.getUserDataZip(context)), outputStream);
                }
            } catch (IOException e) {
                L.e(e);
            }
        }
    }

    private static void copyFile(@NonNull File source, @NonNull FileOutputStream outputStream)
            throws IOException {
        try (InputStream inputStream = new FileInputStream(source)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
        }
    }

    public static void openInnerFileManager(@NonNull Fragment fragment) {
        Intent openExplorerIntent = new Intent(fragment.getContext(), FileManagerActivity.class);
        fragment.startActivityForResult(openExplorerIntent, Requests.FILE_SELECT_FROM_FILE_MANAGER);
    }

    public static void openAppSettings(@NonNull Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
            intent.setData(uri);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
            L.e(e);
        }
    }

    public static void openWebLink(@NonNull Context context, @NonNull String link) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
        } catch (ActivityNotFoundException e) {
            L.e(e);
        }
    }

    public static void openGooglePlay(@NonNull Context context, @NonNull View view) {
        final String appPackageName = context.getPackageName();
        Uri link;
        try {
            link = Uri.parse("market://details?id=" + appPackageName);
            context.startActivity(new Intent(Intent.ACTION_VIEW, link));
        } catch (ActivityNotFoundException e) {
            try {
                link = Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName);
                context.startActivity(new Intent(Intent.ACTION_VIEW, link));
            } catch (ActivityNotFoundException e2) {
                UIUtils.showSnackbar(context, view, context.getString(R.string.message_error_start_google_play));
                L.e(e2);
            }
        }
    }

    public static void sendFeedback(@NonNull Context context, @NonNull String text, @NonNull String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.common_app_name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);
        String message = String.format("%s:", context.getString(R.string.message_email_app_chooser));
        context.startActivity(Intent.createChooser(emailIntent, message));
    }

    static void vibrate(@NonNull Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        int vibrationLength = 30;

        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(vibrationLength,
                        VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(vibrationLength);
            }
        }
    }

    public static void copyTextToClipboard(@NonNull Context context, @NonNull String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(context.getString(R.string.common_app_name), text);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
        }
    }

    public static void closeApp(@NonNull Activity activity) {
        activity.finish();
    }

    public static void restartApp(@NonNull Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        closeApp(activity);
    }

    public static boolean isNotQAndAbove() {
        /*if (BuildConfig.DEBUG) {
            return false;
        }*/
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.Q;
    }

}
