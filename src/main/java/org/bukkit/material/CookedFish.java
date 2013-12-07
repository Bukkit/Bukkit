package org.bukkit.material;

import org.bukkit.FishSpecies;
import org.bukkit.Material;

/**
 * Represents the different types of Cooked Fish.
 */
public class CookedFish extends MaterialData {
    public CookedFish() {
        super(Material.COOKED_FISH);
    }

    public CookedFish(FishSpecies species) {
        this();
        setSpecies(species);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public CookedFish(final int type) {
        super(type);
    }

    public CookedFish(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public CookedFish(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public CookedFish(final Material type, final byte data) {
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
    public CookedFish clone() {
        return (CookedFish) super.clone();
    }
}
