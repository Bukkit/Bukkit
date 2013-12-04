package org.bukkit.event.hanging;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class HangingFrameRemoveItemEvent extends HangingEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private ItemStack item;
    private Entity entity;

    public HangingFrameRemoveItemEvent(Hanging itemFrame, Entity remover, ItemStack item) {
        super(itemFrame);
        cancelled = false;
        this.item = item;
        this.entity = remover;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    /**
     * Gets the item being dropped from the item frame
     *
     * @return The ItemStack being dropped from the frame.
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * Gets the entity responsible for causing the item to be removed.
     *
     * @return The entity that caused the item to drop from the frame.
     */
    public Entity getRemover() {
        return entity;
    }
}
