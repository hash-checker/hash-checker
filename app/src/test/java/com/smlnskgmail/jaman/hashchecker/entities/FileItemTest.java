package com.smlnskgmail.jaman.hashchecker.entities;

import com.smlnskgmail.jaman.hashchecker.components.filemanager.entities.FileItem;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.entities.FileType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileItemTest {

    @Test
    public void validateFileItemTest() {
        FileType fileType = FileType.FILE;
        String filePath = "./";
        String filename = "img_01.png";

        FileItem fileItem = new FileItem(fileType, filePath, filename);

        assertEquals(fileItem.getFileType(), fileType);
        assertEquals(fileItem.getFilePath(), filePath);
        assertEquals(fileItem.getFileName(), filename);
    }

}
