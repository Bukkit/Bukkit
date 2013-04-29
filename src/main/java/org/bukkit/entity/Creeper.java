package org.bukkit.entity;

/**
 * Represents a Creeper
 */
public interface Creeper extends Monster {

    /**
     * Checks if this Creeper is powered (Electrocuted)
     *
     * @return true if this creeper is powered
     */
    public boolean isPowered();

    /**
     * Sets the Powered status of this Creeper
     *
     * @param value New Powered status
     */
    public void setPowered(boolean value);
    
    /**
     * Gets the explosion range of this Creeper.
     *
     * @return the explosion range
     */
    public int getExplosionRange();
    
    /**
     * Sets the explosion range of this Creeper.
     *
     * @param value New explosion range
     */
    public void setExplosionRange(int value);
}
