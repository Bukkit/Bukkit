package org.bukkit.metadata;

import java.util.List;

/**
 * This interface is implemented by all objects that can provide metadata about themselves.
 */
public interface Metadatable {
    public void addMetadata(String metadataKey, MetadataValue newMetadataValue);
    public List<MetadataValue> getMetadata(String metadataKey);
    public boolean hasMetadata(String metadataKey);
}
