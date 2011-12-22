package org.bukkit.inventory.container;

import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.Inventory;

/**
 * 
 * @author Meaglin
 * Indicates an active StorageMinecartContaienr.
 * 
 *  Slot Layout:
 *      0-26 Minecart Inventory
 *      27-53 Player Inventory
 *      54-62 Player Quickbar
 */
public interface StorageMinecartContainer extends Container{
    
    /**
     * This function returns the inventory of the cart.
     * 
     * @return the inventory this container represents
     * 
     * @see Inventory
     */
    public Inventory getInventory();
    
    /**
     * This function returns the StorageMinecart this container represents.
     * 
     * @return the entity this container represents.
     */
    public StorageMinecart getCart();
}
