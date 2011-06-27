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
    private List<Block> blocks;
    private List<Location> ignoreBlocks;
    private List<Location> ignoreDropBlocks;
    private float yield = 0.3F;

    public EntityExplodeEvent(Entity what, Location location, List<Block> blocks) {
        super(Type.ENTITY_EXPLODE, what);
        this.location = location;
        this.cancel = false;
        this.blocks = blocks;
        this.ignoreBlocks = new ArrayList<Location>();
        this.ignoreDropBlocks = new ArrayList<Location>();
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
     * removed from the explosion event.
     */
    public List<Block> blockList() {
        return blocks;
    }

    /**
     * Returns the list of blocks which should not be destroyed during the
     * explosion event.
     */
    public List<Location> ignoreBlockList() {
        return ignoreBlocks;
    }

    /**
     * Returns the list of blocks which should not drop an item after
     * being destroyed.
     */
    public List<Location> ignoreDropBlockList() {
        return ignoreDropBlocks;
    }

    /**
     * Add a block to the list of blocks which should not be destroyed
     * during the explosion event. 
     * 
     * @param block Block you wish to ignore
     */
    public void addIgnoreBlock(Block block) {
        if(!blocks.contains(block))
            return;

        ignoreBlocks.add(block.getLocation());
    }

    /**
     * Add a block to the list of blocks which should not drop an item
     * after being destroyed.
     * 
     * @param block
     */
    public void addIgnoreDropBlock(Block block) {
        if(!blocks.contains(block))
            return;

        ignoreDropBlocks.add(block.getLocation());
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
     *
     * @return
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
