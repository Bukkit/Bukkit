package org.bukkit.util.cast;

/**
 * Casts the object to a string, if the object isn't null. It will call the
 * {@link Object#toString()} method.
 */
public final class StringCaster implements Caster<String> {

    public static final StringCaster INSTANCE = new StringCaster();

    private StringCaster() {
    };

    /**
     * Casts the object to a string, if the object isn't null. It will call the
     * {@link Object#toString()} method.
     */
    public String cast(Object o) {
        return o == null ? null : o.toString();
    }
}