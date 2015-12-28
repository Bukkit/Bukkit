package org.bukkit.material;

import org.bukkit.DirtType;
import org.bukkit.Material;

/**
 * Represents the different types of dirt.
 */
public class Dirt extends MaterialData {

    public Dirt() {
        super(Material.DIRT);
    }

    public Dirt(DirtType dirt) {
        this();
        setType(dirt);
    }

    /**
     * @deprecated Magic value
     */
    @Deprecated
    public Dirt(final int type) {
        super(type);
    }

    public Dirt(final Material type) {
        super(type);
    }

    /**
     * @deprecated Magic value
     */
    @Deprecated
    public Dirt(final int type, final byte data) {
        super(type, data);
    }

    /**
     * @deprecated Magic value
     */
    @Deprecated
    public Dirt(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current type of this dirt.
     *
     * @return The type of dirt
     */
    public DirtType getType() {
        return DirtType.getByData(getData());
    }

    /**
     * Sets the type of this dirt.
     *
     * @param type The new type of dirt
     */
    public void setType(DirtType type) {
        setData(type.getData());
    }

    @Override
    public String toString() {
        return getType() + " " + super.toString();
    }

    @Override
    public Dirt clone() {
        return (Dirt) super.clone();
    }
}
