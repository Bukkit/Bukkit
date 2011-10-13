package org.bukkit.inventory;

import java.util.List;

/**
 * 
 * @author Meaglin
 * Indicates an active Container.
 *
 */
public interface Container {
    
    /**
     * 
     * @param slot
     * @return Slot object for slot
     */
    public Slot getSlot(int slot);
    
    /**
     * 
     * @param slot
     * @return item in the slot
     */
    public ItemStack getItem(int slot);
    
    /**
     * 
     * @return all items in the container
     */
    public List<ItemStack> getItems();
    
    /**
     * 
     * @return the type of the container
     */
    public ContainerType getType();
    
    /**
     * 
     * @return the size of the container
     */
    public int getSize();
    
    /**
     * @return the inventorys of the container the player is interacting with
     */
    public Inventory[] getInventorys();
}
