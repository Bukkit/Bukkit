package org.bukkit.entity;

import org.bukkit.util.Vector;

public interface ProjectileShooter {
    /**
     * Fires a projectile from this projectile shooter.
     * @param <E> The projectile class
     * @param clazz The projectile class
     * @return The fired projectile.
     */
    <E extends Projectile> E fireProjectile(Class<E> clazz);
}
