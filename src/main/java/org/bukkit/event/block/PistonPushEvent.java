package org.bukkit.event.block;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Cancellable;

/**
 * Holds information for events with blocks that are pushed by a piston
 */
public class PistonPushEvent extends BlockEvent implements Cancellable {
    protected BlockFace face;
    protected List<Block> blocks;
    protected boolean cancel;

    public PistonPushEvent(final Block block, final List<Block> blocks, final BlockFace face) {
        super(Type.PISTON_PUSH, block);
        this.face = face;
        this.blocks = blocks;
        this.cancel = false;
    }

    /**
     * Gets the location this block is pushed to
     *
     * @return Block the block is event moving to
     */
    public BlockFace getFace() {
        return face;
    }

    /**
     * Returns the list of blocks that are pushed
     * 
     * @return List of blocks that are getting pushed
     */
    public List<Block> blocklist() {
        return blocks;
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
