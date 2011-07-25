package org.bukkit.util.cast;

/**
 * Tries to cast the given object into another one.
 */
public interface Caster<T> {

    /**
     * Casts the object to the type <code>t</code>. If unable, it will return null.
     * @param o This object will be casted.
     * @return If o is <code>null</code> or it is impossible to cast it will return <code>null</code>. Otherwise the casted object.
     */
    T cast(Object o);
}