package org.bukkit.entity;

/**
 * Represents a minecart entity.
 * 
 * @author sk89q
 */
public interface Minecart extends Vehicle {
    /**
     * Sets a minecart's damage.
     * 
     * @param damage over 40 to "kill" a minecart
     */
    public void setDamage(int damage);
    
    /**
     * Gets a minecart's damage.
     * 
     * @param damage
     */
    public int getDamage();
	
    /**
     * Gets the maximum visible speed of a minecart. The visible speed is unrelated to the momentum.
     *
     * @param visible speed
     */
    public double getMaxVisibleSpeed();

    /**
     * Sets the maximum visible speed of a minecart. Must be nonnegative. Default is 0.4D.
     *
     * @param visible speed
     */
    public void setMaxVisibleSpeed(double speed);
}
