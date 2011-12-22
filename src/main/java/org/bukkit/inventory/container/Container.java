package org.bukkit.inventory.container;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Slot;

/**
 * 
 * @author Meaglin
 * Indicates an active Container.
 *  
 * Default Slot Layout
 * Is always from the top left to bottom right 
 * slot = x + y * row size
 *      
 */
public interface Container {
    
    /**
     * This function returns an wrapper for the MinecraftSlot.
     * 
     * @param slot
     * @return Slot object for slot
     */
    public Slot getSlot(int slot);
    
    /**
     * This function returns the itemstack in the specified slot.
     * Can return null if slot is empty.
     * 
     * 
     * @param slot
     * @return item in the slot
     */
    public ItemStack getItem(int slot);
    
    /**
     * This function returns a list of all the items in the container ordered by slotid.
     * Any entry can be null.
     * 
     * @return all items in the container
     */
    public List<ItemStack> getItems();
    
    /**
     * This function returns the amount of slots this container has.
     * 
     * @return the size of the container
     */
    public int getSize();
}
