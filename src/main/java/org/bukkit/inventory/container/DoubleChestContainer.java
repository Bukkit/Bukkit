package org.bukkit.inventory.container;

import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;

/**
 * 
 * @author Meaglin
 * 
 * Indicates an active DoubleChestContainer.
 * 
 * This container is only used for DoubleChest
 * for SingleChests {@link}ChestContainer
 *  Slot Layout:
 *      0-53 Chest
 *      54-80 Player Inventory
 *      80-89 Player Quickbar
 */

public interface DoubleChestContainer extends Container {

    /**
     * This function returns the inventory representing the whole double chest.
     * 
     * @return The full inventory of this double chest.
     * 
     * @see Inventory
     */
    public Inventory getInventory();
    
    /**
     * This returns the base block of this DoubleChestContainer.
     * usually this is the block the player interacted with.
     * 
     * @return The chest Block
     * 
     * @see Chest
     */
    public Chest getBaseBlock();
    
    /**
     * This returns the second block belonging to this DoubleChestContainer.
     * 
     * @return The chest Block
     * 
     * @see Chest
     */
    public Chest getSecondBlock();
}
