package org.bukkit.entity;

/**
 * Represents a fishing hook.
 */
public interface Fish extends Projectile {

    /**
     * Gets the chance of a fish biting.
     *
     * 0.0 = No Chance.
     * 1.0 = Instant catch.
     *
     * @return chance
     */
    public double getBiteChance();

    /**
     * Sets the chance of a fish biting.
     *
     * 0.0 = No Chance.
     * 1.0 = Instant catch.
     *
     * @param chance
     */
    public void setBiteChance(double chance);

}
