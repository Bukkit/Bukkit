package org.bukkit;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Represents the seven different types of stone.
 */
public enum StoneType {
    /**
     * Represents the normal stone.
     */
    NORMAL(0x0),
    /**
     * Represents the granite.
     */
    GRANITE(0x1),
    /**
     * Represents the polished granite.
     */
    POLISHED_GRANITE(0x2),
    /**
     * Represents the diorite.
     */
    DIORITE(0x3),
    /**
     * Represents the polished diorite.
     */
    POLISHED_DIORITE(0x4),
    /**
     * Represents the andesite.
     */
    ANDESITE(0x5),
    /**
     * Represents the polished andesite.
     */
    POLISHED_ANDESITE(0x6);

    private final byte data;
    private final static Map<Byte, StoneType> BY_DATA = Maps.newHashMap();

    private StoneType(final int data) {
        this.data = (byte) data;
    }

    /**
     * Gets the associated data value representing this type.
     *
     * @return A byte containing the data value of this stone type
     * @deprecated Magic value
     */
    @Deprecated
    public byte getData() {
        return data;
    }

    /**
     * Gets the StoneType with the given data value.
     *
     * @param data The data value to fetch
     * @return The {@link StoneType} representing the given value, or null
     *     if it doesn't exist
     * @deprecated Magic value
     */
    @Deprecated
    public static StoneType getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (StoneType stone : values()) {
            BY_DATA.put(stone.data, stone);
        }
    }
}
