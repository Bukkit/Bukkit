package org.bukkit.inventory;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * An interface to implement for custom inventory windows.
 * 
 * @author Celtic Minstrel
 */
public interface CustomInventory {

    /**
     * Get the number of rows in this inventory window (up to 6, with nine slots per row).
     * 
     * @return The number of rows
     */
    int getRows();

    /**
     * Get the title of this inventory window.
     * 
     * @return The window title
     */
    String getTitle();

    /**
     * Get the maximum stack size of items in this window.
     * 
     * @return The maximum stack size
     */
    int getMaxStack();

    /**
     * Split a stack from this window into two stacks and return one of them.
     * 
     * @param slot The slot to split
     * @param amount How much of the item to return
     * @return An item stack
     */
    ItemStack takeSomeFrom(int slot, int amount);

    /**
     * Set one of the items in this window.
     * 
     * @param slot The slot to set
     * @param stack The new item stack
     */
    void setItem(int slot, ItemStack stack);

    /**
     * Get one of the items in this window.
     * 
     * @param slot The slot to fetch from
     * @return The item in the slot
     */
    ItemStack getItem(int slot);

    /**
     * Check whether the object that this window belongs to is still available.
     * For example, if the object no longer exists, it would return false.
     * 
     * @return Whether the object is available
     */
    boolean isAvailable();

    /**
     * Get the location of the object that this window belongs to.
     * 
     * @return The location
     */
    Location getLocation();

    /**
     * Get the range from which the player can use the object.
     * 
     * @return The range
     */
    double getUseRange();


}
