package org.bukkit.event.vehicle;

import org.bukkit.Location;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.HandlerList;

/**
 * Raised when a vehicle moves to a new Block
 */
public class VehicleMoveBlockEvent extends VehicleEvent{
    private static final HandlerList handlers = new HandlerList();
    private final Location from;
    private final Location to;

    public VehicleMoveBlockEvent(final Vehicle vehicle, final Location from, final Location to) {
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
