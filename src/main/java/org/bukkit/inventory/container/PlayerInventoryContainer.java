package org.bukkit.inventory.container;

import org.bukkit.inventory.Inventory;

/**
 * 
 * @author Meaglin
 * 
 * Indicates an active PlayerInventoryContainer
 * 
 * This container just represents the 2x2 crafting square and the result slot belonging to the inventory "screen"
 * the rest is already container within @see {@link org.bukkit.entity.HumanEntity#getInventory()}
 *  Slot Layout:
 *      0 Crafting result
 *      1-4 Crafting square (1 2;3 4)
 *      5-8 Player Armor (5;6;7;8)
 *      9-35 Player Inventory
 *      36-44 Player Quickbar
 */
public interface PlayerInventoryContainer extends Container {
    
    /**
     * This functions return an inventory representing the 3x3 crafting square of this Container.
     * 
     * 
     * @return the inventory representing the crafting square
     * 
     * @see Inventory
     */
    public Inventory getCraftingInventoy();
    
    /**
     * This function returns a inventory with size 1 representing the result slot of this container
     * 
     * @return the inventory of the result slot
     * 
     * @see Inventory
     */
    public Inventory getResultInventory();
}
