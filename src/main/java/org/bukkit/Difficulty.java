package org.bukkit;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the different server-wide difficulty settings.
 */
public enum Difficulty {

    /**
     * Lowest difficulty. No monsters will spawn. (equivalent to spawn-monsters=false).
     */
    PEACEFUL(0),

    /**
     * Hostile mobs spawn. Enemies deal less damage than on normal difficulty. (Default value)
     */
    EASY(1),

    /**
     * Hostile mobs spawn. Enemies deal normal amounts of damage.
     */
    NORMAL(2),

    /**
     * Hostile mobs spawn. Enemies deal more damage than on normal difficulty.
     */
    HARD(3);

    private final int value;
    private final static Map<Integer, Difficulty> difficulties = new HashMap<Integer, Difficulty>();

    private Difficulty(final int value) {
        this.value = value;
    }

    /**
     * Gets the mode value associated with this Difficulty.
     *
     * @return An integer value of this difficulty
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the Difficulty represented by the specified value
     *
     * @param value Value to check
     * @return Associative {@link Difficulty} with the given value, or null if it doesn't exist
     */
    public static Difficulty getByValue(final int value) {
        return difficulties.get(value);
    }

    static {
        for (Difficulty diff : Difficulty.values()) {
            difficulties.put(diff.getValue(), diff);
        }
    }
}