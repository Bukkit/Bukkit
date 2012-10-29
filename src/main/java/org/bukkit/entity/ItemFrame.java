package org.bukkit.entity;

import org.bukkit.Material;

/**
 * Represents an Item Frame
 */
public interface ItemFrame extends Hanging {
    /**
     * An enum to specify the rotation of the ItemFrame's item
     */
    public enum Rotation {
        /**
         * No rotation
         */
        NONE,
        /**
         * Rotated clockwise
         */
        CLOCKWISE,
        /**
         * Flipped upside-down
         */
        FLIPPED,
        /**
         * Rotated counter-clockwise
         */
        COUNTER_CLOCKWISE,
    }

    /**
     * Get the item in this frame
     *
     * @return The item
     */
    public Material getItem();

    /**
     * Set the item in this frame
     *
     * @param item The new item
     */
    public void setItem(Material item);

    /**
     * Get the rotation of the frame's item
     *
     * @return The direction
     */
    public Rotation getRotation();

    /**
     * Set the rotation of the frame's item
     *
     * @param rotation The new rotation
     */
    public void setRotation(Rotation rotation);

    /**
     * Rotate the frame's item as if it were being right-clicked
     *
     * @return The new rotation
     */
    public Rotation rotate();
}
