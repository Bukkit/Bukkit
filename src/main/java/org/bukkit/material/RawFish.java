package org.bukkit.material;

import org.bukkit.FishSpecies;
import org.bukkit.Material;

/**
 * Represents the different types of Raw Fish.
 */
public class RawFish extends MaterialData {
    public RawFish() {
        super(Material.RAW_FISH);
    }

    public RawFish(FishSpecies species) {
        this();
        setSpecies(species);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public RawFish(final int type) {
        super(type);
    }

    public RawFish(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public RawFish(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public RawFish(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current species of this fish
     *
     * @return FishSpecies of this fish
     */
    public FishSpecies getSpecies() {
        return FishSpecies.getByData(getData());
    }

    /**
     * Sets the species of this fish
     *
     * @param species New species of this fish
     */
    public void setSpecies(FishSpecies species) {
        setData(species.getData());
    }

    @Override
    public String toString() {
        return getSpecies() + " " + super.toString();
    }

    @Override
    public RawFish clone() {
        return (RawFish) super.clone();
    }
}
