package org.bukkit.block;

/**
 * Represents a brewing stand within Minecraft.
 * 
 * @author N3X15 <nexis@7chan.org>
 * 
 */
public interface BrewingStand extends BlockState, ContainerBlock {
    
    /**
     * How much time is left in the brewing cycle
     * 
     * @return Brew Time
     */
    int getBrewingTime();
    
    /**
     * Set the time left before brewing completes.
     * 
     * @param brewTime Brewing time
     */
    void setBrewingTime(int brewTime);
}
