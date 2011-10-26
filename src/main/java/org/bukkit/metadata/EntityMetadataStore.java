package org.bukkit.metadata;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * An EntityMetadataStore stores metadata values for all {@link Entity} classes an their descendants.
 */
public class EntityMetadataStore extends MetadataStoreBase<Entity> implements MetadataStore<Entity> {
    @Override
    protected String Disambiguate(Entity entity, String metadataKey) {
        if(entity instanceof Player) {
            Player p = (Player) entity;
            return p.getName() + ":" + metadataKey;
        } else {
            return Integer.toString(entity.getEntityId()) + ":" + metadataKey;
        }
    }
}
