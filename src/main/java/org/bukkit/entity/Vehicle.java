package org.bukkit.entity;

import org.bukkit.location.Location;

/**
 * Represents a vehicle entity.
 *
 * @author sk89q
 */
public interface Vehicle extends Entity {

    /**
     * Gets the vehicle's velocity.
     *
     * @return velocity vector
     */
    public Location getVelocity();

    /**
     * Sets the vehicle's velocity.
     *
     * @param vel velocity vector
     */
    public void setVelocity(Location vel);
}
