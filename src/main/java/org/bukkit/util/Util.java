package org.bukkit.util;

import java.util.Comparator;

/**
 * General utility class for Bukkit.
 */
public final class Util {

    private Util() {}

    /**
     * Returns a value with the given boundary.
     * @param value given value.
     * @param min given minimum.
     * @param max given maximum.
     * @return a value greater equals the minimum and lower equals the maximum.
     */
    public static <T extends Comparable<T>> T between(T value, T min, T max) {
        return value.compareTo(min) < 0 ? min : (value.compareTo(max) > 0 ? max : value);
    }

    /**
     * Returns a value with the given boundary and comparator.
     * @param value given value.
     * @param min given minimum.
     * @param max given maximum.
     * @param comparator to compare the values.
     * @return a value greater equals the minimum and lower equals the maximum.
     */
    public static <T> T between(T value, T min, T max, Comparator<T> comparator) {
        return comparator.compare(value, min) < 0 ? min : (comparator.compare(value,max) > 0 ? max : value);
    }
}
