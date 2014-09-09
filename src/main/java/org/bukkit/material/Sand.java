package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.SandType;
import org.bukkit.SandstoneType;

/**
 * Represents the different types of sand.
 */
public class Sand extends MaterialData {
    public Sand() {
        super(Material.SAND);
    }

    public Sand(SandType type) {
        this();
        setType(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Sand(final int type) {
        super(type);
    }

    public Sand(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Sand(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Sand(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current type of this sand
     *
     * @return SandType of this sand
     */
    public SandType getType() {
        return SandType.getByData(getData());
    }

    /**
     * Sets the type of this sand
     *
     * @param type New type of this sand
     */
    public void setType(SandType type) {
        setData(type.getData());
    }

    @Override
    public String toString() {
        return getType() + " " + super.toString();
    }

    @Override
    public Sand clone() {
        return (Sand) super.clone();
    }
}
