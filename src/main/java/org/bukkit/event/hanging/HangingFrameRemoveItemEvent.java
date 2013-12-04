package org.bukkit.event.hanging;

import org.bukkit.entity.Hanging;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class HangingFrameRemoveItemEvent extends HangingEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private ItemStack item;
    private Player player;

    public HangingFrameRemoveItemEvent(Hanging itemFrame, Player remover, ItemStack item) {
        super(itemFrame);
        cancelled = false;
        this.item = item;
        this.player = remover;
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

    public ItemStack getItem() {
        return item;
    }

    public Player getPlayer() {
        return player;
    }
}
