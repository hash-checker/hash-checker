package com.smlnskgmail.jaman.hashchecker.entities;

import com.smlnskgmail.jaman.hashchecker.logic.database.api.DbEntity;

import static org.junit.Assert.assertEquals;

public class DbEntityTest extends BaseEntityTest {

    private final DbEntity dbEntity = new DbEntity();

    @Override
    public void validateFields() {
        long id = 1;
        dbEntity.setId(id);
        assertEquals(
                id,
                dbEntity.getId()
        );
    }

    @Override
    public void validateEquals() {
        assertEquals(
                dbEntity,
                dbEntity
        );
    }

    @Override
    public void validateHashCode() {
        assertEquals(
                dbEntity.hashCode(),
                dbEntity.hashCode()
        );
    }

}
