package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.BlockFace;

/**
 * Represents the different types of Logs. This class only supports GENERIC/REDWOOD/BIRCH/JUNGLE Species.
 */
public class Log extends MaterialData {
    public Log() {
        super(Material.LOG);
    }

    public Log(TreeSpecies species) {
        this();
        setSpecies(species);
    }

    public Log(TreeSpecies species, BlockFace dir) {
        this();
        setSpecies(species);
        setDirection(dir);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Log(final int type) {
        super(type);
    }

    public Log(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Log(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Log(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current species of this tree
     *
     * @return TreeSpecies of this tree
     */
    public TreeSpecies getSpecies() {
        return TreeSpecies.getByData((byte) (getData() & 0x3));
    }

    /**
     * Sets the species of this tree
     *
     * @param species New species of this tree
     */
    public void setSpecies(TreeSpecies species) {
        switch (species) {
        case GENERIC:
        case REDWOOD:
        case BIRCH:
        case JUNGLE:
            setData((byte) ((getData() & 0xC) | (species.getData() & 0x3)));
            break;
        default:
            throw new IllegalArgumentException("TreeSpecies " + species + " is not supported by " + getItemType());
        }
    }

    /**
     * Get direction of the log
     *
     * @return BlockFace.TOP for upright (default), BlockFace.NORTH (east-west), BlockFace.WEST (north-south), BlockFace.SELF (directionless)
     */
    public BlockFace getDirection() {
        switch ((getData() >> 2) & 0x3) {
            case 0: // Up-down
            default:
                return BlockFace.UP;
            case 1: // North-south
                return BlockFace.WEST;
            case 2: // East-west
                return BlockFace.NORTH;
            case 3: // Directionless (bark on all sides)
                return BlockFace.SELF;
        }
    }
    /**
     * Set direction of the log
     *
     * @param dir - direction of end of log (BlockFace.SELF for no direction)
     */
    public void setDirection(BlockFace dir) {
        int dat;
        switch (dir) {
            case UP:
            case DOWN:
            default:
                dat = 0;
                break;
            case WEST:
            case EAST:
                dat = 1;
                break;
            case NORTH:
            case SOUTH:
                dat = 2;
                break;
            case SELF:
                dat = 3;
                break;
        }
        setData((byte) ((getData() & 0x3) | (dat << 2)));
    }

    @Override
    public String toString() {
        return getSpecies() + " " + getDirection() + " " + super.toString();
    }

    @Override
    public Log clone() {
        return (Log) super.clone();
    }
}
