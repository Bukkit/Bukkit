package org.bukkit;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Represents the six different species of double plants.
 */
public enum DoublePlantSpecies {
    /**
     * Represents the sunflower double plant.
     */
    SUNFLOWER(0x0),
    /**
     * Represents the lilac double plant.
     */
    LILAC(0x1),
    /**
     * Represents the tall grass double plant.
     */
    DOUBLE_TALLGRASS(0x2),
    /**
     * Represents the large fern double plant.
     */
    LARGE_FERN(0x3),
    /**
     * Represents the rose bush double plant.
     */
    ROSE_BUSH(0x4),
    /**
     * Represents the peony double plant.
     */
    PEONY(0x5),
    /**
     * Represents the upper part of a double plant.
     */
    PLANT_APEX(0x8);

    private final byte data;
    private final static Map<Byte, DoublePlantSpecies> BY_DATA = Maps.newHashMap();

    private DoublePlantSpecies(final int data) {
        this.data = (byte) data;
    }

    /**
     * Gets the associated data value representing this species.
     *
     * @return A byte containing the data value of this double plant species
     * @deprecated Magic value
     */
    @Deprecated
    public byte getData() {
        return data;
    }

    /**
     * Gets the DoublePlantSpecies with the given data value.
     *
     * @param data The data value to fetch
     * @return The {@link DoublePlantSpecies} representing the given value, or null
     *     if it doesn't exist
     * @deprecated Magic value
     */
    @Deprecated
    public static DoublePlantSpecies getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (DoublePlantSpecies doublePlantSpecies : values()) {
            BY_DATA.put(doublePlantSpecies.data, doublePlantSpecies);
        }
    }
}
