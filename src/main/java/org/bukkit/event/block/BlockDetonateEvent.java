package org.bukkit.event.block;

import org.bukkit.event.Cancellable;
import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

/**
 * Called when a TNT block is detonated (primed).
 * <p />
 * If a Block Detonate event is cancelled, the TNT block will disappear without spawning a primed TNT.
 */
public class BlockDetonateEvent extends BlockEvent implements Cancellable {
    
    private static final HandlerList handlers = new HandlerList();
    private final DetonateReason detonateReason;
    private boolean cancelled = false;

    public BlockDetonateEvent(final Block block, final DetonateReason reason) {
        super(block);
        this.detonateReason = reason;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
    
    /**
     * Gets the reason why the block is detonated.
     * 
     * @return The reason why the block is detonated
     */
    public DetonateReason getReason() {
        return detonateReason;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    /**
     * An enum to specify the detonation reasons
     */
    public enum DetonateReason {
        
        /**
         * When the TNT is activated by redstone
         */
        REDSTONE,
        /**
         * When the TNT is activated by fire spreading
         */
        FIRE,
        /**
         * When a player activates it with flint and steel
         */
        FLINT_AND_STEEL,
        /**
         * When the TNT is activated by a nearby explosion
         */
        EXPLOSION,
        /**
         * When the TNT is activated by a plugin
         */
        CUSTOM,
        /**
         * When the TNT is missing a DetonateReason
         */
        DEFAULT
    }

}