package org.bukkit.block;

import org.bukkit.inventory.ItemStack;

/**
 * Represents a flower pot.
 */
public interface FlowerPot extends BlockState {

    /**
     * Get the material in the flower pot
     * 
     * @return item ItemStack for the item currently in the flower pot (material is AIR if it is empty).
     */
    ItemStack getContents();

    /**
     * Set the contents of the flower pot
     * 
     * @param itemStack ItemStack of the item to put in the flower pot or null/material as AIR to empty it.
     */
    void setContents(ItemStack itemStack);

}
