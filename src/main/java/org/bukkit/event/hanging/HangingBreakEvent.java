package org.bukkit.event.hanging;

import org.bukkit.entity.Hanging;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Triggered when a hanging is removed
 */
public class HangingBreakEvent extends HangingEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final RemoveCause cause;

    public HangingBreakEvent(final Hanging hanging, final RemoveCause cause) {
        super(hanging);
        this.cause = cause;
    }

    /**
     * Gets the cause for the hanging's removal
     *
     * @return the RemoveCause for the hanging's removal
     */
    public RemoveCause getCause() {
        return cause;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * An enum to specify the cause of the removal
     */
    public enum RemoveCause {
        /**
         * Removed by an entity
         */
        ENTITY,
        /**
         * Removed by fire
         */
        FIRE,
        /**
         * Removed by placing a block on it
         */
        OBSTRUCTION,
        /**
         * Removed by destroying the block behind it, etc
         */
        PHYSICS,
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
