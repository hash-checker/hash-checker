package com.smlnskgmail.jaman.hashchecker.components.filemanager.selector;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseActivity;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.data.FileItem;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.data.FileType;
import com.smlnskgmail.jaman.hashchecker.support.utils.FileUtils;

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

    private File selectedFile;

    private List<FileItem> files = new ArrayList<>();
    private List<FileItem> storages = new ArrayList<>();

    private String currentPath = null;
    private String startPath;

    @Override
    public void initialize() {
        setContentView(R.layout.activity_file_selector);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(R.string.file_manager_select_storage_title);

        fileDialogAdapter = new FileDialogAdapter(files, FileSelectorActivity.this);
        filesList.setAdapter(fileDialogAdapter);

        storages = FileUtils.getExternalMounts();
        if (startPath != null && !startPath.equals(Environment.getExternalStorageDirectory().getPath())) {
            currentPath = startPath;
            getDir(startPath);
        } else {
            startPath = null;
            toStorageChooser();
//            selectButton.setVisibility(View.GONE);
        }
    }

    private void selectionFinished() {

    }

    private boolean isStorage(String path) {
        for (FileItem storage: storages) {
            if (storage.getFilePath().equals(path)) {
                return true;
            }
        }
        return false;
    }

    private boolean isStartPath() {
        return currentPath == null || currentPath.equals(startPath);
    }

    private void getDirImpl(@NonNull final String directoryPath) {
        files.clear();
        currentPath = directoryPath;

        File f = new File(currentPath);
        File[] files = f.listFiles();

//        myPath.setText(getText(R.string.location) + ": " + currentPath);
        if (!isStartPath()) {
            this.files.add(new FileItem(FileType.BACK_FOLDER, "../", "../"));
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
        getSupportActionBar().setTitle(directoryPath);
    }

    private void getDir(String dirPath) {
        getDirImpl(dirPath);
    }

    private void validatePath() {
        files.clear();
        toStorageChooser();
        fileDialogAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFileClick(@NonNull FileItem fileItem, int position) {
        String path = fileItem.getFilePath();
        File file = new File(path);
        if (position == 0 && path.equals("../") && fileItem.getFileType() != FileType.STORAGE) {
            if (!isStorage(currentPath)) {
                String parent = new File(currentPath).getParent();
                if (parent != null) {
                    getDir(parent);
                }
            } else {
                validatePath();
            }
        } else {
            if (file.isDirectory()) {
                if (file.canRead()) {
                    getDir(path);
                } else {

                }
            }
        }
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
        if (!isStartPath()) {
            if (!isStorage(currentPath)) {
                getDir(new File(currentPath).getParent());
                return;
            } else if (isStorage(currentPath)) {
                validatePath();
                return;
            }
        }
        super.onBackPressed();
    }

}
