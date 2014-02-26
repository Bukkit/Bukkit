package org.bukkit.material;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.SnowLayerHeight;

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
     * @return the new SnowLayerHeight value if modified, null otherwise
     */
    public SnowLayerHeight addLayer() {
        SnowLayerHeight newHeight = SnowLayerHeight.fromHeight((byte) (getData() + 1));

        // New height is invalid
        if (newHeight == null) {
            return null;
        }

        setData(newHeight.getHeightData());

        return newHeight;
    }

    /**
     * Removes a layer from the current snow layer. If the resulting
     * height were to go below the first layer, then the block's height
     * will not be affected.
     *
     * @return the new SnowLayerHeight value if modified, null otherwise
     */
    public SnowLayerHeight removeLayer() {
        SnowLayerHeight newHeight = SnowLayerHeight.fromHeight((byte) (getData() - 1));

        // New height is invalid
        if (newHeight == null) {
            return null;
        }

        setData(newHeight.getHeightData());

        return newHeight;
    }

    /**
     * Gets the height of this snow layer
     * @return the height of this snow layer
     */
    public SnowLayerHeight getHeight() {
        return SnowLayerHeight.fromHeight(getData());
    }

    /**
     * Sets the height of this snow layer
     * @param height the height of the new snow layer
     */
    public void setHeight(SnowLayerHeight height) {
        setData(height.getHeightData());
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
