package org.bukkit.event.vehicle;

import org.bukkit.entity.Vehicle;
import org.bukkit.event.HandlerList;

/**
 * Raised when a vehicle is created.
 */
@SuppressWarnings("serial")
public class VehicleCreateEvent extends VehicleEvent {
    public VehicleCreateEvent(Vehicle vehicle) {
        super(Type.VEHICLE_CREATE, vehicle);
    }
    
    private static final HandlerList handlers = new HandlerList();
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
