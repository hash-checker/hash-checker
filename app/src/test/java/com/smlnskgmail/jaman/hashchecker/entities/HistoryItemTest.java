package com.smlnskgmail.jaman.hashchecker.entities;

import com.smlnskgmail.jaman.hashchecker.generator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.navigation.fragments.history.entities.HistoryItem;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class HistoryItemTest {

    @Test
    public void validateHistoryItemTest() {
        Date generationDate = new Date();
        HashType hashType = HashType.MD5;
        boolean isFile = true;
        String objectValue = "./Downloads/task_manager.apk";
        String hashValue = "9gkfnb7nvklckofdamvkdlsop16789dm";

        HistoryItem historyItem = new HistoryItem(generationDate, hashType, isFile,
                objectValue, hashValue);

        Assert.assertEquals(historyItem.getGenerationDate(), generationDate);
        Assert.assertEquals(historyItem.getHashType(), hashType);
        Assert.assertEquals(historyItem.isFile(), isFile);
        Assert.assertEquals(historyItem.getObjectValue(), objectValue);
        Assert.assertEquals(historyItem.getHashValue(), hashValue);
    }

}
