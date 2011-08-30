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
     * Gets the item stack associated with this item drop.
     *
     * @return An item stack.
     */
    public ItemStack getItemStack();

    /**
     * Sets the item stack associated with this item drop.
     *
     * @param stack An item stack.
     */
    public void setItemStack(ItemStack stack);
}
