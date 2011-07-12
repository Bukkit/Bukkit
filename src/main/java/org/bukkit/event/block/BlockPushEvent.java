package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Cancellable;

/**
 * Holds information for events with a source block and a destination block
 */
public class BlockPushEvent extends BlockEvent implements Cancellable {
    protected BlockFace face;
    protected boolean cancel;

    public BlockPushEvent(final Block block, final BlockFace face) {
        super(Type.BLOCK_PUSH, block);
        this.face = face;
        this.cancel = false;
    }

    /**
     * Gets the location this block is pushed to
     *
     * @return Block the block is event originated from
     */
    public BlockFace getFace() {
        return face;
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
