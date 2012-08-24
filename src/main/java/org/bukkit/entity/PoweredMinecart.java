package org.bukkit.entity;

import org.bukkit.util.Vector;

/**
 * Represents a powered minecart.
 */
public interface PoweredMinecart extends Minecart {

    /**
     * Gets the amount of fuel in the minecart. The fuel is measured in ticks,
     * and one piece of coal put into the minecart adds 3600 ticks.
     *
     * @return Number of ticks for which the minecart will continue moving under power.
     */
    public int getFuel();

    /**
     * Sets the amount of fuel in the minecart. The fuel is measured in ticks,
     * and one piece of coal put into the minecart adds 3600 ticks.
     * If you want to add fuel to a static powered minecart, {@link #setPushMod(Vector) setPushMod() } 
     * will have to be used aswell.
     *
     * @param fuel Number of ticks for which the minecart will continue moving under power.
     */
    public void setFuel(int fuel);

    /**
     * Get whether this minecart consumes fuel while running.
     * Should return true unless a plugin has changed it.
     *
     * @return true if the minecart consumes fuel.
     */
    public boolean consumesFuel();

    /**
     * Get whether this minecart consumes fuel while running.
     * Should return true unless a plugin has changed it.
     *
     * @return true if the minecart consumes fuel.
     */
    public void setConsumesFuel(boolean consumes);

    /**
     * Gets the push modifier for the minecart. The push modifier is the effect the
     * "engine" has on the minecart velocity in 2D space, and the vector Y value will always be 0.
     * The minecart will move autonomously as long as the push modifier x or z is greater than 0 and
     * fuel is greater than 0. When coal is added to the minecart, the push modifier is set to
     * the difference in x and y between the location of the player and the minecart.
     *
     * @return Vector representation of the push modifier, Y value will always be 0.
     */
    public Vector getPushMod();

    /**
     * Sets the push modifier for the minecart.  The push modifier is the effect the
     * "engine" has on the minecart velocity in 2D space, and the vector Y value will be ignored.
     * The minecart will move autonomously as long as the push modifier x or z is greater than 0 and
     * fuel is greater than 0. When coal is added to the minecart, the push modifier is set to
     * the difference in x and y between the location of the player and the minecart.
     *
     * @param push Vector to set the push modifier to, Y value will be ignored.
     */
    public void setPushMod(Vector push);
}
