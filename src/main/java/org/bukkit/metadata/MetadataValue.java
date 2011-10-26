package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

public interface MetadataValue {

    /**
     * Attempts to convert this metadata value to an int and return it.
     * @return
     * @throws MetadataConversionException
     */
    public int asInt() throws MetadataConversionException;

    /**
     * Attempts to convert this metadata value to a double and return it.
     * @return
     * @throws MetadataConversionException
     */
    public double asDouble() throws MetadataConversionException;

    /**
     * Attempts to convert this metadata value to a boolean and return it.
     * @return
     * @throws MetadataConversionException
     */
    public boolean asBoolean() throws MetadataConversionException;

    /**
     * Returns the String representation of this metadata item.
     * @return
     */
    public String asString();

    /**
     * Returns the {@link Plugin} that created this metadata item.
     * @return
     */
    public Plugin getOwningPlugin();

    /**
     * Invalidates this metadata item, forcing it to recompute when next accessed.
     */
    public void invalidate();
}
