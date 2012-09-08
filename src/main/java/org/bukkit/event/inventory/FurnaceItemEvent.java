package org.bukkit.event.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * Called when an ItemStack is removed from the furnace result slot.
 */
public class FurnaceItemEvent extends InventoryEvent {

    private static final HandlerList handlers = new HandlerList();
    private ItemStack result;
    private int exp;

    public FurnaceItemEvent(ItemStack result, InventoryView what, final int exp) {
        super(what);
        this.result = result;
        this.exp = exp;
    }

    /**
     * Get the experience to drop after the items are taken from furnace.
     *
     * @return The experience to drop
     */
    public int getExpToDrop() {
        return exp;
    }

    /**
     * Sets the amount of experience to drop after the items are taken from furnace.
     *
     * @param exp 1 or higher to drop experience, or else nothing will drop
     */
    public void setExpToDrop(int exp) {
        this.exp = exp;
    }
    
    /**
     * Gets the resultant ItemStack for this event
     *
     * @return smelting result ItemStack
     */
    public ItemStack getResult() {
        return result;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public CraftingInventory getInventory() {
        return (CraftingInventory) super.getInventory();
    }
}
