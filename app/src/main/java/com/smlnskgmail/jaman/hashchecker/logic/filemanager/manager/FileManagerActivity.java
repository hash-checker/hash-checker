package com.smlnskgmail.jaman.hashchecker.logic.filemanager.manager;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseActivity;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.entities.FileItem;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.entities.FileType;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.manager.listadapter.FileItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.manager.support.FileExtensions;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.manager.support.FileRequests;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.manager.support.FileSelectTarget;
import com.smlnskgmail.jaman.hashchecker.tools.UITools;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManagerActivity extends BaseActivity implements FileSelectTarget {

    private static final String BACK_FOLDER = "../";

    private FileItemsAdapter fileItemsAdapter;

    private final List<FileItem> files = new ArrayList<>();
    private final List<FileItem> storages = new ArrayList<>();

    private String currentPath = null;

    @Override
    public void create() {
        setContentView(R.layout.activity_file_explorer);
        RecyclerView rvFilesList = findViewById(R.id.rv_file_explorer_list);
        resetTitle();

        fileItemsAdapter = new FileItemsAdapter(files, FileManagerActivity.this);
        rvFilesList.setAdapter(fileItemsAdapter);

        storages.addAll(FileExtensions.getExternalMounts());
        toStorageChooser();
    }

    private void loadDirectoryWithPath(@NonNull final String directoryPath) {
        files.clear();
        currentPath = directoryPath;

        File f = new File(currentPath);
        File[] files = f.listFiles();

        this.files.add(new FileItem(FileType.BACK_FOLDER, BACK_FOLDER, BACK_FOLDER));
        if (files == null) {
            return;
        }

        Arrays.sort(files, (file, file2) -> {
            if (file.isDirectory() && !file2.isDirectory()) {
                return -1;
            } else if (!file.isDirectory() && file2.isDirectory()) {
                return 1;
            } else {
                return file.getName().compareTo(file2.getName());
            }
        });
        for (File file: files) {
            String path = file.getPath();
            String name = file.getName();
            if (file.isDirectory()) {
                this.files.add(new FileItem(FileType.FOLDER, path, name));
            } else {
                final String fileName = file.getPath();
                final String fileNameInLowerCase = fileName.toLowerCase();
                if (FileExtensions.isVideo(fileNameInLowerCase)) {
                    this.files.add(new FileItem(FileType.VIDEO, path, name));
                } else if (FileExtensions.isImage(fileNameInLowerCase)) {
                    this.files.add(new FileItem(FileType.IMAGE, path, name));
                } else if (FileExtensions.isSound(fileName)) {
                    this.files.add(new FileItem(FileType.MUSIC, path, name));
                } else {
                    this.files.add(new FileItem(FileType.FILE, path, name));
                }
            }
        }
        fileItemsAdapter.notifyDataSetChanged();
        getSupportActionBar().setTitle(directoryPath);
    }

    @Override
    public void fileSelect(@NonNull FileItem fileItem, int position) {
        String path = fileItem.getFilePath();
        File file = new File(path);
        if (position == 0 && path.equals(BACK_FOLDER)
                && fileItem.getFileType() != FileType.STORAGE) {
            if (!isStorage(currentPath)) {
                String parent = new File(currentPath).getParent();
                if (parent != null) {
                    loadDirectoryWithPath(parent);
                }
            } else {
                validatePath();
            }
        } else {
            if (file.canRead()) {
                if (file.isDirectory()) {
                    loadDirectoryWithPath(path);
                } else {
                    selectionFinished(fileItem.getFilePath());
                }
            }
        }
    }

    private void selectionFinished(@NonNull String path) {
        Intent selectFileIntent = new Intent();
        selectFileIntent.putExtra(FileRequests.FILE_SELECT_DATA, path);
        setResult(FileRequests.FILE_SELECT_FROM_FILE_MANAGER, selectFileIntent);
        finish();
    }

    private void toStorageChooser() {
        if (!files.isEmpty()) {
            files.clear();
            currentPath = null;
            resetTitle();
        }
        files.addAll(storages);
        fileItemsAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_file_explorer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_action_close_file_manager) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (currentPath == null) {
            super.onBackPressed();
        } else if (!isStorage(currentPath)) {
            loadDirectoryWithPath(new File(currentPath).getParent());
        } else if (isStorage(currentPath)) {
            validatePath();
        }
    }

    private void validatePath() {
        files.clear();
        resetTitle();
        toStorageChooser();
    }

    private void resetTitle() {
        UITools.setActionBarTitle(getSupportActionBar(), R.string.file_manager_select_storage_title);
    }

    private boolean isStorage(String path) {
        for (FileItem storage: storages) {
            if (storage.getFilePath().equals(path)) {
                return true;
            }
        }
        return false;
    }

}
