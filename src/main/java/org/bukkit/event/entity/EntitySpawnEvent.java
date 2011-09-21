package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

/**
 * Called when an entity is spawned into the world.
 */
public abstract class EntitySpawnEvent extends EntityEvent implements Cancellable {

    protected final Location location;
    protected boolean cancelled;

    public EntitySpawnEvent(Type type, Entity what, Location loc) {
        super(type, what);
        location = loc;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    /**
     * Gets the location at which the entity is spawning.
     *
     * @return The location at which the entity is spawning.
     */
    public Location getLocation() {
        return location;
    }

}