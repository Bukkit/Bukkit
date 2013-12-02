package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;

/**
 * Represents the different types of leaves. This class only supports ACACIA/DARK_OAK Species.
 */
public class Leaves2 extends Leaves {
    public Leaves2() {
        super(Material.LEAVES_2);
    }

    public Leaves2(TreeSpecies species) {
        this();
        setSpecies(species);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Leaves2(final int type) {
        super(type);
    }

    public Leaves2(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Leaves2(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Leaves2(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current species of this leaf
     *
     * @return TreeSpecies of this leaf
     */
    @Override
    public TreeSpecies getSpecies() {
        return TreeSpecies.getByData((byte) ((getData() & 0x3) + 4));
    }

    /**
     * Sets the species of this leaf
     *
     * @param species New species of this leaf
     */
    @Override
    public void setSpecies(TreeSpecies species) {
        switch (species) {
        case ACACIA:
        case DARK_OAK:
            setData((byte) ((getData() & 0xC) | (species.getData() - 4)));
            break;
        default:
            throw new IllegalArgumentException("TreeSpecies " + species + " is not supported by " + getItemType());
        }
    }

    @Override
    public String toString() {
        return getSpecies() + " " + super.toString();
    }

    @Override
    public Leaves2 clone() {
        return (Leaves2) super.clone();
    }
}
