package org.bukkit;

/**
 *  Height values for a snow layer block.
 */
public enum SnowLayerHeight {

    /*
     * The first snow layer.
     */
    LAYER_ONE(0x0),

    /*
     * The second snow layer.
     */
    LAYER_TWO(0x1),

    /*
     * The third snow layer.
     */
    LAYER_THREE(0x2),

    /*
     * The fourth snow layer.
     */
    LAYER_FOUR(0x3),

    /*
     * The fifth snow layer.
     */
    LAYER_FIVE(0x4),

    /*
     * The sixth snow layer.
     */
    LAYER_SIX(0x5),

    /*
     * The seventh snow layer.
     */
    LAYER_SEVEN(0x6),

    /*
     * The eighth snow layer.
     */
    LAYER_EIGHT(0x7);

    private byte heightData;

    private SnowLayerHeight(int heightData) {
        this.heightData = (byte) heightData;
    }

    /**
     * Gets the height data for a snow layer
     * @return height data for a snow layer
     */
    public byte getHeightData() {
        return heightData;
    }

    /**
     * Gets the snow layer height from the specified data.
     *
     * @param heightData the snow layer height specified by data
     * @return a SnowLayerHeight value based on the height, or
     *     null if the height value is invalid
     */
    public static SnowLayerHeight fromHeight(byte heightData) {
        for (SnowLayerHeight slh : values()) {
            if (slh.getHeightData() == heightData) {
                return slh;
            }
        }

        return null;
    }
}
