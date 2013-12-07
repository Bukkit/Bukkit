package org.bukkit;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Represents the different species of fish.
 */
public enum FishSpecies {

    /**
     * Represents the common fish species.
     */
    GENERIC(0x0),
    /**
     * Represents salmon.
     */
    SALMON(0x1),
    /**
     * Represents clownfish.
     */
    CLOWNFISH(0x2),
    /**
     * Represents pufferfish.
     */
    PUFFERFISH(0x3),
    ;

    private final byte data;
    private final static Map<Byte, FishSpecies> BY_DATA = Maps.newHashMap();

    private FishSpecies(final int data) {
        this.data = (byte) data;
    }

    /**
     * Gets the associated data value representing this species
     *
     * @return A byte containing the data value of this fish species
     * @deprecated Magic value
     */
    @Deprecated
    public byte getData() {
        return data;
    }

    /**
     * Gets the FishSpecies with the given data value
     *
     * @param data Data value to fetch
     * @return The {@link FishSpecies} representing the given value, or null if
     *         it doesn't exist
     * @deprecated Magic value
     */
    @Deprecated
    public static FishSpecies getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (FishSpecies species : values()) {
            BY_DATA.put(species.data, species);
        }
    }
}
