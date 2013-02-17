package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;


/**
 * A special case metadata item that contains the same value forever after initialization. 
 * 
 * History/Justification:
 * This is a memory/speed improvement over FixedMetadataValue which could not be 
 * improved because doing so would violate previous inheritance hierarchy.
 */
public class StaticMetadataValue extends MetadataValueAdapter {
    /** Hold the internal value of this metadata. */
    private final Object internalValue;

    /**
     * Initializes a FixedMetadataValue with an Object
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param value the value assigned to this metadata value.
     */
    public StaticMetadataValue(Plugin owningPlugin, final Object value) {
        super(owningPlugin);
        this.internalValue = value;
    }

    /**
     * Get the value associated with this MetadataValue. 
     */
    public Object value() {
        return this.internalValue;
    }

    /** Invalidating a StaticMetadataValue does nothing. */
    public void invalidate() {
    }
}
