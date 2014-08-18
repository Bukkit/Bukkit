package org.bukkit.event.entity;

import org.bukkit.entity.Item;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when an item has found another item close enough and compatible
 * enough to merge with. Either item could cease to exist after the event.
 */
public class ItemMergeEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Item other;
    private boolean cancelled;

    public ItemMergeEvent(final Item initiator, final Item other) {
        super(initiator);
        this.other = other;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public Item getEntity() {
        return (Item) entity;
    }

    /**
     * Gets the item that was found close enough to {@link #getEntity()} to
     * merge with.
     *
     * @return Item found close enough to merge with
     */
    public Item getOther() {
        return other;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
