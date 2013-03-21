package org.bukkit.entity;

/**
 * Represents a fishing hook.
 */
public interface Fish extends Projectile {

    /**
     * Causes a fish to bite the hook.
     */
    public void bite();

    /**
     * Gets the chance of a fish biting during normal weather.
     *
     * @return chance
     */
    public short getBiteChance();

    /**
     * Sets the chance of a fish biting during normal weather.
     *
     * @param chance "1-in-chance" chance of triggering a bite each tick
     */
    public void setBiteChance(short chance);

    /**
     * Gets the chance of a fish biting outdoors during rain.
     *
     * @return chance
     */
    public short getRainyBiteChance();

    /**
     * Sets the chance of a fish biting outdoors during rain.
     *
     * @param chance "1-in-chance" chance of triggering a bite each tick
     */
    public void setRainyBiteChance(short chance);
}
