package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Holds information for player movement events
 */
@SuppressWarnings("serial")
public class PlayerMoveEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private Location from;
    private Location to;

    public PlayerMoveEvent(final Player player, final Location from, final Location to) {
        super(Type.PLAYER_MOVE, player);
        this.from = from;
        this.to = to;
    }

    PlayerMoveEvent(final Event.Type type, final Player player, final Location from, final Location to) {
        super(type, player);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     * <p />
     * If a move or teleport event is cancelled, the player will be moved or
     * teleported back to the Location as defined by getFrom(). This will not
     * fire an event
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     * <p />
     * If a move or teleport event is cancelled, the player will be moved or
     * teleported back to the Location as defined by getFrom(). This will not
     * fire an event
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the location this player moved from
     *
     * @return Location the player moved from
     */
    public Location getFrom() {
        return from;
    }

    /**
     * Sets the location to mark as where the player moved from
     *
     * @param from New location to mark as the players previous location
     */
    public void setFrom(Location from) {
        this.from = from;
    }

    /**
     * Gets the location this player moved to
     *
     * @return Location the player moved to
     */
    public Location getTo() {
        return to;
    }

    /**
     * Sets the location that this player will move to
     *
     * @param to New Location this player will move to
     */
    public void setTo(Location to) {
        this.to = to;
    }

    /**
     * Checks to see if the player involved in this event has moved by at least 1 block
     *
     * @return True if the player has changed their block location
     */
    public boolean isCoarse() {
        return !(from.getBlockX() == to.getBlockX()
                && from.getBlockY() == to.getBlockY()
                && from.getBlockZ() == to.getBlockZ()
                && from.getWorld().equals(to.getWorld()));
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
