package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 */
public interface MetadataStore <TSubject> {
    /**
     * Adds a metadata value to an object.
     * @param subject The object receiving the metadata.
     * @param metadataKey A unique key to identify this metadata.
     * @param newMetadataValue
     */
    public void setMetadata(TSubject subject, String metadataKey, MetadataValue newMetadataValue);

    /**
     * Returns all metadata values attached to an object. If multiple plugins have attached metadata, each will value
     * will be included.
     * @param subject
     * @param metadataKey
     * @return
     */
    public List<MetadataValue> getMetadata(TSubject subject, String metadataKey);

    /**
     * Tests to see if a metadata attribute has been set on an object.
     * @param subject
     * @param metadataKey
     * @return
     */
    public boolean hasMetadata(TSubject subject, String metadataKey);

    /**
     * Removes a metadata item owned by a plugin from a subject.
     * @param subject
     * @param metadataKey
     * @param owningPlugin
     */
    public void removeMetadata(TSubject subject, String metadataKey, Plugin owningPlugin);

    /**
     * Invalidates all metadata in the metadata store that originates from the given plugin. Doing this will force
     * each invalidated metadata item to be recalculated the next time it is accessed.
     * @param owningPlugin
     */
    public void invalidateAll(Plugin owningPlugin);
}
