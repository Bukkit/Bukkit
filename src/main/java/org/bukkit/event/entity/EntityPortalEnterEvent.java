package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.location.WorldLocation;

/**
 * Stores data for entities standing inside a portal block
 */
public class EntityPortalEnterEvent extends EntityEvent {

    private WorldLocation location;

    public EntityPortalEnterEvent(Entity entity, WorldLocation location) {
        super(Type.ENTITY_PORTAL_ENTER, entity);
        this.location = location;
    }

    /**
     * Gets the portal block the entity is touching
     * 
     * @return The portal block the entity is touching
     */
    public WorldLocation getLocation() {
        return location;
    }
}