package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;

/**
 * Stores data for entities standing inside a portal block
 */
@SuppressWarnings("serial")
public class EntityPortalEnterEvent extends EntityEvent {

    private Location location;

    public EntityPortalEnterEvent(Entity entity, Location location) {
        super(Type.ENTITY_PORTAL_ENTER, entity);
        this.location = location;
    }

    /**
     * Gets the portal block the entity is touching
     *
     * @return The portal block the entity is touching
     */
    public Location getLocation() {
        return location;
    }
    
    private static final HandlerList handlers = new HandlerList();
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
