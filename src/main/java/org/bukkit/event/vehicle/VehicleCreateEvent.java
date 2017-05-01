package org.bukkit.event.vehicle;

import org.bukkit.entity.Vehicle;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Raised when a vehicle is created.
 */
public class VehicleCreateEvent extends VehicleEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Object creator;
    private final VehicleCreateReason reason;
    
    public VehicleCreateEvent(final Vehicle vehicle, final Object creator, final VehicleCreateReason reason) {
        super(vehicle);
        
        this.creator = creator;
        this.reason = reason;
    }

    
    /**
     * Returns the creator of this minecart. Minecarts can
     * be created a number of ways, which is the reason why
     * the creator is represented by a generic Object.
     * 
     * This can represent one of the following:
     * 
     * Dispenser = it was created by a dispenser
     * Player = A player created this vehicle
     * null = This was created using the <b>CraftWorld.spawn()</b> method
     * @return The generic object representing the creator of this vehicle
     */
    public Object getCreator() {
        return creator;
    }
    
    /**
     * Returns the reason this vehicle was created
     * @return Reason for vehicle's creation
     */
    public VehicleCreateReason getReason() {
        return reason;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
    
    public boolean isCancelled() {
        return cancelled;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
