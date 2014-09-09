package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.PrismarineType;

/**
 * Represents the different types of prismarine.
 */
public class Prismarine extends MaterialData {
    public Prismarine() {
        super(Material.PRISMARINE);
    }

    public Prismarine(PrismarineType type) {
        this();
        setType(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Prismarine(final int type) {
        super(type);
    }

    public Prismarine(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Prismarine(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Prismarine(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current type of this prismarine
     *
     * @return PrismarineType of this prismarine
     */
    public PrismarineType getType() {
        return PrismarineType.getByData(getData());
    }

    /**
     * Sets the type of this prismarine
     *
     * @param type New type of this prismarine
     */
    public void setType(PrismarineType type) {
        setData(type.getData());
    }

    @Override
    public String toString() {
        return getType() + " " + super.toString();
    }

    @Override
    public Prismarine clone() {
        return (Prismarine) super.clone();
    }
}
