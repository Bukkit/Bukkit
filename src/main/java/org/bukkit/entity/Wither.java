package org.bukkit.entity;

import org.bukkit.Location;

/**
 * Represents a Wither boss
 */
public interface Wither extends Monster {

    /**
     * Gets the current target of a wither head.
     *
     * @param head which head to check the target of
     * @return the current target of a wither head, or null if none
     */
    public LivingEntity getTarget(WitherHead head);

    /**
     * Sets the current target of a wither head.
     *
     * @param head which head to check the target of
     * @param entity the entity to set as the target
     */
    public void setTarget(WitherHead head, LivingEntity entity);

    /**
     * Fires a wither skull from a wither head.
     *
     * @param head which head to fire from
     * @param entity the entity to fire at
     */
    public void shoot(WitherHead head, LivingEntity entity);

    /**
     * Fires a wither skull from a wither head.
     *
     * @param head which head to fire from
     * @param location the location to fire at
     */
    public void shoot(WitherHead head, Location location);

    /**
     * An enum to specify which head of the wither
     */
    public enum WitherHead {

        /**
         * The Left head in respect to a frontal view
         */
        LEFT,

        /**
         * The Center head in respect to a frontal view
         */
        CENTER,

        /**
         * The Right head in respect to a frontal view
         */
        RIGHT,
    }
}
