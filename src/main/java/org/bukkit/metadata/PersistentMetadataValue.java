package org.bukkit.metadata;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Map;

/**
 * A PersistentMetadataValue is a special case metadata item that may
 * only contain ConfigurationSerializeable Objects, Lists, Maps or
 * basic Object types.
 * <p>
 * This class extends FixedMetadataValue to do the work, and is
 * primarily here to serve as a reminder that ItemMeta holds
 * a slightly different form of Metadata.
 */
public class PersistentMetadataValue extends FixedMetadataValue {
    /**
     * Initializes a PersistentMetadataValue with a
     * ConfigurationSerializable Object
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final ConfigurationSerializable value) {
        super(owningPlugin, value);
    }

    /**
     * Initializes a PersistentMetadataValue with a String value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final String value) {
        super(owningPlugin, value);
    }

    /**
     * Initializes a PersistentMetadataValue with an Integer value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Integer value) {
        super(owningPlugin, value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Boolean value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Boolean value) {
        super(owningPlugin, value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Long value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Long value) {
        super(owningPlugin, value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Double value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Double value) {
        super(owningPlugin, value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Short value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Short value) {
        super(owningPlugin, value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Float value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Float value) {
        super(owningPlugin, value);
    }

    /**
     * Initializes a PersistentMetadataValue with a List value.
     * <p>
     * Note that the contents of the List must also be persistable,
     * otherwise an IllegalArgumentException may be generated when
     * the metadata store persists.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final List<?> value) {
        super(owningPlugin, value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Map value.
     * <p>
     * Note that the keys of the Map must be Strings, and the
     * contents of the Map must be persistable,
     * otherwise an IllegalArgumentException may be generated when
     * the metadata store persists.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Map<String, ?> value) {
        super(owningPlugin, value);
    }

    /**
     * Initializes a PersistentMetadataValue with an Object value.
     * <p>
     * The Object must be one of the valid persistable object types.
     * The other Constructor forms are preferred, this is here as a
     * convenience method.
     *
     * @throws java.lang.IllegalArgumentException on non-persistable input
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Object value) {
        super(owningPlugin, value);

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
}
