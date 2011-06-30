package org.bukkit.event.entity;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;

/**
 * Called when an entity is damaged by a projectile
 */
public class EntityDamageByProjectileEvent extends EntityDamageByEntityEvent {

    private Projectile projectile;
    private boolean bounce;

    public EntityDamageByProjectileEvent(Entity damagee, Projectile projectile, DamageCause cause, int damage) {
        this(projectile.getShooter(), damagee, projectile, cause, damage);
    }

    public EntityDamageByProjectileEvent(Entity damager, Entity damagee, Projectile projectile, DamageCause cause, int damage) {
        super(damager, damagee, cause, damage);
        this.projectile = projectile;
        Random random = new Random();

        this.bounce = random.nextBoolean();
    }

    /**
     * The projectile used to cause the event
     *
     * @return the projectile
     */
    public Projectile getProjectile() {
        return projectile;
    }

    public void setBounce(boolean bounce) {
        this.bounce = bounce;
    }

    public boolean getBounce() {
        return bounce;
    }
}
