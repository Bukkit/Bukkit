package org.bukkit.metadata;

import org.bukkit.World;

/**
 * An EntityMetadataStore stores metadata values for all {@link org.bukkit.entity.Entity} classes an their descendants.
 */
public class WorldMetadataStore extends MetadataStoreBase<World> implements MetadataStore<World> {
    @Override
    protected String Disambiguate(World world, String metadataKey) {
        return world.getUID().toString() + ":" + metadataKey;
    }
}
