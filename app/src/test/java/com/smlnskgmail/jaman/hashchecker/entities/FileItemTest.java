package com.smlnskgmail.jaman.hashchecker.entities;

import com.smlnskgmail.jaman.hashchecker.components.filemanager.entities.FileItem;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.entities.FileType;

import org.junit.Assert;
import org.junit.Test;

public class FileItemTest {

    @Test
    public void validateFileItemTest() {
        FileType fileType = FileType.FILE;
        String filePath = "./";
        String filename = "img_01.png";

        FileItem fileItem = new FileItem(fileType, filePath, filename);

        Assert.assertEquals(fileItem.getFileType(), fileType);
        Assert.assertEquals(fileItem.getFilePath(), filePath);
        Assert.assertEquals(fileItem.getFileName(), filename);
    }

}
