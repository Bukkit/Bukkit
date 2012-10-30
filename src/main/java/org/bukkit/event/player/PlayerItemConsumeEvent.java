package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Fired when a player consumes an item (such as a potion or bread).
 */
public class PlayerItemConsumeEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack consumedItem;
    private boolean cancel = false;
    
    public PlayerItemConsumeEvent(final Player player, final ItemStack consumedItem) {
        super(player);
        this.consumedItem = consumedItem;
    }

    /**
     * Gets the item that the player tried to consume
     *
     * @return The consumed item
     */
    public ItemStack getConsumedItem() {
        return consumedItem.clone();
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
