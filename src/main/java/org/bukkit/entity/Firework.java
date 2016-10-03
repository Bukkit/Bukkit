package org.bukkit.entity;

import org.bukkit.inventory.meta.FireworkMeta;

public interface Firework extends Entity {

    /**
     * Get a copy of the fireworks meta
     *
     * @return A copy of the current Firework meta
     */
    FireworkMeta getFireworkMeta();

    /**
     * Apply the provided meta to the fireworks
     *
     * @param meta The FireworkMeta to apply
     */
    void setFireworkMeta(FireworkMeta meta);

    /**
     * Cause this firework to explode at earliest opportunity, as if it has no
     * remaining fuse.
     */
    void detonate();
    
    /**
     * get the total number of ticks before the firework will detonate
     *
     * @return number of ticks to live in total
     */
    int getTotalLifetime();
    
    /**
     * set the total number of ticks before the firework will detonate
     *
     * @param ticks number of ticks to live in total
     */
    void setTotalLifetime(int ticks);
    
    /**
     * get the remaining number of ticks before the firework will detonate
     *
     * @return remaining number of ticks to live
     */
    int getRemainingLifetime();
    
    /**
     * set the remaining number of ticks before the firework will detonate
     *
     * @param ticks remaining number of ticks to live
     */
    void setRemainingLifetime(int ticks);
    
}
