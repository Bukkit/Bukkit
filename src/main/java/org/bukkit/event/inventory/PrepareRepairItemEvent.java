package org.bukkit.event.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * Called when:
 * <ol>
 * <li>A player places an item in the secondary slot, and there's already an item in the primary slot.</li>
 * <li>A player changes the name to be assigned to the item, and there's an item in the primary slot.</li>
 * <li>A player change the name to be assigned to the item, and there's an item in both the primary and secondary slots.</li>
 * </ol>
 * Not called when:
 * <ol>
 * <li>A player changes the name to be assigned to the item to nothing.</li>
 * <li>A player changes the name to be assigned to the item to what it previously was.</li>
 * <li>The item in the primary slot cannot be repaired with the item in the secondary slot.</li>
 * <li>The item in the primary slot doesn't need to be repaired, and the player hasn't changed the name to be assigned to the item.</li>
 * </ol>
 */
public class PrepareRepairItemEvent extends InventoryEvent{

    private static final HandlerList handlers = new HandlerList();
    private final int expCost;
    private final AnvilInventory inventory;
    private final String originalName;
    private final String materialName;
    private String name;
    private ItemStack result;
    private int itemCost;
    
    public PrepareRepairItemEvent(AnvilInventory inventory, InventoryView transaction, String name, String materialName, ItemStack result, int expCost, int itemCost) {
        super(transaction);
        this.inventory = inventory;
        this.name = name;
        originalName = name;
        this.materialName = materialName;
        this.result = result;
        this.expCost = expCost;
        this.itemCost = itemCost;
    }
    
    /**
     * Gets the name of the primary item's material.
     * 
     * @return the name of the primary item's material.
     */
    public String getMaterialName() {
        return materialName;
    }
    
    /**
     * Gets the unmodified name of the result as specified by the player.
     * 
     * @return unmodified name of the result.
     */
    public String getOriginalName() {
        return originalName;
    }
    
    /**
     * Sets the number of items to be removed from the secondary ItemStack.
     * 
     * @param newItemCost the number of items to be removed from the secondary ItemStack
     */
    public void setItemCost(int newItemCost) {
        itemCost = newItemCost;
    }
    
    /**
     * Gets the number of items removed from the secondary ItemStack.
     * By default this is 1.
     * 
     * @return the number of items to be removed from the secondary ItemStack
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
     * Gets the String that will be set as the name of the result.
     * This can be modified by other plugins.  Use {@link #getOriginalName() getOriginalName()} for an unmodified version.
     * 
     * @return the result's name.
     */
    public String getItemName() {
        return name;
    }
    
    /**
     * Sets the name of the result, even if the result has been changed.
     * 
     * @param newName the new name for the result.
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
