package org.bukkit;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Represents the types of skulls
 */
public enum SkullType {
    SKELETON (0),
    WITHER (1),
    ZOMBIE (2),
    PLAYER (3),
    CREEPER (4);

    private short id;
    private final static Map<Short, SkullType> BY_ID = Maps.newHashMap();

    private SkullType(final int id) {
        this.id = (short) id;
    }

    /**
     * Gets the associated data value representing this type of skull
     *
     * @return A short containing the id of this skull type
     */
    public short getId() {
        return id;
    }

    /**
     * Gets the type of skull with the given id.
     *
     * @param id
     *            ID value to fetch
     * @return The {@link SkullType} representing the given value, or null if
     *         it doesn't exist
     */
    public static SkullType getById(final short id) {
        return BY_ID.get(id);
    }

    static {
        for (SkullType type : values()) {
            BY_ID.put(type.id, type);
        }
    }
}
