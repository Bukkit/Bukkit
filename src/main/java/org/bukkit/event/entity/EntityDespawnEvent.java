package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

/**
 * Called whenever an Entity is removed from the world.
 */
public class EntityDespawnEvent extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();

    public EntityDespawnEvent(final Entity entity) {
        super(entity);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
