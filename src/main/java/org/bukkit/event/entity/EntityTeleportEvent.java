package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

/**
 * Thrown when a non-player entity (such as an Enderman) tries to teleport from one
 * location to another.
 */
public class EntityTeleportEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel;
    private Location from;
    private Location to;
    private TeleportCause cause = TeleportCause.UNKNOWN;

    public EntityTeleportEvent(Entity what, Location from, Location to, TeleportCause cause) {
        super(what);
        this.from = from;
        this.to = to;
        this.cancel = false;
        this.cause = cause;
    }

    public TeleportCause getCause() {
    	return cause;
    }
    
    public enum TeleportCause {
        /**
         * Indicates the teleporation was caused by a player throwing an Ender Pearl
         */
        ENDER_PEARL,
        /**
         * Indicates the teleportation was caused by a player executing a command
         */
        COMMAND,
        /**
         * Indicates the teleportation was caused by a plugin
         */
        PLUGIN,
        /**
         * Indicates the teleportation was caused by a player entering a Nether portal
         */
        NETHER_PORTAL,
        /**
         * Indicates the teleportation was caused by a player entering an End portal
         */
        END_PORTAL,
        /**
         * Indicates the teleportation was caused by an event not covered by this enum
         */
        UNKNOWN;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the location that this entity moved from
     *
     * @return Location this entity moved from
     */
    public Location getFrom() {
        return from;
    }

    /**
     * Sets the location that this entity moved from
     *
     * @param from New location this entity moved from
     */
    public void setFrom(Location from) {
        this.from = from;
    }

    /**
     * Gets the location that this entity moved to
     *
     * @return Location the entity moved to
     */
    public Location getTo() {
        return to;
    }

    /**
     * Sets the location that this entity moved to
     *
     * @param to New Location this entity moved to
     */
    public void setTo(Location to) {
        this.to = to;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}