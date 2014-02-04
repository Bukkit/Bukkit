package org.bukkit.block;

import org.bukkit.inventory.ItemStack;

/**
 * Represents a flower pot.
 */
public interface FlowerPot extends BlockState {

    /**
     * Get the material in the flower pot
     * 
     * @return item ItemStack for the block currently in the flower pot.
     */
    ItemStack getContents();

    /**
     * Set the contents of the flower pot
     * 
     * @param itemStack ItemStack of the block to put in the flower pot.
     */
    void setContents(ItemStack itemStack);

}
