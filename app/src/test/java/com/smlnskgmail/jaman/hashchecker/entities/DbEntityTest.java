package com.smlnskgmail.jaman.hashchecker.entities;

import com.smlnskgmail.jaman.hashchecker.logic.database.DbEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DbEntityTest {

    @Test
    public void validateDbEntityTest() {
        long id = 1;
        DbEntity dbEntity = new DbEntity();
        dbEntity.setId(id);

        assertEquals(
                id,
                dbEntity.getId()
        );
    }

}
