package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;


/**
 * A FixedMetadataValue is a special case metadata item that contains the same value forever after initialization.
 * Invalidating a FixedMetadataValue has no affect.
 */
public class FixedMetadataValue extends MetadataValueAdapter {
    private final Object internalValue;
    
    /**
     * Initializes a FixedMetadataValue with an Object
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param value the value assigned to this metadata value.
     */
    public FixedMetadataValue(Plugin owningPlugin, final Object value) {
        super(owningPlugin);
        this.internalValue = value;
    }

    /**
     * Gives the fixed value that this MetadataValue represents.
     */
    public Object value() {
        return this.internalValue;
    }

    /**
     * Invalidate this value.
     * 
     * Does nothing, due to the fixed value.
     */
    public void invalidate() {
        // Do nothing
    }
}
