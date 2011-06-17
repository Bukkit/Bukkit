package org.bukkit.event.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;

/**
 * Called on mushroom spread
 */
public class MushroomSpreadEvent extends BlockEvent implements Cancellable {
    private Material material;
    private Block to;
    private Block from;
    private byte data;
    private boolean cancel;

    public MushroomSpreadEvent(Block block) {
        super(Type.MUSHROOM_SPREAD, block);
        this.to = block;
        this.material = block.getType();
        this.cancel = false;
    }

    /**
     * Gets the material being placed on a block during a mushroom spread
     *
     * @return the material being placed by a mushroom spread
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material to be placed on a block during the mushroomspread
     *
     * @param material the material to be placed
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Gets the block data of a block involved in a mushroomspread
     *
     * @return the data of the block being placed
     */
    public byte getData() {
        return data;
    }

    /**
     * Sets the block data of a block involved in a mushroomspread
     *
     * @param data
     */
    public void setData(byte data) {
        this.data = data;
    }
    
    /**
     * Sets the new mushroom location
     *
     * @param Block
     */
    public void setTo(Block block) {
        this.to = block;
    }
    
    /**
     * Gets the block where it tries to spawn a new mushroom
     * 
     * @return the block
     */
    public Block getTo() {
        return to;
    }
    
    /**
     * Sets the original mushroom location
     *
     * @param Block
     */
    public void setFrom(Block block) {
        this.from = block;
    }
    
    /**
     * Gets the block where it tries to spawn a new mushroom
     * 
     * @return the block
     */
    public Block getFrom() {
        return from;
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
     * @param cancel true if you wish to cancel mushrooms spreading
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
