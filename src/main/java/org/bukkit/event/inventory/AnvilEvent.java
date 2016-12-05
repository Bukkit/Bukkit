package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Called when an ItemStack is sucessfully renamed/repaired/enchanted in an anvil
 */

public class AnvilEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack item;
    private boolean cancelled;
    
    public AnvilEvent(final Block anvil, final ItemStack item) {
        super(anvil);
        this.item = item;
        this.cancelled = false;
    }
    
    /**
     * Gets the ItemStack after anvil
     *
     * @return the ItemStack
     */
    public ItemStack geItem() {
        return this.item;
    }
    
    /**
     * Change the item meta of the new ItemStack
     *
     * @param newItemStack the new ItemStack you want to change
     */
     public void setItemStack(ItemStack newItemStack) {
        this.item = newItemStack;
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
     
}
