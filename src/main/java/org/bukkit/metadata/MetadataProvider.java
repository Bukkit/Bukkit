package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

/**
 * Provide "On-Demand" Metadata lookup.
 * 
 * The purpose of this is to allow plugin authors to define a way for
 * Metadata to be generated on request for a key, rather than having to
 * predefine it.
 *
 * @param <T> A supplied class which can receive Metadata.
 */
public interface MetadataProvider<T> {
    
    /**
     * Get the plugin which owns this provider.
     * @return A Bukkit plugin.
     */
    public Plugin getOwningPlugin();
    
    /**
     * Get a Metadata value for a subject. 
     * @param subject The object for which we're requesting metadata
     * @param key The key on which metadata is being requested.
     * @return A MetadataValue, or null
     */
    public MetadataValue getValue(T subject, String key);
}
