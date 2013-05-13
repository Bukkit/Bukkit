package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a block is destroyed as a result of being burnt by fire.
 * <p>
 * If a Block Burn event is cancelled, the block will not be destroyed as a result of being burnt by fire.
 */
public class BlockBurnEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Block blockFire;

    public BlockBurnEvent(final Block block, final Block blockFire) {
        super(block);
        this.blockFire = blockFire;
        this.cancelled = false;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Get the fire block at the source of the event
     *
     * @return The fire block at the source of the event
     */
    public Block getFireBlock() {
        return blockFire;
    }
}
