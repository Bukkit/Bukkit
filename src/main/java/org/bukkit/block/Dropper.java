package org.bukkit.block;

import org.bukkit.inventory.InventoryHolder;

/**
 * Represents a dropper.
 */
public interface Dropper extends BlockState, InventoryHolder {

    /**
     * Try to drop one of item from one itemstack the dropper contain
     * 
     * Item choose by random function : 1/ContentsSize
     * 
     * Example : 
     * 
     * 1 2 3
     * 4   6
     * 7
     * 
     * Dropper can drop one by one these items : 1 3 2 7 1 2 2 3 4 6 6 ...
     * 
     * If a container is around the dropper, this item go inside (like with a Hopper)
     *
     * @return true if successful drop an item, false if the block is no longer a dropper
     */
	public boolean drop();
}
