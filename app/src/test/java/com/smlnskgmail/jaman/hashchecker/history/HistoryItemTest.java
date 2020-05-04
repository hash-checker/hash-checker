package com.smlnskgmail.jaman.hashchecker.history;

import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.history.HistoryItem;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HistoryItemTest {

    @Test
    public void validateHistoryItemTest() {
        Date generationDate = new Date();
        HashType hashType = HashType.MD5;
        boolean isFile = true;
        String objectValue = "./Downloads/task_manager.apk";
        String hashValue = "9gkfnb7nvklckofdamvkdlsop16789dm";

        HistoryItem historyItem = new HistoryItem(
                generationDate,
                hashType,
                isFile,
                objectValue,
                hashValue
        );

        assertEquals(
                generationDate,
                historyItem.getGenerationDate()
        );
        assertEquals(
                hashType,
                historyItem.getHashType()
        );
        assertEquals(
                isFile,
                historyItem.isFile()
        );
        assertEquals(
                objectValue,
                historyItem.getObjectValue()
        );
        assertEquals(
                hashValue,
                historyItem.getHashValue()
        );

        assertEquals(
                historyItem,
                historyItem
        );

        assertNotEquals(
                historyItem,
                null
        );
        assertNotEquals(
                historyItem,
                "String"
        );
    }

}
