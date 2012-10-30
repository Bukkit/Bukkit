package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Represents a skull.
 */
public class Skull extends MaterialData implements Directional {
    public Skull() {
        super(Material.SKULL);
    }

    /**
     * Instantiate a skull facing in a particular direction.
     *
     * @param direction the direction the skull's face is facing
     */
    public Skull(BlockFace direction) {
        this();
        setFacingDirection(direction);
    }

    public Skull(final int type) {
        super(type);
    }

    public Skull(final Material type) {
        super(type);
    }

    public Skull(final int type, final byte data) {
        super(type, data);
    }

    public Skull(final Material type, final byte data) {
        super(type, data);
    }

    public void setFacingDirection(BlockFace face) {
        int data;

        switch (face) {
            case EAST:
                data = 0x1;
                break;

            case SOUTH:
                data = 0x2;
                break;

            case WEST:
                data = 0x3;
                break;

            case NORTH:
            default:
                data = 0x4;
        }

        setData((byte) (data & 3));
    }

    public BlockFace getFacing() {
        int data = getData() & 7;

        switch (data) {
            case 0x1:
                return BlockFace.EAST;

            case 0x2:
                return BlockFace.SOUTH;

            case 0x3:
                return BlockFace.WEST;

            case 0x4:
            default:
                return BlockFace.SOUTH;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " facing " + getFacing();
    }

    @Override
    public Skull clone() {
        return (Skull) super.clone();
    }
}
