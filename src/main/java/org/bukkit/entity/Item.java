/**
 * 
 */
package org.bukkit.entity;

import org.bukkit.inventory.ItemStack;

/**
 * Represents an Item.
 * 
 * @author Cogito
 *
 */
public interface Item extends Entity {
    /**
     * Gets the item stack contained in this ItemDrop
     *
     * @return ItemStack of the contents of this drop
     */
    public ItemStack getItemStack();


    /**
     * sets the item stack contained in this ItemDrop
     *
     * @param items New contents of this drop
     */
    public void setItemStack(ItemStack items);
}
