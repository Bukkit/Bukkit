package org.bukkit.inventory.container;

import org.bukkit.inventory.Inventory;

/**
 * 
 * @author Meaglin
 * 
 * Indicates an active WorkbenchContainer.
 *  Slot Layout:
 *      0 Crafting result
 *      1-9 Crafting square (1 2 3;4 5 6;7 8 9)
 *      10-36 Player Inventory
 *      37-45 Player Quickbar
 */
public interface WorkbenchContainer extends BlockContainer {
    
    /**
     * This functions return an inventory representing the 3x3 crafting square of this Container.
     * 
     * 
     * @return the inventory representing the crafting square
     * 
     * @see Inventory
     */
    public Inventory getCraftingInventory();
    
    /**
     * This function returns a inventory with size 1 representing the result slot of this container
     * 
     * @return the inventory of the result slot
     * 
     * @see Inventory
     */
    public Inventory getResultInventory();
}
