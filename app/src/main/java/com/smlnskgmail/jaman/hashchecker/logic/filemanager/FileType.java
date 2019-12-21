package com.smlnskgmail.jaman.hashchecker.logic.filemanager;

import com.smlnskgmail.jaman.hashchecker.R;

public enum FileType {

    FOLDER(R.drawable.ic_folder),
    BACK_FOLDER(R.drawable.ic_folder),
    FILE(R.drawable.ic_file),
    VIDEO(R.drawable.ic_video_label),
    IMAGE(R.drawable.ic_image),
    MUSIC(R.drawable.ic_music_note),
    STORAGE(R.drawable.ic_settings_file_manager);

    private final int iconResId;

    FileType(int iconResId) {
        this.iconResId = iconResId;
    }

    public int getIconResId() {
        return iconResId;
    }

}
