package org.bukkit.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * This event is fired whenever a player's bed spawn location is changed.
 */
public class PlayerBedSpawnChangeEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private Block to;

    public PlayerBedSpawnChangeEvent(final Player who, final Block to) {
        super(who);
        this.to = to;
    }

    /**
     * Returns where the bed spawn is changing to.
     *
     * @return where bed spawn will change to
     */
    public Block getTo() {
        return to;
    }

    /**
     * Sets where the bed spawn will change to.
     * 
     * @param to where bed spawn will change to
     */
    public void setTo(Block to) {
        this.to = to;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
