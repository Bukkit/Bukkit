package org.bukkit;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Represents the two different types of Sand
 */
public enum SandType {
    NORMAL(0x0),
    RED(0x1);

    private final byte data;
    private final static Map<Byte, SandType> BY_DATA = Maps.newHashMap();

    private SandType(final int data) {
        this.data = (byte) data;
    }

    /**
     * Gets the associated data value representing this type of sand
     *
     * @return A byte containing the data value of this sand type
     * @deprecated Magic value
     */
    @Deprecated
    public byte getData() {
        return data;
    }

    /**
     * Gets the type of sand with the given data value
     *
     * @param data Data value to fetch
     * @return The {@link org.bukkit.SandType} representing the given value, or null
     *     if it doesn't exist
     * @deprecated Magic value
     */
    @Deprecated
    public static SandType getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (SandType type : values()) {
            BY_DATA.put(type.data, type);
        }
    }
}
