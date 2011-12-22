package org.bukkit.inventory.container;

import org.bukkit.inventory.Inventory;

/**
 * 
 * @author Meaglin
 * 
 * Indicates an active ChestContainer.
 * 
 * This container is only used for Single Chest
 * for double chest @see DoubleChestContainer
 *  Slot Layout:
 *      0-26 Chest
 *      27-53 Player Inventory
 *      54-62 Player Quickbar
 */
public interface ChestContainer extends BlockContainer {
    
    /**
     * This functions return the inventory of the chest.
     * 
     * @return the inventory of the chest
     * 
     * @see Inventory
     */
    public Inventory getInventory();
}
