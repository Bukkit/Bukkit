package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a block changes its state to a new one.
 * <p>
 * Examples:
 * <ul>
 * <li>Cauldron loses water when entity extinguish itself in it.
 * <li>Cauldron filling with water due to rain.
 * <li>Anvil gets used after repairing.
 * </ul>
 * <p>
 * If a Block State Change event is cancelled, the block will not change.
 */
public class BlockStateChangeEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final BlockState newState;

    public BlockStateChangeEvent(final Block block, final BlockState newState) {
        super(block);
        this.newState = newState;
        this.cancelled = false;
    }

    /**
     * Gets the new state of the block that was changed.
     * 
     * @return The new block state of the block that was changed
     */
    public BlockState getNewState() {
        return newState;
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
}
