package com.smlnskgmail.jaman.hashchecker.database;

import com.smlnskgmail.jaman.hashchecker.logic.database.HelperFactory;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class HistoryHelperTest {

    @Test
    public void runTest() {
        assertNotNull(HelperFactory.getHelper());

        HelperFactory.releaseHelper();
        assertNull(HelperFactory.getHelper());
    }

}
