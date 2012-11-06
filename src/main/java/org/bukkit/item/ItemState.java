package org.bukkit.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/**
 * Represents a captured state of a item, which will not change automatically.
 * <p />
 * ItemState can exist multiple times for any given Item. 
 * Note that another plugin may change the state of the block and 
 * you will not know, or they may change the block to
 * another type entirely, causing your ItemState to become invalid.
 */
public interface ItemState {

    /**
     * Gets the item represented by this ItemState
     *
     * @return Item that this ItemState represents
     */
    ItemStack getItem();

    /**
     * Gets the metadata for this item
     *
     * @return item specific metadata
     */
    MaterialData getData();

    /**
     * Gets the type of this item
     *
     * @return item type
     */
    Material getType();

    /**
     * Gets the type-id of this item
     *
     * @return item type-id
     */
    int getTypeId();

    /**
     * Sets the metadata for this item
     *
     * @param data New item specific metadata
     */
    void setData(MaterialData data);

    /**
     * Sets the type of this item
     *
     * @param type Material to change this item to
     */
    void setType(Material type);

    /**
     * Sets the type-id of this item
     *
     * @param type Type-Id to change this item to
     * @return Whether it worked?
     */
    void setTypeId(int type);

    /**
     * @return The data as a raw byte.
     */
    public byte getRawData();

    /**
     * @param data The new data value for the item.
     */
    public void setRawData(byte data);
}
