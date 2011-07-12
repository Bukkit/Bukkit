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
     * @return The item stack associated with this item drop.
     */
    public ItemStack getItemStack();

    /**
     * Sets the item stack associated with this item drop.
     *
     * @param stack
     */
    public void setItemStack(ItemStack stack);

    /**
     * Gets the item age.
     *
     * @author robin0van0der0v
     * @return The item age.
     */
    public int getAge();

    /**
     * Sets the item age.
     *
     * @author robin0van0der0v
     * @param age
     */
    public void setAge(int age);
}