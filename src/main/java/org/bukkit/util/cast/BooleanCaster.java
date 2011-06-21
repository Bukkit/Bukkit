package org.bukkit.util.cast;

/**
 * Casts the object to a boolean, if the object is one, otherwise null.
 */
public final class BooleanCaster implements Caster<Boolean> {
    
    public static final BooleanCaster INSTANCE = new BooleanCaster();
    
    private BooleanCaster() {};
    
    /**
     * Casts the object to a boolean, if the object is one, otherwise null.
     */
    public Boolean cast(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Boolean) {
            return (Boolean) o;
        } else {
            return null;
        }
    }
}