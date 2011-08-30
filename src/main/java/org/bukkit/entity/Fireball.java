package org.bukkit.entity;

import org.bukkit.location.Location;

/**
 * Represents a Fireball.
 */
public interface Fireball extends Projectile, Explosive {
    /**
     * Fireballs fly straight and do not take setVelocity(...) well.
     *
     * @param direction
     *            the direction this fireball is flying toward
     */
    public void setDirection(Location direction);

    /**
     * Retrieve the direction this fireball is heading toward
     *
     * @return the direction
     */
    public Location getDirection();

}
