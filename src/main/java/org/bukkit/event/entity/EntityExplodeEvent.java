package org.bukkit.event.entity;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;

/**
 * Called when an entity explodes
 */
public class EntityExplodeEvent extends EntityEvent implements Cancellable {   
    private boolean cancel;
    private Location location;
    private List<EntityExplodeEventBlock> blocks;
    private float yield = 0.3F;

    public EntityExplodeEvent(Entity what, Location location, List<EntityExplodeEventBlock> blocks) {
        super(Type.ENTITY_EXPLODE, what);
        this.location = location;
        this.cancel = false;
        this.blocks = blocks;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Returns the list of blocks that would have been removed or were
     * removed from the explosion event
     * 
     * You can modify the blocks in the returned list to control how the
     * explosion treats them. You could even add or remove blocks from 
     * the list, although this is not recommended.
     * 
     * @see org.bukkit.event.entity.EntityExplodeEventBlock
     */
    public List<EntityExplodeEventBlock> getBlocks() {
        return blocks;
    }

    /**
     * Returns the list of blocks that would have been removed or were
     * removed from the explosion event
     * 
     * NOTICE: This method is very memory inefficient!
     * 
     * @deprecated Method has been replaced by getBlocks()
     * @see #getBlocks()
     */
    @Deprecated
    public List<Block> blockList() {
        List<Block> list = new ArrayList<Block>();
        for(EntityExplodeEventBlock block : blocks) {
            list.add(block.getBlock());
        }

        return list;
    }

    /**
     * Returns the location where the explosion happened.
     * It is not possible to get this value from the Entity as
     * the Entity no longer exists in the world.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Returns the percentage of blocks to drop from this explosion
     */
    public float getYield() {
        return yield;
    }

    /**
     * Sets the percentage of blocks to drop from this explosion
     */
    public void setYield(float yield) {
        this.yield = yield;
    }
}
