package org.bukkit;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Represents the three different types of Prismarine
 */
public enum PrismarineType {
    NORMAL(0x0),
    BRICK(0x1),
    DARK(0x2);

    private final byte data;
    private final static Map<Byte, PrismarineType> BY_DATA = Maps.newHashMap();

    private PrismarineType(final int data) {
        this.data = (byte) data;
    }

    /**
     * Gets the associated data value representing this type of prismarine
     *
     * @return A byte containing the data value of this prismarine type
     * @deprecated Magic value
     */
    @Deprecated
    public byte getData() {
        return data;
    }

    /**
     * Gets the type of prismarine with the given data value
     *
     * @param data Data value to fetch
     * @return The {@link org.bukkit.PrismarineType} representing the given value, or null
     *     if it doesn't exist
     * @deprecated Magic value
     */
    @Deprecated
    public static PrismarineType getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (PrismarineType type : values()) {
            BY_DATA.put(type.data, type);
        }
    }
}
