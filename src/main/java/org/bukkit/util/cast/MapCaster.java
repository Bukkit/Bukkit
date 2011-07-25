package org.bukkit.util.cast;

import java.util.Map;

/**
 * Casts the object to a map, if the object is one, otherwise null.
 */
public class MapCaster<K, V> implements Caster<Map<K, V>> {

    public static final MapCaster<String, Object> STRING_OBJECT_INSTANCE = new MapCaster<String, Object>(); 
    
    /**
     * Casts the object to a map, if the object is one, otherwise null.
     */
    @SuppressWarnings("unchecked")
    public Map<K, V> cast(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Map) {
            return (Map<K, V>) o;
        } else {
            return null;
        }
    }

}
