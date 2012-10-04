package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Thrown when a player consumes an item
 */
public class PlayerConsumeEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack item;
    private boolean cancel = false;

    public PlayerConsumeEvent(final Player player, final ItemStack drop) {
        super(player);
        this.item = drop;
    }

    /**
     * Gets the ItemStack the player consumes
     *
     * @return ItemStack the player consumes
     */
    public ItemStack getItemStack() {
        return item;
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
