package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a TNT is ignited, leading to the creation of a primed TNT.
 * <p />
 * If a TNTIgniteEvent is cancelled, the primed TNT will not be spawned.
 */
public class TNTIgniteEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final TNTPrimed tntPrimed;
    private boolean cancelled;

    public TNTIgniteEvent(final Block block, final TNTPrimed entity) {
        super(block);
        this.tntPrimed = entity;
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
    
    public TNTPrimed getEntity() {
        return tntPrimed;
    }
}
