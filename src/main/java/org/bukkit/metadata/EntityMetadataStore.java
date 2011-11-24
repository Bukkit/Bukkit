package org.bukkit.metadata;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * An EntityMetadataStore stores metadata values for all {@link Entity} classes an their descendants.
 */
public class EntityMetadataStore extends MetadataStoreBase<Entity> implements MetadataStore<Entity> {
    /**
     * Generates a unique metadata key for an {@link Entity} entity ID.
     * @see MetadataStoreBase#Disambiguate(Object, String)
     * @param entity
     * @param metadataKey
     * @return
     */
    @Override
    protected String Disambiguate(Entity entity, String metadataKey) {
        return Integer.toString(entity.getEntityId()) + ":" + metadataKey;
    }
}
