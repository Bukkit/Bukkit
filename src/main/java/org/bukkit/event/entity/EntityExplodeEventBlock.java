package org.bukkit.event.entity;

import org.bukkit.block.Block;

/**
 * Class used by EntityExplodeEvent to store per-block settings
 * 
 * @see org.bukkit.event.entity.EntityExplodeEvent
 */
public class EntityExplodeEventBlock { 
    private final Block block;
    private boolean destroyable;
    private boolean dropable;
    private float yield;

    public EntityExplodeEventBlock(final Block block) {
        this.block = block;
        this.destroyable = true;
        this.dropable = true;
        this.yield = -1.0F;
    }

    /**
     * Returns the actual block associated with the explosion event
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Returns if this block should be destroyed during the explosion
     */
    public boolean isDestroyable() {
        return destroyable;
    }

    /**
     * Sets if this block should be destroyed during the explosion
     * 
     * @param destroyable True if this block should be destroyed
     */
    public void setDestroyable(boolean destroyable) {
        this.destroyable = destroyable;
    }

    /**
     * Returns if this block should drop an item after the explosion
     */
    public boolean isDropable() {
        return dropable;
    }

    /**
     * Sets if this block should drop an item after the explosion
     * 
     * @param dropable True if this block should drop an item
     */
    public void setDropable(boolean dropable) {
        this.dropable = dropable;
    }

    /**
     * Returns the chance this block drops an item after the explosion
     */
    public float getYield() {
        return yield;
    }

    /**
     * Sets the chance this block drops and item after the explosion
     * 
     * When set to a negative value, the yield-value of the associated 
     * explosion event will be used instead.
     * 
     * @param yield Value between 0.0F and 1.0F
     */
    public void setYield(float yield) {
        this.yield = yield;
    }
}
