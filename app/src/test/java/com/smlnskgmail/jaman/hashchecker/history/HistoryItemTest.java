package com.smlnskgmail.jaman.hashchecker.history;

import com.smlnskgmail.jaman.hashchecker.entities.BaseEntityTest;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.history.HistoryItem;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HistoryItemTest extends BaseEntityTest {

    private Date generationDate = new Date();
    private HashType hashType = HashType.MD5;
    private boolean isFile = true;
    private String objectValue = "./Downloads/task_manager.apk";
    private String hashValue = "9gkfnb7nvklckofdamvkdlsop16789dm";

    private HistoryItem historyItem = new HistoryItem(
            generationDate,
            hashType,
            isFile,
            objectValue,
            hashValue
    );

    @Override
    public void validateFields() {
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
    }

    @Override
    public void validateEquals() {
        assertEquals(
                historyItem,
                historyItem
        );
        assertEquals(
                new HistoryItem(
                        generationDate,
                        hashType,
                        isFile,
                        objectValue,
                        hashValue
                ),
                historyItem
        );

        assertNotEquals(
                new HistoryItem(
                        generationDate,
                        HashType.SHA_1,
                        isFile,
                        objectValue,
                        hashValue
                ),
                historyItem
        );
        assertNotEquals(
                new HistoryItem(
                        generationDate,
                        hashType,
                        false,
                        objectValue,
                        hashValue
                ),
                historyItem
        );
        assertNotEquals(
                new HistoryItem(
                        generationDate,
                        hashType,
                        isFile,
                        "",
                        hashValue
                ),
                historyItem
        );
        assertNotEquals(
                new HistoryItem(
                        generationDate,
                        hashType,
                        isFile,
                        objectValue,
                        ""
                ),
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

    @Override
    public void validateHashCode() {
        assertEquals(
                historyItem.hashCode(),
                historyItem.hashCode()
        );
        assertEquals(
                new HistoryItem(
                        generationDate,
                        hashType,
                        isFile,
                        objectValue,
                        hashValue
                ).hashCode(),
                historyItem.hashCode()
        );

        assertNotEquals(
                new HistoryItem(
                        generationDate,
                        HashType.SHA_1,
                        isFile,
                        objectValue,
                        hashValue
                ).hashCode(),
                historyItem.hashCode()
        );
    }

}
