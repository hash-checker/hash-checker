package com.smlnskgmail.jaman.hashchecker.filemanager;

import com.smlnskgmail.jaman.hashchecker.entities.BaseEntityTest;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileItem;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileItemTest extends BaseEntityTest {

    private FileType fileType = FileType.FILE;
    private String filePath = "./";
    private String filename = "img_01.png";

    private FileItem fileItem = new FileItem(
            fileType,
            filePath,
            filename
    );

    @Override
    public void validateFields() {
        assertEquals(
                fileType,
                fileItem.getFileType()
        );
        assertEquals(
                filePath,
                fileItem.getFilePath()
        );
    }

    @Override
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

    @Override
    public void validateHashCode() {
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
