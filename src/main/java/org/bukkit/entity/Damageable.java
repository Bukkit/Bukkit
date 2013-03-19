package org.bukkit.entity;

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
     * Heals the given amount of damage on this entity. If the amount given
     * would overheal the entity, the health will be capped at maximum.
     *
     * <p />
     * This method will, as a side effect, call a
     * {@link org.bukkit.event.entity.EntityRegainHealthEvent} with a reason
     * of CUSTOM.
     *
     * @param amount Amount of damage to heal
     * @throws IllegalArgumentException on negative values
     */
    void heal(int amount);

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
