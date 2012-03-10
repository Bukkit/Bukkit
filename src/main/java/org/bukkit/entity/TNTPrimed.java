package org.bukkit.entity;

/**
 * Represents a Primed TNT.
 */
public interface TNTPrimed extends Explosive {
    /**
     * Set the number of ticks until the TNT blows up after being primed.
     *
     * @param fuseTicks The fuse ticks
     */
    public void setFuseTicks(int fuseTicks);

    /**
     * Retrieve the number of ticks until the explosion of this TNTPrimed entity
     *
     * @return the number of ticks until this TNTPrimed explodes
     */
    public int getFuseTicks();
    
    //CraftBukkit
    
    /**
     * Retrieve the location of the TNT block from which the TNTPrimed was spawned
     * 
     * @return the location of the TNT block from which the TNTPrimed was spawned
     */
     public Location getOriginalBlock();
}
