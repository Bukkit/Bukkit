package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called whenever an entity dismounts from another entity.
 */
public class EntityDismountEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled;
    private Entity exited = null;

    public EntityDismountEvent(Entity passenger, Entity exited) {
        super(passenger);
        this.exited = exited;
    }

    /**
     * Get the exited entity.
     */
    public Entity getExited() {
        return this.exited;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
