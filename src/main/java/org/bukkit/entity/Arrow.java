package org.bukkit.entity;

/**
 * Represents an arrow.
 */
public interface Arrow extends Projectile {

    /**
     * Determines if this projectile is critical.
     *
     * @return true if it is critical.
     */
    public boolean isCritical();

    /**
     * Set whether or not this arrow should be critical.
     *
     * @param critical whether or not it should be critical.
     */
    public void setCritical(boolean critical);

}
