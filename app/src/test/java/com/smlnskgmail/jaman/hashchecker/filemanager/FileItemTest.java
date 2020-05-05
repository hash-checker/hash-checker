package com.smlnskgmail.jaman.hashchecker.filemanager;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileItem;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileType;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FileItemTest {

    private FileType fileType = FileType.FILE;
    private String filePath = "./";
    private String filename = "img_01.png";

    private FileItem fileItem = new FileItem(
            fileType,
            filePath,
            filename
    );

    @Test
    public void validateFields() {
        assertEquals(
                fileType,
                fileItem.getFileType()
        );
        assertEquals(
                filePath,
                fileItem.getFilePath()
        );
        assertEquals(
                filename,
                fileItem.getFileName()
        );
    }

    @Test
    public void validateEquals() {
        assertEquals(
                fileItem,
                fileItem
        );
        assertEquals(
                new FileItem(
                        fileType,
                        filePath,
                        filename
                ),
                fileItem
        );

        assertNotEquals(
                new FileItem(
                        FileType.IMAGE,
                        filePath,
                        filename
                ),
                fileItem
        );
        assertNotEquals(
                new FileItem(
                        fileType,
                        "./temp/",
                        filename
                ),
                fileItem
        );
        assertNotEquals(
                new FileItem(
                        fileType,
                        filePath,
                        "temp"
                ),
                fileItem
        );
        assertNotEquals(
                fileItem,
                null
        );
        assertNotEquals(
                filename,
                "String"
        );
    }

    @Test
    public void validateHashCode() {
        FileType fileType = FileType.FILE;
        String filePath = "./";
        String filename = "img_01.png";

        FileItem fileItem = new FileItem(
                fileType,
                filePath,
                filename
        );

        assertEquals(
                fileItem.hashCode(),
                fileItem.hashCode()
        );
        assertEquals(
                new FileItem(
                        fileType,
                        filePath,
                        filename
                ).hashCode(),
                fileItem.hashCode()
        );

        assertNotEquals(
                new FileItem(
                        FileType.IMAGE,
                        filePath,
                        filename
                ).hashCode(),
                filename.hashCode()
        );
    }

    @Test
    public void validateIcons() {
        assertEquals(
                R.drawable.ic_folder,
                FileType.FOLDER.getIconResId()
        );
        assertEquals(
                R.drawable.ic_folder,
                FileType.BACK_FOLDER.getIconResId()
        );
        assertEquals(
                R.drawable.ic_file,
                FileType.FILE.getIconResId()
        );
        assertEquals(
                R.drawable.ic_video_label,
                FileType.VIDEO.getIconResId()
        );
        assertEquals(
                R.drawable.ic_image,
                FileType.IMAGE.getIconResId()
        );
        assertEquals(
                R.drawable.ic_music_note,
                FileType.MUSIC.getIconResId()
        );
        assertEquals(
                R.drawable.ic_settings_file_manager,
                FileType.STORAGE.getIconResId()
        );
    }

    @Test
    public void validateVideoFileExtensions() {
        assertThat(
                Arrays.asList(
                        ".3gp",
                        ".mp4",
                        ".mkv",
                        ".webm"
                ),
                is(FileItem.Extensions.VIDEO_EXTENSIONS)
        );
    }

    @Test
    public void validateImageFileExtensions() {
        assertThat(
                Arrays.asList(
                        ".bmp",
                        ".gif",
                        ".jpg",
                        ".png",
                        ".webp",
                        ".heic",
                        ".heif"
                ),
                is(FileItem.Extensions.IMAGE_EXTENSIONS)
        );
    }

    @Test
    public void validateSoundFileExtensions() {
        assertThat(
                Arrays.asList(
                        ".m4a",
                        ".aac",
                        ".tc",
                        ".flac",
                        ".gsm",
                        ".mid",
                        ".xmf",
                        ".mxmf",
                        ".rtttl",
                        ".rtx",
                        ".ota",
                        ".imy",
                        ".mp3",
                        ".wav",
                        ".ogg"
                ),
                is(FileItem.Extensions.SOUND_EXTENSIONS)
        );
    }

    @Test
    public void validateVideoFileCheck() {
        assertTrue(
                FileItem.Extensions.isVideo(
                        "video.mp4"
                )
        );
        assertFalse(
                FileItem.Extensions.isVideo(
                        "video.wav"
                )
        );
    }

    @Test
    public void validateImageFileCheck() {
        assertTrue(
                FileItem.Extensions.isImage(
                        "image.png"
                )
        );
        assertFalse(
                FileItem.Extensions.isImage(
                        "image.wav"
                )
        );
    }

    @Test
    public void validateSoundFileCheck() {
        assertTrue(
                FileItem.Extensions.isSound(
                        "sound.mp3"
                )
        );
        assertFalse(
                FileItem.Extensions.isSound(
                        "sound.avi"
                )
        );
    }

    @Test
    public void validateFileExtensionCheck() {
        String extension = ".avi";
        assertEquals(
                extension,
                FileItem.Extensions.getFileExtension(
                        "video" + extension
                )
        );

        assertEquals(
                "",
                FileItem.Extensions.getFileExtension(
                        "video"
                )
        );
    }

}
