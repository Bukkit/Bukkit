package org.bukkit.event.entity;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

import java.util.List;

/**
 * Called when a firework explodes
 */
public class FireworkExplodeEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel;
    private final Location location;

    public FireworkExplodeEvent(final Firework what, final Location location) {
        super(what);
        this.location = location;
        this.cancel = false;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Returns the location where the explosion happened.
     * If it's not possible to get this value from the Firework itself
     * @return The location of the firework explosion
     */
    public Location getLocation() {
        return location;
    }

    @Override
    public Firework getEntity() {
        return (Firework) entity;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
