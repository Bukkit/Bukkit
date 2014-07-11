package org.bukkit.entity;

import org.bukkit.entity.Player;

/**
 * Represents an Animal.
 */
public interface Animals extends Ageable {

    /**
     * Determines if this animal is currently breeding.
     *
     * @return true if it is breeding.
     */
    public boolean isBreeding();

    /**
     * Set whether this animal is currently trying to breed.
     *
     * @param breeding whether the animal should try to breed.
	 * @param player the player that induced the breeding.
     */
    public void setBreeding(boolean breeding, Player player);

    /**
     * Set whether this animal is currently trying to breed.
     *
     * @param breeding whether the animal should try to breed.
     */
    public void setBreeding(boolean breeding);

}
