package org.bukkit.block;

import org.bukkit.material.MaterialData;

/**
 * Represents a flower pot.
 */
public interface FlowerPot extends BlockState {

    /**
     * Get the material in the flower pot
     * 
     * @return item MaterialData for the item currently in the flower pot (material is AIR if it is empty).
     */
    MaterialData getContents();

    /**
     * Set the contents of the flower pot
     * 
     * @param materialData MaterialData of the item to put in the flower pot or null/material as AIR to empty it.
     */
    void setContents(MaterialData materialData);

}
