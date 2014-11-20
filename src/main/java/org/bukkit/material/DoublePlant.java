package org.bukkit.material;

import org.bukkit.DoublePlantSpecies;
import org.bukkit.Material;

/**
 * Represents the different types of double plants.
 */
public class DoublePlant extends MaterialData {

    public DoublePlant() {
        super(Material.DOUBLE_PLANT);
    }

    public DoublePlant(DoublePlantSpecies species) {
        this();
        setSpecies(species);
    }

    /**
     * @deprecated Magic value
     */
    @Deprecated
    public DoublePlant(final int type) {
        super(type);
    }

    public DoublePlant(final Material type) {
        super(type);
    }

    /**
     * @deprecated Magic value
     */
    @Deprecated
    public DoublePlant(final int type, final byte data) {
        super(type, data);
    }

    /**
     * @deprecated Magic value
     */
    @Deprecated
    public DoublePlant(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current species of this double plant.
     *
     * @return The DoublePlantSpecies of this double plant
     */
    public DoublePlantSpecies getSpecies() {
        return DoublePlantSpecies.getByData(getData());
    }

    /**
     * Sets the species of this double plant.
     *
     * @param species The new species of this double plant
     */
    public void setSpecies(DoublePlantSpecies species) {
        setData(species.getData());
    }

    @Override
    public String toString() {
        return getSpecies() + " " + super.toString();
    }

    @Override
    public DoublePlant clone() {
        return (DoublePlant) super.clone();
    }
}
