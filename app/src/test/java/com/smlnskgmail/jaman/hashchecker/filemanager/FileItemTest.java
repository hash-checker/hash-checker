package com.smlnskgmail.jaman.hashchecker.filemanager;

import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileItem;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileItemTest {

    @Test
    public void validateFileItemTest() {
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


        assertEquals(
                fileItem,
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

}
