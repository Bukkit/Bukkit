package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.SandType;
import org.bukkit.SpongeType;

/**
 * Represents the different types of sponge.
 */
public class Sponge extends MaterialData {
    public Sponge() {
        super(Material.SPONGE);
    }

    public Sponge(SpongeType type) {
        this();
        setType(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Sponge(final int type) {
        super(type);
    }

    public Sponge(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Sponge(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Sponge(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current type of this sponge
     *
     * @return SpongeType of this sponge
     */
    public SpongeType getType() {
        return SpongeType.getByData(getData());
    }

    /**
     * Sets the type of this sponge
     *
     * @param type New type of this sponge
     */
    public void setType(SpongeType type) {
        setData(type.getData());
    }

    @Override
    public String toString() {
        return getType() + " " + super.toString();
    }

    @Override
    public Sponge clone() {
        return (Sponge) super.clone();
    }
}
