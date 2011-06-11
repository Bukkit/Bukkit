package org.bukkit.event.vehicle;

import org.bukkit.entity.Vehicle;
import org.bukkit.location.DirectionalLocation;

/**
 * Raised when a vehicle moves.
 *
 * @author sk89q
 */
public class VehicleMoveEvent extends VehicleEvent {
    private DirectionalLocation from;
    private DirectionalLocation to;

    public VehicleMoveEvent(Vehicle vehicle, DirectionalLocation from, DirectionalLocation to) {
        super(Type.VEHICLE_MOVE, vehicle);

        this.from = from;
        this.to = to;
    }

    /**
     * Get the previous position.
     *
     * @return
     */
    public DirectionalLocation getFrom() {
        return from;
    }

    /**
     * Get the next position.
     *
     * @return
     */
    public DirectionalLocation getTo() {
        return to;
    }
}
