package com.smlnskgmail.jaman.hashchecker.components.filemanager.selector;

import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseActivity;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.data.FileItem;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.data.FileType;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.selector.pathadapter.FileDialogAdapter;
import com.smlnskgmail.jaman.hashchecker.support.preferences.Constants;
import com.smlnskgmail.jaman.hashchecker.support.utils.FileUtils;
import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileSelectorActivity extends BaseActivity implements OnFileClickListener {

    @BindView(R.id.files_list)
    protected RecyclerView filesList;

    private FileDialogAdapter fileDialogAdapter;

    private List<FileItem> files = new ArrayList<>();
    private List<FileItem> storages = new ArrayList<>();

    private String currentPath = null;
    private String startPath;

    @Override
    public void initialize() {
        setContentView(R.layout.activity_file_selector);
        ButterKnife.bind(this);
        UIUtils.setActionBarTitle(getSupportActionBar(),
                R.string.file_manager_select_storage_title);

        fileDialogAdapter = new FileDialogAdapter(files, FileSelectorActivity.this);
        filesList.setAdapter(fileDialogAdapter);

        storages = FileUtils.getExternalMounts();
        if (startPath != null && !startPath.equals(Environment
                .getExternalStorageDirectory().getPath())) {
            currentPath = startPath;
            getDirectory(startPath);
        } else {
            startPath = null;
            toStorageChooser();
        }
    }

    private boolean isStorageSelector() {
        return currentPath.equals(startPath);
    }

    private void getDirectory(@NonNull final String directoryPath) {
        files.clear();
        currentPath = directoryPath;

        File f = new File(currentPath);
        File[] files = f.listFiles();

        if (!isStorageSelector()) {
            this.files.add(new FileItem(FileType.BACK_FOLDER,
                    Constants.FileNames.BACK_FOLDER, Constants.FileNames.BACK_FOLDER));
        }
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
                final String fileNameLwr = fileName.toLowerCase();
                if (FileUtils.isVideo(fileNameLwr)) {
                    this.files.add(new FileItem(FileType.VIDEO, path, name));
                } else if (FileUtils.isImage(fileNameLwr)) {
                    this.files.add(new FileItem(FileType.IMAGE, path, name));
                } else if (FileUtils.isSound(fileName)) {
                    this.files.add(new FileItem(FileType.MUSIC, path, name));
                } else {
                    this.files.add(new FileItem(FileType.FILE, path, name));
                }
            }
        }
        fileDialogAdapter.notifyDataSetChanged();
        UIUtils.setActionBarTitle(getSupportActionBar(), directoryPath);
    }

    @Override
    public void onFileClick(@NonNull FileItem fileItem, int position) {
        String path = fileItem.getFilePath();
        File file = new File(path);
        if (position == 0 && path.equals(Constants.FileNames.BACK_FOLDER)
                && fileItem.getFileType() != FileType.STORAGE) {
            if (!isStorage(currentPath)) {
                String parent = new File(currentPath).getParent();
                if (parent != null) {
                    getDirectory(parent);
                }
            } else {
                validatePath();
            }
        } else {
            if (file.isDirectory()) {
                if (file.canRead()) {
                    getDirectory(path);
                } else {

                }

                getDirectory(path);
            } else {
                selectionFinished(fileItem.getFilePath());
            }
        }
    }

    private void selectionFinished(@NonNull String path) {
        Intent selectFileIntent = new Intent();
        selectFileIntent.putExtra(Constants.RequestData.FILE_SELECT_DATA, path);
        setResult(Constants.Requests.FILE_SELECT_REQUEST_FROM_APP_FILE_MANAGER, selectFileIntent);
        finish();
    }

    private void toStorageChooser() {
        files.addAll(storages);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_file_manager, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.close_file_manager:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!isStorageSelector()) {
            if (!isStorage(currentPath)) {
                getDirectory(new File(currentPath).getParent());
                return;
            } else if (isStorage(currentPath)) {
                validatePath();
                return;
            }
        }
        super.onBackPressed();
    }

    private void validatePath() {
        files.clear();
        toStorageChooser();
        fileDialogAdapter.notifyDataSetChanged();
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
