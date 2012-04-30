package org.bukkit;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Represents the four different types of Smooth Brick
 */
public enum SmoothBrickType {
    NORMAL(0x0),
    MOSSY(0x1),
    CRACKED(0x2),
    CIRCLE(0x3);

    private final byte data;
    private final static Map<Byte, SmoothBrickType> BY_DATA = Maps.newHashMap();

    private SmoothBrickType(final int data) {
        this.data = (byte) data;
    }


    /**
     * Gets the associated data value representing this type of smooth brick
     *
     * @return A byte containing the data value of this smooth brick type
     */
    public byte getData() {
        return data;
    }

    /**
     * Gets the type of smooth brick with the given data value
     *
     * @param data
     *            Data value to fetch
     * @return The {@link SmoothBrickType} representing the given value, or null if
     *         it doesn't exist
     */
    public static SmoothBrickType getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (SmoothBrickType type : values()) {
            BY_DATA.put(type.data, type);
        }
    }
}
