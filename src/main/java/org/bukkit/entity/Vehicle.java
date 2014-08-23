package org.bukkit.entity;

import org.bukkit.util.Vector;

/**
 * Represents a vehicle entity.
 */
public interface Vehicle extends Entity {

    /**
     * Gets the vehicle's velocity.
     *
     * @return velocity vector
     */
    public Vector getVelocity();

    /**
     * Sets the vehicle's velocity.
     *
     * @param vel velocity vector
     */
    public void setVelocity(Vector vel);
    
    /**
     * Get the type of the vehicle.
     *
     * @return The vehicle type.
     */
    public VehicleType getVehicleType();
}
