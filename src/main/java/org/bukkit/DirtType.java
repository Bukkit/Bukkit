package org.bukkit;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Represents the three different types of dirt.
 */
public enum DirtType {
    /**
     * Represents the regular dirt type.
     */
    NORMAL(0x0),
    /**
     * Represents the coarse dirt type.
     */
    COARSE(0x1),
    /**
     * Represents the Podzol dirt type.
     */
    PODZOL(0x2);

    private final byte data;
    private final static Map<Byte, DirtType> BY_DATA = Maps.newHashMap();

    private DirtType(final int data) {
        this.data = (byte) data;
    }

    /**
     * Gets the associated data value representing this type.
     *
     * @return A byte containing the data value of this dirt type
     * @deprecated Magic value
     */
    @Deprecated
    public byte getData() {
        return data;
    }

    /**
     * Gets the DirtType with the given data value.
     *
     * @param data The data value to fetch
     * @return The {@link DirtType} representing the given value, or null
     *     if it doesn't exist
     * @deprecated Magic value
     */
    @Deprecated
    public static DirtType getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (DirtType dirt : values()) {
            BY_DATA.put(dirt.data, dirt);
        }
    }
}
