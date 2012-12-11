package org.bukkit.entity;

/**
 * Represents a Witch
 */
public interface Witch extends Monster {
    /**
     * Gets whether the witch is aggressive towards the player.
     * 
     * @return The witch's aggressiveness.
     */
    public boolean isAggressive();
    
    /**
     * Sets whether the witch is aggressive towards the player.
     * 
     * @param Whether the witch should be aggressive or not.
     */
    public void setAggressive(boolean aggressive);
}
