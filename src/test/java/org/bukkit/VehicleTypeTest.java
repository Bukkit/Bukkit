package org.bukkit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.VehicleType;
import org.junit.Test;

public class VehicleTypeTest {

    @Test
    public void getByName() {
        for (VehicleType vehicleType : VehicleType.values()) {
            if (vehicleType.getName() != null) {
                assertThat(VehicleType.fromName(vehicleType.getName()), is(vehicleType));
            }
        }
    }

    @Test
    public void getById() throws Throwable {
        for (VehicleType vehicleType : VehicleType.values()) {
            if (vehicleType.getClass().getField(vehicleType.name()).getAnnotation(Deprecated.class) != null) {
                continue;
            }
            if (vehicleType.getTypeId() >= 0) { // ensure that it's not a special case
                assertThat(VehicleType.fromId(vehicleType.getTypeId()), is(vehicleType));
            }
        }
    }

    @Test
    public void getByOutOfRangeId() {
        assertThat(VehicleType.fromId(Integer.MAX_VALUE), is(nullValue()));
        assertThat(VehicleType.fromId(Integer.MIN_VALUE), is(nullValue()));
    }

    @Test
    public void getByNameNull() {
        assertThat(VehicleType.fromName(null), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void matchVehicleTypeByNull() {
        VehicleType.matchVehicleType(null);
    }

    @Test
    public void matchVehicleTypeById() throws Throwable {
        for (VehicleType vehicleType : VehicleType.values()) {
            if (vehicleType.getClass().getField(vehicleType.name()).getAnnotation(Deprecated.class) != null) {
                continue;
            }
            if (vehicleType.getTypeId() >= 0) {
                assertThat(VehicleType.matchVehicleType(String.valueOf(vehicleType.getTypeId())), is(vehicleType));
            }
        }
    }

    @Test
    public void matchVehicleTypeByName() {
        for (VehicleType vehicleType : VehicleType.values()) {
            if (vehicleType.getName() != null) {
                assertThat(VehicleType.matchVehicleType(vehicleType.getName()), is(vehicleType));
            }
        }
    }

    @Test
    public void matchVehicleTypeByNameUpperCaseAndSpaces() {
        for (VehicleType vehicleType : VehicleType.values()) {
            if (vehicleType.getName() != null){
                String name = vehicleType.getName().replaceAll("_", " ").toUpperCase();
                assertThat(VehicleType.matchVehicleType(name), is(vehicleType));
            }
        }
    }

    @Test
    public void matchVehicleTypeByValue() {
        for (VehicleType vehicleType : VehicleType.values()) {
            assertThat(VehicleType.matchVehicleType(vehicleType.toString()), is(vehicleType));
        }
    }

    @Test
    public void matchVehicleTypeByValueLowerCaseAndSpaces() {
        for (VehicleType vehicleType : VehicleType.values()) {
            String name = vehicleType.toString().replaceAll("_", " ").toLowerCase();
            assertThat(VehicleType.matchVehicleType(name), is(vehicleType));
        }
    }

    //EntityType conversion tests.
    @Test
    public void validEntityTypeShouldBeVehicleType() {
        for (VehicleType vehicleType : VehicleType.values()) {
            assertThat(VehicleType.isVehicle(vehicleType.getEntityType()), is(true));
        }
    }

    @Test
    public void validEntityTypesShouldMatchInName() {
        //Tests that all valid vehicle types are by the by the same name in EntityType,
        //and that they are the same value.
        for (VehicleType vehicleType : VehicleType.values()) {
            assertThat(EntityType.valueOf(vehicleType.name()), notNullValue());
            assertThat(EntityType.valueOf(vehicleType.name()), is(vehicleType.getEntityType()));
        }
    }
}

