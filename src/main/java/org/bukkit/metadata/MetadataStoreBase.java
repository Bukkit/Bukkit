package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MetadataStoreBase<TSubject> {
    private Map<String, List<MetadataValue>> metadataMap = new HashMap<String, List<MetadataValue>>();

    /**
     * Adds a metadata value to an object. If a plugin has already added a metadata value to an object, that value
     * will be replaced with the value of {@code newMetadataValue}.
     *
     * Implementation note: I considered using a {@link java.util.concurrent.locks.ReadWriteLock} for controlling
     * access to {@code metadataMap}, but decided that the added overhead wasn't worth the finer grained access control.
     * Bukkit is almost entirely single threaded so locking overhead shouldn't pose a problem.
     * @param subject The object receiving the metadata.
     * @param metadataKey A unique key to identify this metadata.
     * @param newMetadataValue
     */
    public synchronized void setMetadata(TSubject subject, String metadataKey, MetadataValue newMetadataValue) {
        String key = Disambiguate(subject, metadataKey);
        if(!metadataMap.containsKey(key)) {
            metadataMap.put(key, new ArrayList<MetadataValue>());
        }
        // we now have a list of subject's metadata for the given metadata key. If newMetadataValue's owningPlugin
        // is found in this list, replace the value rather than add a new one.
        List<MetadataValue> metadataList = metadataMap.get(key);
        for(int i = 0; i < metadataList.size(); i++) {
            if(metadataList.get(i).getOwningPlugin() == newMetadataValue.getOwningPlugin()) {
                metadataList.set(i, newMetadataValue);
                return;
            }
        }
        // we didn't find a duplicate...add the new metadata value
        metadataList.add(newMetadataValue);
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
    public synchronized boolean hasMetadata(TSubject subject, String metadataKey) {
        String key = Disambiguate(subject, metadataKey);
        return metadataMap.containsKey(key);
    }

    /**
     * Removes a metadata item owned by a plugin from a subject.
     * @param subject
     * @param metadataKey
     * @param owningPlugin
     */
    public synchronized void removeMetadata(TSubject subject, String metadataKey, Plugin owningPlugin) {
        String key = Disambiguate(subject, metadataKey);
        List<MetadataValue> metadataList = metadataMap.get(key);
        for(int i = 0; i < metadataList.size(); i++) {
            if(metadataList.get(i).getOwningPlugin() == owningPlugin) {
                metadataList.remove(i);
            }
        }
    }

    /**
     * Invalidates all metadata in the metadata store that originates from the given plugin. Doing this will force
     * each invalidated metadata item to be recalculated the next time it is accessed.
     * @param owningPlugin
     */
    public synchronized void invalidateAll(Plugin owningPlugin) {
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
