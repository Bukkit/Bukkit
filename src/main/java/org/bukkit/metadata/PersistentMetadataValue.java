package org.bukkit.metadata;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Map;

/**
 * A PersistentMetadataValue is a special case metadata item that may
 * only contain ConfigurationSerializable Objects, Lists, Maps or
 * basic Object types.
 * <p>
 * This class is primarily here to serve as a reminder that
 * a persistent data store must hold serializable data.
 */
public class PersistentMetadataValue extends MetadataValueAdapter implements MetadataValue {
    /**
     * Store the internal value that is represented by this value.
     * This is expected to be one of a set of a valid serializable types.
     */
    private final Object internalValue;

    /**
     * Initializes a PersistentMetadataValue with an Object value.
     * <p>
     * The Object must be one of the valid persistable object types.
     * Otherwise, an IllegalArgumentException will be thrown.
     *
     * @throws java.lang.IllegalArgumentException on non-persistable input
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Object value) {
        super(owningPlugin);
        this.internalValue = value;

        if (!(
              value instanceof ConfigurationSerializable
           || value instanceof String
           || value instanceof Integer
           || value instanceof Double
           || value instanceof Long
           || value instanceof Boolean
           || value instanceof Short
           || value instanceof Float
           || value instanceof Map
           || value instanceof List
        )) {
            throw new IllegalArgumentException("Invalid Object type for PersistentMetadataValue: " + value.getClass());
        }
    }

    @Override
    public Object value() {
        return internalValue;
    }

    @Override
    public void invalidate() {

    }
}
