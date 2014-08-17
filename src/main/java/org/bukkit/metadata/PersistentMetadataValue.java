package org.bukkit.metadata;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
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
     * Initializes a PersistentMetadataValue with a
     * ConfigurationSerializable Object
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final ConfigurationSerializable value) {
        this(owningPlugin, (Object)value);
    }

    /**
     * Initializes a PersistentMetadataValue with a String value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final String value) {
        this(owningPlugin, (Object)value);
    }

    /**
     * Initializes a PersistentMetadataValue with an Integer value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Integer value) {
        this(owningPlugin, (Object)value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Boolean value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Boolean value) {
        this(owningPlugin, (Object)value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Long value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Long value) {
        this(owningPlugin, (Object)value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Double value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Double value) {
        this(owningPlugin, (Object)value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Short value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Short value) {
        this(owningPlugin, (Object)value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Byte value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Byte value) {
        this(owningPlugin, (Object)value);
    }

    /**
     * Initializes a PersistentMetadataValue with a Float value.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    public PersistentMetadataValue(Plugin owningPlugin, final Float value) {
        this(owningPlugin, (Object)value);
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
        this(owningPlugin, (Object)value);
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
        this(owningPlugin, (Object)value);
    }

    /**
     * Initializes a PersistentMetadataValue with an Object value.
     * <p>
     * The Object must be one of the valid persistable object types.
     *
     * @param owningPlugin the {@link org.bukkit.plugin.Plugin} that created this metadata value
     * @param value the value assigned to this metadata value
     */
    private PersistentMetadataValue(Plugin owningPlugin, final Object value) {
        super(owningPlugin);
        this.internalValue = value;
    }

    @Override
    public Object value() {
        return internalValue;
    }

    @Override
    public void invalidate() {

    }

    public List<Object> asList() {
        return internalValue instanceof List ? (List<Object>)internalValue : Collections.emptyList();
    }

    public Map<String, Object> asMap() {
        return internalValue instanceof Map ? (Map<String, Object>)internalValue : Collections.<String, Object>emptyMap();
    }
}
