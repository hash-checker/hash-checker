package com.smlnskgmail.jaman.hashchecker.entities;

import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.support.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.entities.HistoryItem;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

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

        assertEquals(historyItem.getGenerationDate(), generationDate);
        assertEquals(historyItem.getHashType(), hashType);
        assertEquals(historyItem.isFile(), isFile);
        assertEquals(historyItem.getObjectValue(), objectValue);
        assertEquals(historyItem.getHashValue(), hashValue);
    }

}
