package org.bukkit.material;

import org.bukkit.GrassSpecies;
import org.bukkit.Material;

/**
 * Represents the different types of long grasses.
 */
public class LongGrass extends MaterialData {
    public LongGrass() {
        super(Material.LOG);
    }

    public LongGrass(GrassSpecies species) {
        this();
        setSpecies(species);
    }

    public LongGrass(final int type) {
        super(type);
    }

    public LongGrass(final Material type) {
        super(type);
    }

    public LongGrass(final int type, final byte data) {
        super(type, data);
    }

    public LongGrass(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current species of this grass
     *
     * @return GrassSpecies of this grass
     */
    public GrassSpecies getSpecies() {
        return GrassSpecies.getByData(getData());
    }

    /**
     * Sets the species of this grass
     *
     * @param species New species of this grass
     */
    public void setSpecies(GrassSpecies species) {
        setData(species.getData());
    }

    @Override
    public String toString() {
        return getSpecies() + " " + super.toString();
    }
}
