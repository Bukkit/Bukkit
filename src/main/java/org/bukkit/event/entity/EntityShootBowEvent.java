package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a LivingEntity shoots a bow firing an arrow
 */
public class EntityShootBowEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack bow;
    private final ItemStack projectileStack;
    private Entity projectile;
    private final float force;
    private boolean cancelled;
    private boolean consumeCancelled;

    @Deprecated
    public EntityShootBowEvent(final LivingEntity shooter, final ItemStack bow, final Projectile projectile, final float force) {
        super(shooter);
        this.bow = bow;
        this.projectile = projectile;
        this.force = force;
        this.projectileStack = null;
    }
    
    public EntityShootBowEvent(final LivingEntity shooter, final ItemStack bow, final ItemStack projectileStack, final Projectile projectile, final float force) {
        super(shooter);
        this.bow = bow;
        this.projectileStack = projectileStack;
        this.projectile = projectile;
        this.force = force;
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) entity;
    }

    /**
     * Gets the bow ItemStack used to fire the arrow; is null if the shooter is a skeleton
     *
     * @return the bow involved in this event, or null
     */
    public ItemStack getBow() {
        return bow;
    }

    /**
     * Gets the projectile ItemStack fired by the bow
     * 
     * @return the projectile ItemStack involved in this event
     */
    public ItemStack getProjectileStack() {
        return projectileStack;
    }

    /**
     * Gets the projectile which will be launched by this event
     *
     * @return the launched projectile
     */
    public Entity getProjectile() {
        return projectile;
    }

    /**
     * Replaces the projectile which will be launched
     *
     * @param projectile the new projectile
     */
    public void setProjectile(Entity projectile) {
        this.projectile = projectile;
    }

    /**
     * Gets the force the arrow was launched with
     *
     * @return bow shooting force, up to 1.0
     */
    public float getForce() {
        return force;
    }

    /**
     * Gets whether the projectile ItemStack will be consumed in this event; this has no effect to a skeleton
     * 
     * @return if the stack is being consumed
     */
    public boolean isConsumingStack() {
        return consumeCancelled;
    }

    /**
     * Set whether the projectile ItemStack will be consumed in this event; this has no effect to a skeleton
     * 
     * @param cancel true if you wish to cancel projectile consumption
     */
    public void setConsumingStack(boolean cancel) {
        consumeCancelled = cancel;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
