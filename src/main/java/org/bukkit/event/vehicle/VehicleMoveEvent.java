package org.bukkit.event.vehicle;

import org.bukkit.Location;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;

/**
 * Raised when a vehicle moves.
 */
public class VehicleMoveEvent extends VehicleEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Location from;
    private final Location to;
    private boolean cancelled;

    public VehicleMoveEvent(final Vehicle vehicle, final Location from, final Location to) {
        super(vehicle);

        this.from = from;
        this.to = to;
    }

    /**
     * Get the previous position.
     *
     * @return Old position.
     */
    public Location getFrom() {
        return from;
    }

    /**
     * Get if event is cancelled
     *
     * @return Event is cancelled
     */
   public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
    
    /**
     * Get the next position.
     *
     * @return New position.
     */
    public Location getTo() {
        return to;
    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
