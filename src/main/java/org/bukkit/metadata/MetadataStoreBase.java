package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MetadataStoreBase<TSubject> {
    private Map<String, List<MetadataValue>> metadataMap = new HashMap<String, List<MetadataValue>>();

    /**
     * Adds a metadata value to an object.
     * @param subject The object receiving the metadata.
     * @param metadataKey A unique key to identify this metadata.
     * @param newMetadataValue
     */
    public synchronized void addMetadata(TSubject subject, String metadataKey, MetadataValue newMetadataValue) {
        String key = Disambiguate(subject, metadataKey);
        if(!metadataMap.containsKey(key)) {
            metadataMap.put(key, new ArrayList<MetadataValue>());
        }
        metadataMap.get(key).add(newMetadataValue);
    }

    /**
     * Returns all metadata values attached to an object. If multiple plugins have attached metadata, each will value
     * will be included.
     * @param subject
     * @param metadataKey
     * @return
     */
    public synchronized List<MetadataValue> getMetadata(TSubject subject, String metadataKey) {
        String key = Disambiguate(subject, metadataKey);
        if(metadataMap.containsKey(key)) {
            return metadataMap.get(key);
        } else {
            return new ArrayList<MetadataValue>();
        }
    }

    /**
     * Tests to see if a metadata attribute has been set on an object.
     * @param subject
     * @param metadataKey
     * @return
     */
    public boolean hasMetadata(TSubject subject, String metadataKey) {
        String key = Disambiguate(subject, metadataKey);
        return metadataMap.containsKey(key);
    }

    /**
     * Invalidates all metadata in the metadata store that originates from the given plugin. Doing this will force
     * each invalidated metadata item to be recalculated the next time it is accessed.
     * @param owningPlugin
     */
    public void invalidateAll(Plugin owningPlugin) {
        for(List<MetadataValue> values : metadataMap.values()) {
            for(MetadataValue value : values) {
                if(value.getOwningPlugin() == owningPlugin) {
                    value.invalidate();
                }
            }
        }
    }

    /**
     * Creates a unique name for the object receiving metadata. Each concrete implementation of MetadataStoreBase
     * must implement this method.
     * @param subject
     * @param metadataKey
     * @return
     */
    protected abstract String Disambiguate(TSubject subject, String metadataKey);
}
