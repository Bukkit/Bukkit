package org.bukkit.entity;

/**
 * Represents a fishing hook.
 */
public interface Fish extends Projectile {

    /**
     * Gets the chance of a fish biting.
     *
     * @return chance
     */
    public double getBiteChance();

    /**
     * Sets the chance of a fish biting.
     *
     * @param chance where 0.0 is never, 1.0 is very frequent
     */
    public void setBiteChance(double chance);

}
