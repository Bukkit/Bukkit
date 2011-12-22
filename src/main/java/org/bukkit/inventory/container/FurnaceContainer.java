package org.bukkit.inventory.container;

import org.bukkit.inventory.Inventory;

/**
 * 
 * @author Meaglin
 * 
 * Indicates an active FurnaceContainer
 *  Slot Layout:
 *      0 Furnace resource
 *      1 Furnace fuel
 *      2 Furnace result
 *      3-29 Player Inventory
 *      30-38 Player Quickbar
 */
public interface FurnaceContainer extends BlockContainer {

    /**
     * This function returns a inventory of size 3.
     * That represents the contents of the furnace.
     * Slots({@link Inventory#getItem(int)} )
     *  0 = resource
     *  1 = fuel
     *  2 = result
     * 
     * @return inventory of the furnace
     * 
     * @see Inventory
     */
    public Inventory getInventory();
}
