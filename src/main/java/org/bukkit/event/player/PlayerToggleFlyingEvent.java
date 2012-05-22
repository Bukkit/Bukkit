package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a player toggles their flying state
 */
public class PlayerToggleFlyingEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final boolean isFlying;
    private boolean cancel = false;

    public PlayerToggleFlyingEvent(final Player player, final boolean isFlying) {
        super(player);
        this.isFlying = isFlying;
    }

    /**
     * Returns whether the player is now flying or not.
     *
     * @return flying state
     */
    public boolean isFlying() {
        return isFlying;
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