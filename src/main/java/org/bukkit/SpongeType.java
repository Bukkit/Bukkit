package org.bukkit;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Represents the two different types of Sponge
 */
public enum SpongeType {
    NORMAL(0x0),
    WET(0x1);

    private final byte data;
    private final static Map<Byte, SpongeType> BY_DATA = Maps.newHashMap();

    private SpongeType(final int data) {
        this.data = (byte) data;
    }

    /**
     * Gets the associated data value representing this type of sponge
     *
     * @return A byte containing the data value of this sponge type
     * @deprecated Magic value
     */
    @Deprecated
    public byte getData() {
        return data;
    }

    /**
     * Gets the type of sponge with the given data value
     *
     * @param data Data value to fetch
     * @return The {@link org.bukkit.SpongeType} representing the given value, or null
     *     if it doesn't exist
     * @deprecated Magic value
     */
    @Deprecated
    public static SpongeType getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (SpongeType type : values()) {
            BY_DATA.put(type.data, type);
        }
    }
}
