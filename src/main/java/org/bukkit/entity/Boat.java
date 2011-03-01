package org.bukkit.entity;

/**
 * Represents a boat entity.
 * 
 * @author sk89q
 */
public interface Boat extends Vehicle {

    /**
     * Sets the boat's damage
     *
     * @param damage over 40 to "kill" the boat
     */
    public void setDamage(int damage);


    /**
     * Gets the boat's damage
     *
     * @return damage int
     */
    public int getDamage();


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
