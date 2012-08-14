package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Represents the tripwire hook
 */
public class TripwireHook extends SimpleAttachableMaterialData implements Directional, Redstone {
    
    public TripwireHook() {
        super(Material.TRIPWIRE_HOOK);
    }

    public TripwireHook(final int type) {
        super(type);
    }

    public TripwireHook(final int type, final byte data) {
        super(type, data);
    }
    
    public TripwireHook(BlockFace dir) {
        this();
        setFacingDirection(dir);
    }
    
    /**
     * Test if tripwire is connected
     * @return true if connected, false if not
     */
    public boolean isConnected() {
        return (getData() & 0x4) != 0;
    }
    
    /**
     * Set tripwire connection state
     * @param connected - true if connected, false if not
     */
    public void setConnected(boolean connected) {
        int dat = getData() & (0x8 | 0x3);
        if (connected) {
            dat |= 0x4;
        }
        setData((byte) dat);
    }

    /**
     * Test if hook is currently activated
     * @return true if activated, false if not
     */
    public boolean isActivated() {
        return (getData() & 0x8) != 0;
    }
    
    /**
     * Set hook activated state
     * @param act - true if activated, false if not
     */
    public void setActivated(boolean act) {
        int dat = getData() & (0x4 | 0x3);
        if (act) {
            dat |= 0x8;
        }
        setData((byte) dat);
    }
    
    public void setFacingDirection(BlockFace face) {
        int dat = getData() & 0xC;
        switch (face) {
            case EAST:
                break;
            case SOUTH:
                dat |= 0x1;
                break;
            case WEST:
                dat |= 0x2;
                break;
            case NORTH:
                dat |= 0x3;
                break;
            default:
                break;
        }
        setData((byte) dat);
    }

    public BlockFace getFacing() {
        switch (getData() & 0x3) {
            case 0:
                return BlockFace.EAST;
            case 1:
                return BlockFace.SOUTH;
            case 2:
                return BlockFace.WEST;
            case 3:
                return BlockFace.NORTH;
        }
        return null;
    }
    
    public boolean isPowered() {
        return isActivated();
    }

    @Override
    public TripwireHook clone() {
        return (TripwireHook) super.clone();
    }

    @Override
    public String toString() {
        return super.toString() + " facing " + getFacing() + (isActivated()?" Activated":"") + (isConnected()?" Connected":"");
    }

    /**
     * Gets the face that this block is attached on
     *
     * @return BlockFace attached to
     */
    public BlockFace getAttachedFace() {
        byte data = getData();

        switch (data) {
        case 0x1:
            return BlockFace.SOUTH;

        case 0x2:
            return BlockFace.WEST;

        case 0x3:
            return BlockFace.NORTH;

        case 0x0:
            return BlockFace.EAST;
        }

        return null;
    }
    
}
