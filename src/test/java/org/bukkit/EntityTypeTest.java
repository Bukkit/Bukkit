package org.bukkit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.bukkit.entity.EntityType;
import org.junit.Test;

public class EntityTypeTest {

    @Test
    public void getByName() {
        for (EntityType entityType : EntityType.values()) {
        	if (entityType.getName() != null)
                assertThat(EntityType.fromName(entityType.getName()), is(entityType));
        }
    }

    @Test
    public void getById() throws Throwable {
        for (EntityType entityType : EntityType.values()) {
            if (entityType.getClass().getField(entityType.name()).getAnnotation(Deprecated.class) != null) {
                continue;
            }
            if (entityType.getTypeId() >= 0) // ensure that it's not a special case
                assertThat(EntityType.fromId(entityType.getTypeId()), is(entityType));
        }
    }

    @Test
    public void getByOutOfRangeId() {
        assertThat(EntityType.fromId(Integer.MAX_VALUE), is(nullValue()));
        assertThat(EntityType.fromId(Integer.MIN_VALUE), is(nullValue()));
    }

    @Test
    public void getByNameNull() {
        assertThat(EntityType.fromName(null), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void matchEntityTypeByNull() {
        EntityType.matchEntityType(null);
    }

    @Test
    public void matchEntityTypeById() throws Throwable {
        for (EntityType entityType : EntityType.values()) {
            if (entityType.getClass().getField(entityType.name()).getAnnotation(Deprecated.class) != null) {
                continue;
            }
            if (entityType.getTypeId() >= 0)
                assertThat(EntityType.matchEntityType(String.valueOf(entityType.getTypeId())), is(entityType));
        }
    }

    @Test
    public void matchEntityTypeByName() {
        for (EntityType entityType : EntityType.values()) {
        	if (entityType.getName() != null)
                assertThat(EntityType.matchEntityType(entityType.getName()), is(entityType));
        }
    }

    @Test
    public void matchEntityTypeByNameUpperCaseAndSpaces() {
        for (EntityType entityType : EntityType.values()) {
        	if (entityType.getName() != null){
                String name = entityType.getName().replaceAll("_", " ").toUpperCase();
                assertThat(EntityType.matchEntityType(name), is(entityType));
        	}
        }
    }

    @Test
    public void matchEntityTypeByValue() {
        for (EntityType entityType : EntityType.values()) {
            assertThat(EntityType.matchEntityType(entityType.toString()), is(entityType));
        }
    }

    @Test
    public void matchEntityTypeByValueLowerCaseAndSpaces() {
        for (EntityType entityType : EntityType.values()) {
            String name = entityType.toString().replaceAll("_", " ").toLowerCase();
            assertThat(EntityType.matchEntityType(name), is(entityType));
        }
    }
}
