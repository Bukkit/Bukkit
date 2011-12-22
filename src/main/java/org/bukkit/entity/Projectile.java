package org.bukkit.entity;

import org.bukkit.block.Dispenser;

/**
 * Represents a shootable entity
 */
public interface Projectile extends Entity {

    /**
     * Retrieve the shooter of this projectile.
     *
     * @return the {@link ProjectileShooter} that shot this projectile
     */
    public ProjectileShooter getShooter();

    /**
     * Set the shooter of this projectile
     *
     * @param shooter the {@link ProjectileShooter} that shot this projectile
     */
    public void setShooter(ProjectileShooter shooter);

    /**
     * Determine if this projectile should bounce or not when it hits.
     *
     * If a small fireball does not bounce it will set the target on fire.
     *
     * @return true if it should bounce.
     */
    public boolean doesBounce();

    /**
     * Set whether or not this projectile should bounce or not when it hits something.
     *
     * @param doesBounce whether or not it should bounce.
     */
    public void setBounce(boolean doesBounce);
}
