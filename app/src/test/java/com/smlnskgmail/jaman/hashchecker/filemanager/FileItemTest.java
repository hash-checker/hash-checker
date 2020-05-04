package com.smlnskgmail.jaman.hashchecker.filemanager;

import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileItem;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileItemTest {

    @Test
    public void validateFields() {
        FileType fileType = FileType.FILE;
        String filePath = "./";
        String filename = "img_01.png";

        FileItem fileItem = new FileItem(
                fileType,
                filePath,
                filename
        );

        assertEquals(
                fileType,
                fileItem.getFileType()
        );
        assertEquals(
                filePath,
                fileItem.getFilePath()
        );
    }

    @Test
    public void validateEquals() {
        FileType fileType = FileType.FILE;
        String filePath = "./";
        String filename = "img_01.png";

        FileItem fileItem = new FileItem(
                fileType,
                filePath,
                filename
        );

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

}
