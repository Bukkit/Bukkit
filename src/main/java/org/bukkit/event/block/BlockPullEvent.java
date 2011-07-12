package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Cancellable;

/**
 * Holds information for events with a block that is being pulled
 */
public class BlockPullEvent extends BlockEvent implements Cancellable {
    protected BlockFace face;
    protected Block source;
    protected boolean cancel;

    public BlockPullEvent(final Block block, final Block source, final BlockFace face) {
        super(Type.BLOCK_PULL, block);
        this.face = face;
        this.source = source;
        this.cancel = false;
    }

    /**
     * Gets the location this block is pulled from
     *
     * @return Block the block is event originated from
     */
    public BlockFace getFace() {
        return face;
    }
    
    /**
     * Gets the source of the blockpull
     *
     * @return the block where event originated from
     */
    public Block getSource(){
        return source;
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
}
