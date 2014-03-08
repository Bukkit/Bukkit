package org.bukkit.material;

import org.bukkit.Material;

/**
 * Represents a snow layer block.
 */
public class SnowLayer extends MaterialData {
    public SnowLayer() {
        super(Material.SNOW);
    }

    /**
     *
     * @deprecated magic value
     */
    public SnowLayer(final int type) {
        super(type);
    }

    public SnowLayer(final Material material) {
        super(material);
    }

    /**
     *
     * @deprecated magic value
     */
    public SnowLayer(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated magic value
     */
    public SnowLayer(final Material material, final byte data) {
        super(material, data);
    }

    /**
     * Adds a layer to the current snow layer. If this goes over
     * the max height for a snow layer the current height will
     * not be affected.
     *
     * @return true if a layer was added, false otherwise
     */
    public boolean addLayer() {
        byte data = getData();

        if (data < 7) {
            data++;
            setData(data);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes a layer from the current snow layer. If the resulting
     * height were to go below the first layer, then the block's height
     * will not be affected.
     *
     * @return true if a layer was removed, false otherwise
     */
    public boolean removeLayer() {
        byte data = getData();

        if (data > 0) {
            data--;
            setData(data);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the height of this snow layer
     * @return the height of this snow layer
     */
    public byte getHeight() {
        return getData();
    }

    /**
     * Sets the height of this snow layer
     * @param height the height of the new snow layer
     */
    public void setHeight(int height) {
        if (height < 8) {
            setData((byte) height);
        }
    }

    @Override
    public String toString() {
        return getHeight() + " " + super.toString();
    }

    @Override
    public MaterialData clone() {
        return (SnowLayer) super.clone();
    }
}
