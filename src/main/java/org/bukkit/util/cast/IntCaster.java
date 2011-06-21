package org.bukkit.util.cast;

/**
 * Casts the object to an Integer, if the object is a Number, otherwise null.
 */
public final class IntCaster implements Caster<Integer> {
    
    public static final IntCaster INSTANCE = new IntCaster();
    
    private IntCaster() {};
    
    /**
     * Casts the object to an Integer, if the object is a Number, otherwise null.
     */
    public Integer cast(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Number) {
            return ((Number) o).intValue();
        } else {
            return null;
        }
    }
}