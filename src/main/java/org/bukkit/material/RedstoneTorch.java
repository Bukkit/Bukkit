
package org.bukkit.material;

import org.bukkit.Material;

/**
 * Represents a redstone torch
 */
public class RedstoneTorch extends Torch implements Redstone {
    public RedstoneTorch() {
        super(Material.REDSTONE_TORCH_ON);
    }
    
    public RedstoneTorch(final int type) {
        super(type);
    }

    public RedstoneTorch(final Material type) {
        super(type);
    }

    public RedstoneTorch(final int type, final byte data) {
        super(type, data);
    }

    public RedstoneTorch(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current state of this Material, indicating if it's powered or
     * unpowered
     *
     * @return true if powered, otherwise false
     */
    public boolean isPowered() {
        return getItemType() == Material.REDSTONE_TORCH_ON;
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + (isPowered() ? "" : "NOT ") + "POWERED";
    }
}
