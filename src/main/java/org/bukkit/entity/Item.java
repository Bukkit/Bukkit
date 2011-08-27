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
     * @return
     */
    public ItemStack getItemStack();

    /**
     * Sets the item stack associated with this item drop.
     *
     * @param stack
     */
    public void setItemStack(ItemStack stack);

    /**
     * Gets the pickup delay associated with this item drop.
     *
     * @return
     */
    public int getPickupDelay();

    /**
     * Sets the pickup delay associated with this item drop.
     *
     * @param delay
     */
    public void setPickupDelay(int delay);
}