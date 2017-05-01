package org.bukkit.entity;

/**
 * A representation of a fused {@link Explosive}.
 */
public interface FusedExplosive extends Explosive {
    /**
     * Set the number of ticks until the entity blows up after being primed.
     *
     * @param fuseTicks The fuse ticks
     */
    public void setFuseTicks(int fuseTicks);

    /**
     * Retrieve the number of ticks until the explosion of this entity.
     *
     * @return the number of ticks until this entity explodes
     */
    public int getFuseTicks();

}
