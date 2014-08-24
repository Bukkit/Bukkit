package org.bukkit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.HangingType;
import org.junit.Test;

public class HangingTypeTest {

    //Getting tests.
    @Test
    public void getByName() {
        for (HangingType hangingType : HangingType.values()) {
            if (hangingType.getName() != null) {
                assertThat(HangingType.fromName(hangingType.getName()), is(hangingType));
            }
        }
    }

    @Test
    public void getById() throws Throwable {
        for (HangingType hangingType : HangingType.values()) {
            if (hangingType.getClass().getField(hangingType.name()).getAnnotation(Deprecated.class) != null) {
                continue;
            }
            if (hangingType.getTypeId() >= 0) { // ensure that it's not a special case
                assertThat(HangingType.fromId(hangingType.getTypeId()), is(hangingType));
            }
        }
    }

    @Test
    public void getByOutOfRangeId() {
        assertThat(HangingType.fromId(Integer.MAX_VALUE), is(nullValue()));
        assertThat(HangingType.fromId(Integer.MIN_VALUE), is(nullValue()));
    }

    @Test
    public void getByNameNull() {
        assertThat(HangingType.fromName(null), is(nullValue()));
    }

    //Matching tests.
    @Test(expected = IllegalArgumentException.class)
    public void matchHangingTypeByNull() {
        HangingType.matchHangingType(null);
    }

    @Test
    public void matchHangingTypeById() throws Throwable {
        for (HangingType hangingType : HangingType.values()) {
            if (hangingType.getClass().getField(hangingType.name()).getAnnotation(Deprecated.class) != null) {
                continue;
            }
            if (hangingType.getTypeId() >= 0) {
                assertThat(HangingType.matchHangingType(String.valueOf(hangingType.getTypeId())), is(hangingType));
            }
        }
    }

    @Test
    public void matchHangingTypeByName() {
        for (HangingType hangingType : HangingType.values()) {
            if (hangingType.getName() != null) {
                assertThat(HangingType.matchHangingType(hangingType.getName()), is(hangingType));
            }
        }
    }

    @Test
    public void matchHangingTypeByNameUpperCaseAndSpaces() {
        for (HangingType hangingType : HangingType.values()) {
            if (hangingType.getName() != null){
                String name = hangingType.getName().replaceAll("_", " ").toUpperCase();
                assertThat(HangingType.matchHangingType(name), is(hangingType));
            }
        }
    }

    @Test
    public void matchHangingTypeByValue() {
        for (HangingType hangingType : HangingType.values()) {
            assertThat(HangingType.matchHangingType(hangingType.toString()), is(hangingType));
        }
    }

    @Test
    public void matchHangingTypeByValueLowerCaseAndSpaces() {
        for (HangingType hangingType : HangingType.values()) {
            String name = hangingType.toString().replaceAll("_", " ").toLowerCase();
            assertThat(HangingType.matchHangingType(name), is(hangingType));
        }
    }

    @Test
    public void entityTypeClassShouldMatchHangingTypeClass() {
        for (HangingType hangingType : HangingType.values()) {
            assertEquals(hangingType.getEntityClass(), hangingType.getEntityType().getEntityClass());
        }
    }

    //EntityType conversion tests.
    @Test
    public void validEntityTypeShouldBeHangingType() {
        for (HangingType hangingType : HangingType.values()) {
            assertThat(HangingType.isHanging(hangingType.getEntityType()), is(true));
        }
    }

    @Test
    public void validEntityTypesShouldMatchInName() {
        //Tests that all valid hanging types are by the by the same name in EntityType,
        //and that they are the same value.
        for (HangingType hangingType : HangingType.values()) {
            assertThat(EntityType.valueOf(hangingType.name()), notNullValue());
            assertThat(EntityType.valueOf(hangingType.name()), is(hangingType.getEntityType()));
        }
    }
}

