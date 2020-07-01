package com.smlnskgmail.jaman.hashchecker.logic.filemanager.ui;

import android.content.Intent;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseActivity;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileItem;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileType;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.ui.list.FileItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.logic.logs.L;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FileManagerActivity extends BaseActivity implements FileSelectTarget {

    public static final String FILE_SELECT_DATA = "file_select_data";
    public static final String LAST_PATH = "last_path";

    public static final int FILE_SELECT = 1;
    public static final int FILE_SELECT_FROM_FILE_MANAGER = 2;

    public static final int PERMISSION_STORAGE = 3;

    private static final String BACK_FOLDER = "../";

    private FileItemsAdapter fileItemsAdapter;

    private final List<FileItem> files = new ArrayList<>();
    private final List<FileItem> storages = new ArrayList<>();

    private String currentPath = null;

    @Override
    public void create() {
        setContentView(R.layout.activity_file_explorer);
        RecyclerView rvFilesList = findViewById(R.id.rv_file_explorer_list);

        fileItemsAdapter = new FileItemsAdapter(
                files,
                FileManagerActivity.this
        );
        rvFilesList.setAdapter(fileItemsAdapter);

        storages.addAll(getExternalMounts());

        if (getIntent().hasExtra(LAST_PATH)) {
            String lastPath = getIntent().getStringExtra(LAST_PATH);
            //noinspection ConstantConditions
            if (new File(lastPath).exists()) {
                loadDirectoryWithPath(currentPath = lastPath);
            }
        }
        if (currentPath == null) {
            resetTitle();
            toStorageChooser();
        }
    }

    private List<FileItem> getExternalMounts() {
        List<FileItem> storages = new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        String name = Environment.getExternalStorageDirectory().getName();
        storages.add(
                new FileItem(
                        FileType.STORAGE,
                        path,
                        name
                )
        );
        try {
            String reg = ".*vold.*(vfat|ntfs|exfat|fat32|ext3|ext4).*rw.*";
            StringBuilder sb = new StringBuilder();
            try {
                Process process = new ProcessBuilder()
                        .command("mount")
                        .redirectErrorStream(true)
                        .start();
                process.waitFor();
                InputStream is = process.getInputStream();

                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                while (is.read(buffer) != -1) {
                    sb.append(new String(buffer));
                }
                is.close();
            } catch (Exception e) {
                L.e(e);
                return storages;
            }

            String[] lines = sb.toString().split("\n");
            for (String line: lines) {
                if (!line.toLowerCase(Locale.ENGLISH).contains("asec")) {
                    if (line.matches(reg)) {
                        String[] parts = line.split(" ");
                        for (String part: parts) {
                            if (part.startsWith("/")) {
                                if (!part.toLowerCase(Locale.ENGLISH).contains("vold")) {
                                    String storageName;
                                    int counter = 0;
                                    StringBuilder tempBuilder = new StringBuilder();
                                    for (int i = part.length() - 1; i >= 0; i--) {
                                        if (counter == 0) {
                                            if (part.charAt(i) == '/') {
                                                counter = 1;
                                            } else {
                                                tempBuilder.append(part.charAt(i));
                                            }
                                        }
                                    }
                                    storageName = tempBuilder.toString();
                                    tempBuilder.delete(0, tempBuilder.length());
                                    for (int i = storageName.length() - 1; i >= 0; i--) {
                                        tempBuilder.append(storageName.charAt(i));
                                    }
                                    storageName = tempBuilder.toString();
                                    boolean equalState = false;
                                    for (FileItem storage: storages) {
                                        if (("/storage/" + storageName).equals(storage.getFileName())) {
                                            equalState = true;
                                            break;
                                        }
                                    }
                                    if (!equalState) {
                                        String storagePath = "/storage/" + storageName;
                                        if (new File(storagePath).isDirectory()) {
                                            storages.add(
                                                    new FileItem(
                                                            FileType.STORAGE,
                                                            storagePath,
                                                            storageName
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            L.e(e);
            return storages;
        }
        return storages;
    }

    private void loadDirectoryWithPath(
            @NonNull final String directoryPath
    ) {
        files.clear();
        currentPath = directoryPath;

        File f = new File(currentPath);
        File[] files = f.listFiles();

        this.files.add(
                new FileItem(
                        FileType.BACK_FOLDER,
                        BACK_FOLDER,
                        BACK_FOLDER
                )
        );
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
                this.files.add(
                        new FileItem(
                                FileType.FOLDER,
                                path,
                                name
                        )
                );
            } else {
                final String fileName = file.getPath();
                final String fileNameInLowerCase = fileName.toLowerCase();
                if (FileItem.Extensions.isVideo(fileNameInLowerCase)) {
                    this.files.add(
                            new FileItem(
                                    FileType.VIDEO,
                                    path,
                                    name
                            )
                    );
                } else if (FileItem.Extensions.isImage(fileNameInLowerCase)) {
                    this.files.add(
                            new FileItem(
                                    FileType.IMAGE,
                                    path,
                                    name
                            )
                    );
                } else if (FileItem.Extensions.isSound(fileName)) {
                    this.files.add(
                            new FileItem(
                                    FileType.MUSIC,
                                    path,
                                    name
                            )
                    );
                } else {
                    this.files.add(
                            new FileItem(
                                    FileType.FILE,
                                    path,
                                    name
                            )
                    );
                }
            }
        }
        fileItemsAdapter.notifyDataSetChanged();
        getSupportActionBar().setTitle(directoryPath);
    }

    @Override
    public void fileSelect(
            @NonNull FileItem fileItem,
            int position
    ) {
        String path = fileItem.getFilePath();
        File file = new File(path);
        if (position == 0
                && path.equals(BACK_FOLDER)
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
                    //noinspection ConstantConditions
                    selectionFinished(
                            file.getParent(),
                            fileItem.getFilePath()
                    );
                }
            }
        }
    }

    private void selectionFinished(
            @NonNull String parent,
            @NonNull String path
    ) {
        Intent selectFileIntent = new Intent();
        selectFileIntent.putExtra(FILE_SELECT_DATA, path);
        selectFileIntent.putExtra(LAST_PATH, parent);
        setResult(
                FILE_SELECT_FROM_FILE_MANAGER,
                selectFileIntent
        );
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
        getMenuInflater().inflate(
                R.menu.menu_file_explorer,
                menu
        );
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
            loadDirectoryWithPath(
                    new File(currentPath).getParent()
            );
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
        UIUtils.setActionBarTitle(
                getSupportActionBar(),
                R.string.file_manager_select_storage_title
        );
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
