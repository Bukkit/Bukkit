package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * This interface is implemented by all objects that can provide metadata about themselves.
 */
public interface Metadatable {
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue);
    public List<MetadataValue> getMetadata(String metadataKey);
    public boolean hasMetadata(String metadataKey);
    public void removeMetadata(String metadataKey, Plugin owningPlugin);
}
