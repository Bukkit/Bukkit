package org.bukkit.material;

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
