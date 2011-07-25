package org.bukkit.util.cast;

/**
 * Casts the object to a long, if the object is a Number, otherwise null.
 */
public final class LongCaster implements Caster<Long> {
    
    public static final LongCaster INSTANCE = new LongCaster();
    
    private LongCaster() {};
    
    /**
     * Casts the object to a long, if the object is a Number, otherwise null.
     */
    public Long cast(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Number) {
            return ((Number) o).longValue();
        } else {
            return null;
        }
    }
}