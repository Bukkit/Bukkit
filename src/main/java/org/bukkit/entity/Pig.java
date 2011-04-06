/**
 * 
 */
package org.bukkit.entity;

/**
 * Represents a Pig.
 * 
 * @author Cogito
 *
 */
public interface Pig extends Animals {
    /**
     * @author xPaw
     * @return Whether the pig has saddle on it
     */
    public boolean hasSaddle();
    
    /**
     * @author xPaw
     * @param flag Whether to set saddle on pig
     */
    public void setSaddle(boolean flag);
}