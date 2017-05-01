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
     * Calling this method will reset yield.
     * Powered true -> yield = 6F
     * Powered false -> yield = 3F
     *
     * @param value New Powered status
     */
    public void setPowered(boolean value);
    
    /**
    * Set the radius affected by this creeper's explosion
    *
    * @param yield The explosive yield
    */
    public void setYield(float yield);

    /**
    * Return the radius or yield of this creeper's explosion
    *
    * @return the radius of blocks affected
    */
    public float getYield();
}
