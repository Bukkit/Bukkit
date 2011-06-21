package org.bukkit.util.cast;


public final class DoubleCaster implements Caster<Double> {
    
    public static final DoubleCaster INSTANCE = new DoubleCaster();
    
    private DoubleCaster() {};
    
    /**
     * Casts the object to a double, if the object is a Number, otherwise null.
     */
    public Double cast(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Number) {
            return ((Number) o).doubleValue();
        } else {
            return null;
        }
    }
}