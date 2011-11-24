package org.bukkit.metadata;

import org.bukkit.World;

/**
 * An WorldMetadataStore stores metadata values for {@link World} objects.
 */
public class WorldMetadataStore extends MetadataStoreBase<World> implements MetadataStore<World> {
    /**
     * Generates a unique metadata key for a {@link World} object based on the world UID.
     * @see WorldMetadataStore#Disambiguate(Object, String)
     * @param world
     * @param metadataKey
     * @return
     */
    @Override
    protected String Disambiguate(World world, String metadataKey) {
        return world.getUID().toString() + ":" + metadataKey;
    }
}
