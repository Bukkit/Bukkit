package org.bukkit;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Represents the various render distances that are available.
 */
public enum RenderDistance {
    TINY(3),
    SHORT(2),
    NORMAL(1),
    FAR(0);

    private final int value;
    private final static Map<Integer, RenderDistance> BY_ID = Maps.newHashMap();

    /**
     * Constructs a new RenderDistance enum with the specified value
     * @param value Value sent in Packet 204 for the render distance
     */
    private RenderDistance(final int value) {
        this.value = value;
    }

    /**
     * Gets the render distance value associated with this RenderDistance.
     *
     * @return An integer value of this render distance
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the RenderDistance represented by the specified value
     *
     * @param value Value to check
     * @return Associative {@link RenderDistance} with the given value, or null if it doesn't exist
     */
    public static RenderDistance getByValue(final int value) {
        return BY_ID.get(value);
    }

    static {
        for (RenderDistance renderDistance : values()) {
            BY_ID.put(renderDistance.value, renderDistance);
        }
    }
}
