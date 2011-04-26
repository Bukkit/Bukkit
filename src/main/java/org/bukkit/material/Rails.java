package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Represents minecart rails.
 */
public class Rails extends MaterialData {

    public Rails() {
        super(Material.RAILS);
    }

    public Rails(final int type) {
        super(type);
    }

    public Rails(final Material type) {
        super(type);
    }

    public Rails(final int type, final byte data) {
        super(type, data);
    }

    public Rails(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * @return the whether this track is set on a slope
     */
    public boolean isOnSlope() {
        byte d = getConvertedData();
        return (d == 0x2 || d == 0x3 || d == 0x4 || d == 0x5);
    }

    /**
     * @return the whether this track is set as a curve
     */
    public boolean isCurve() {
        byte d = getConvertedData();
        return (d == 0x6 || d == 0x7 || d == 0x8 || d == 0x9);
    }

    /**
     * @return the direction these tracks are set <br>
     *         Note that tracks are bidirectional and that the direction
     *         returned is the ascending direction if the track is set on a
     *         slope. If it is set as a curve, the corner of the track is
     *         returned.
     */
    public BlockFace getDirection() {
        byte d = getConvertedData();
        switch (d) {
        case 0x0:
        default:
            return BlockFace.WEST;
        case 0x1:
            return BlockFace.SOUTH;
        case 0x2:
            return BlockFace.SOUTH;
        case 0x3:
            return BlockFace.NORTH;
        case 0x4:
            return BlockFace.EAST;
        case 0x5:
            return BlockFace.WEST;
        case 0x6:
            return BlockFace.NORTH_EAST;
        case 0x7:
            return BlockFace.SOUTH_EAST;
        case 0x8:
            return BlockFace.SOUTH_WEST;
        case 0x9:
            return BlockFace.NORTH_WEST;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " facing " + getDirection() + (isCurve() ? " on a curve" : (isOnSlope() ? " on a slope" : ""));
    }

    /**
     * Return the data without the extended properties used by {@link PoweredRail} and {@link DetectorRail}. Overridden in {@link ExtendedRails}
     * @return the data without the extended part
     */
    protected byte getConvertedData() {
        return getData();
    }
}
