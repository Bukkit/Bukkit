package org.bukkit;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * All supported color values for dyes and cloth
 */
public enum DyeColor {

    /**
     * Represents white dye
     */
    WHITE(0xF),
    /**
     * Represents orange dye
     */
    ORANGE(0xE),
    /**
     * Represents magenta dye
     */
    MAGENTA(0xD),
    /**
     * Represents light blue dye
     */
    LIGHT_BLUE(0xC),
    /**
     * Represents yellow dye
     */
    YELLOW(0xB),
    /**
     * Represents lime dye
     */
    LIME(0xA),
    /**
     * Represents pink dye
     */
    PINK(0x9),
    /**
     * Represents (dark) gray dye
     */
    GRAY(0x8),
    /**
     * Represents silver (light gray) dye
     */
    SILVER(0x7),
    /**
     * Represents cyan dye
     */
    CYAN(0x6),
    /**
     * Represents purple dye
     */
    PURPLE(0x5),
    /**
     * Represents blue dye
     */
    BLUE(0x4),
    /**
     * Represents brown dye
     */
    BROWN(0x3),
    /**
     * Represents green dye
     */
    GREEN(0x2),
    /**
     * Represents red dye
     */
    RED(0x1),
    /**
     * Represents black dye
     */
    BLACK(0x0);

    private final byte data;
    private final static Map<Byte, DyeColor> BY_DATA = Maps.newHashMap();

    private DyeColor(final int data) {
        this.data = (byte) data;
    }

    /**
     * Gets the associated data value representing this color
     *
     * @return A byte containing the data value of this color
     */
    public byte getData() {
        return data;
    }

    /**
     * Gets the DyeColor with the given data value
     *
     * @param data Data value to fetch
     * @return The {@link DyeColor} representing the given value, or null if it doesn't exist
     */
    public static DyeColor getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (DyeColor color : values()) {
            BY_DATA.put(color.getData(), color);
        }
    }
}
