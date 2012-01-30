package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

@SuppressWarnings("serial")
public class EndermanTeleportEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private boolean cancel;
    private Location from;
    private Location to;

    public EndermanTeleportEvent(Entity what, Location from, Location to) {
        super(Type.ENDERMAN_TELEPORT, what);
        this.from = from;
        this.to = to;
        this.cancel = false;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     * <p />
     * If the teleport event is cancelled, the Enderman will be moved or
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
     * If the teleport event is cancelled, the Enderman will be moved or
     * teleported back to the Location as defined by getFrom(). This will not
     * fire an event
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the location that this Enderman moved from
     *
     * @return Location this Enderman moved from
     */
    public Location getFrom() {
        return from;
    }

    /**
     * Sets the location that this Enderman moved from
     *
     * @param from New location to mark as the Enderman's previous location
     */
    public void setFrom(Location from) {
        this.from = from;
    }

    /**
     * Gets the location that this Enderman moved to
     *
     * @return Location the Enderman moved to
     */
    public Location getTo() {
        return to;
    }

    /**
     * Sets the location that this Enderman moved to
     *
     * @param to New Location this Enderman moved to
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