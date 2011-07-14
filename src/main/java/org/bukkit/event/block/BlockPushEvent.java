package org.bukkit.event.block;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Cancellable;

/**
 * Holds information for events with blocks that are pushed by a piston
 */
public class BlockPushEvent extends BlockEvent implements Cancellable {

    protected BlockFace face;
    protected Block endBlock;
    protected boolean cancel;
    protected List<Block> pushedBlocks;

    public BlockPushEvent(final Block block, final Block endBlock, final BlockFace face) {
        super(Type.BLOCK_PUSH, block);
        this.face = face;
        this.endBlock = endBlock;
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
        if (pushedBlocks == null) {
            pushedBlocks = new ArrayList<Block>();
            Block tempblock = block.getRelative(face.getModX(), face.getModY(), face.getModZ());
            while (tempblock != endBlock) {
                pushedBlocks.add(tempblock);
                tempblock = tempblock.getRelative(face.getModX(), face.getModY(), face.getModZ());
            }
        }
        return pushedBlocks;
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
