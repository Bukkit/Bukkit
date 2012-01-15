package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a block is destroyed as a result of being burnt by fire.
 * <p />
 * If a Block Burn event is cancelled, the block will not be destroyed as a result of being burnt by fire.
 */
@SuppressWarnings("serial")
public class BlockBurnEvent extends BlockEvent implements Cancellable {
    private boolean cancelled;

    public BlockBurnEvent(Block block) {
        super(Type.BLOCK_BURN, block);
        this.cancelled = false;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
    
    private static final HandlerList handlers = new HandlerList();
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
