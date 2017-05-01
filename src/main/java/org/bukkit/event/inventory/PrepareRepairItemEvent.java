package org.bukkit.event.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * Called whenever either the items in it or the name of the item to be created are changed.
 */
public class PrepareRepairItemEvent extends InventoryEvent{

    private static final HandlerList handlers = new HandlerList();
    private final int expCost;
    private AnvilInventory inventory;
    private String name;
    private ItemStack result;
    private int itemCost;
    
    public PrepareRepairItemEvent(AnvilInventory inventory, InventoryView transaction, String name, ItemStack result, int expCost, int itemCost) {
        super(transaction);
        this.inventory = inventory;
        this.name = name;
        this.result = result;
        this.expCost = expCost;
        this.itemCost = itemCost;
    }
    
    /**
     * Sets the number of items to be removed from the secondary ItemStack.
     * 
     * @param newItemCost
     */
    public void setItemCost(int newItemCost) {
        itemCost = newItemCost;
    }
    
    /**
     * Gets the number of items removed from the ItemStack to be spent.
     * By default this is 1.
     * 
     * @return
     */
    public int getItemCost() {
        return itemCost;
    }
    
    /**
     * Gets the Inventory of the anvil.  
     * It has two slots(0, 1), which are the primary and secondary ItemStacks, respectively.
     *
     * @return the Inventory of the anvil.
     */
    public AnvilInventory getInventory() {
        return inventory;
    }
    
    /**
     * Gets the ItemStack that will have items from it repaired.
     * 
     * @return the ItemStack that will have items from it repaired.
     */
    public ItemStack getPrimaryItemStack() {
        return inventory.getItem(0);
    }
    
    /**
     * Gets the ItemStack that will lose items as a result of a repair.
     * 
     * @return the ItemStack which will lose items as a result of a repair.
     */
    public ItemStack getSecondaryItemStack() {
        return inventory.getItem(1);
    }
    
    /**
     * Gets the title of the result as specified by the player repairing it.
     * 
     * @return the result's title.
     */
    public String getItemName() {
        return name;
    }
    
    /**
     * Sets the title of the result, even if the result has been changed.
     * 
     * @param newName the new title for the result.
     */
    public void setItemName(String newName) {
        name = newName;
    }
    
    /**
     * Gets the result of the repair/rename.
     * 
     * @return the result of the repair/rename.
     */
    public ItemStack getResult() {
        return result;
    }
    
    /**
     * Sets of the result of the repair/rename.
     * 
     * @param newResult The new result of the repair/rename.
     */
    public void setResult(ItemStack newResult) {
        result = newResult;
    }
    
    /**
     * Get cost in Exp Levels of the repair/rename.
     * Note: This value cannot be changed, because the client does the calculation itself.
     * 
     * @return experience level cost
     */
    public int getExpLevelCost() {
        return expCost;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
