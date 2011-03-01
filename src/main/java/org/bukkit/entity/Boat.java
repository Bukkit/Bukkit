package org.bukkit.entity;

/**
 * Represents a boat entity.
 * 
 * @author sk89q
 */
public interface Boat extends Vehicle {

    /**
     * Gets the boat's damage
     *
     * @return int damage
     */
    public int getDamage();

    /**
     * Sets the boat's damage
     *
     * @param damage int
     */
    public void setDamage(int damage);

    /**
     * Gets the maximum speed of a boat. The speed is unrelated to the velocity.
     *
     * @param speed
     */
    public double getMaxSpeed();

    /**
     * Sets the maximum speed of a boat. Must be nonnegative. Default is 0.4D.
     *
     * @param speed
     */
    public void setMaxSpeed(double speed);
}
