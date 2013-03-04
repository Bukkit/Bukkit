package org.bukkit.entity;

import org.bukkit.event.entity.EntityRegainHealthEvent;

/**
 * Represents an {@link Entity} that has health and can take damage.
 */
public interface Damageable extends Entity {
    /**
     * Deals the given amount of damage to this entity.
     *
     * @param amount Amount of damage to deal
     */
    void damage(int amount);

    /**
     * Deals the given amount of damage to this entity, from a specified entity.
     *
     * @param amount Amount of damage to deal
     * @param source Entity which to attribute this damage from
     */
    void damage(int amount, Entity source);

    /**
     * Heals the given amount of damage on this entity.
     * If you want to fully heal an entity, use {@link #setHealth(int)}({@link #getMaxHealth()}) instead.
     * <p>
     * This method will, as a side effect, call a {@link org.bukkit.event.entity.EntityRegainHealthEvent} with {@link org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason#PLUGIN} as the reason.
     * </p>
     * @param amount Amount of damage to heal
     */
    void heal(int amount);

    /**
     * Heals the given amount of damage on this entity.
     * If you want to fully heal an entity, use {@link #setHealth(int)}({@link #getMaxHealth()}) instead.
     * <p>
     * This method will, as a side effect, call a {@link org.bukkit.event.entity.EntityRegainHealthEvent} with the specified reason.
     * </p>
     * @param amount Amount of damage to heal
     */
    void heal(int amount, EntityRegainHealthEvent.RegainReason reason);

    /**
     * Gets the entity's health from 0 to {@link #getMaxHealth()}, where 0 is dead.
     *
     * @return Health represented from 0 to max
     */
    int getHealth();

    /**
     * Sets the entity's health from 0 to {@link #getMaxHealth()}, where 0 is dead.
     *
     * @param health New health represented from 0 to max
     * @throws IllegalArgumentException Thrown if the health is < 0 or > {@link #getMaxHealth()}
     */
    void setHealth(int health);

    /**
     * Gets the maximum health this entity has.
     *
     * @return Maximum health
     */
    int getMaxHealth();

    /**
     * Sets the maximum health this entity can have.
     * <p />
     * If the health of the entity is above the value provided it will be set to that value.
     * <p />
     * Note: An entity with a health bar ({@link Player}, {@link EnderDragon}, {@link Wither}, etc...} will have their bar scaled accordingly.
     *
     * @param health amount of health to set the maximum to
     */
    void setMaxHealth(int health);

    /**
     * Resets the max health to the original amount.
     */
    void resetMaxHealth();
}
