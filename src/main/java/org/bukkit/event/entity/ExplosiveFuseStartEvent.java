package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Explosive;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a fused {@link Explosive} is ignited.
 */
public class ExplosiveFuseStartEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    public ExplosiveFuseStartEvent(Explosive what) {
        super(what);
    }

    /**
     * Gets the {@link Explosive} that was ignited.
     *
     * @return The ignited {@link Explosive}
     */
    @Override
    public Explosive getEntity() {
        return (Explosive) entity;
    }

    /**
     * Gets the event's {@link Location}.
     * @return The event Location
     */
    public Location getLocation() {
        return entity.getLocation ();
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
