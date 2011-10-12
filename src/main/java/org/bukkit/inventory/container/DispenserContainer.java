package org.bukkit.inventory.container;

import org.bukkit.inventory.Inventory;

/**
 * 
 * @author Meaglin
 *
 * Indicates an active DispenserContainer.
 *  Slot Layout:
 *      0-8 Dispenser square (0 1 2;3 4 5;6 7 8)
 *      9-35 Player Inventory
 *      36-44 Player Quickbar
 */
public interface DispenserContainer extends BlockContainer {
    
    /**
     * This function returns a 3x3 Inventory containing the contents of this dispenser.
     * 
     * @return the inventory this container represents
     * 
     * @see Inventory
     */
    public Inventory getInventory();
}
