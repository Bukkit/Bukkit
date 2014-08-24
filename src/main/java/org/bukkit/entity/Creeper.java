package org.bukkit.entity;

/**
 * Represents a Creeper
 */
public interface Creeper extends Monster, FusedExplosive {

    /**
     * Checks if this Creeper is powered (Electrocuted)
     *
     * @return true if this creeper is powered
     */
    public boolean isPowered();

    /**
     * Sets the Powered status of this Creeper.<br>
     * Powered creeper's explosion yield is 2 times bigger than default.
     *
     * @param value New Powered status
     */
    public void setPowered(boolean value);
}
