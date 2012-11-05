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
     * Sets number of ticks before explosion
     *
     * @param fuse Number of ticks to set
     */
    public void setMaxFuseTicks(int fuse);

    /**
     * Sets explosion radius
     *
     * @param radius Explosion radius
     */
    public void setExplosionRadius(int radius);

    /**
     * Gets number of ticks before explosion
     *
     * @return Number of ticks before explosion
     */
    public int getMaxFuseTicks();

    /**
     * Gets explosion radius
     *
     * @return Explosion radius
     */
    public int getExplosionRadius();
}
