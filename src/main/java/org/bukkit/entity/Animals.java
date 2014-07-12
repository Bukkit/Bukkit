package org.bukkit.entity;

import org.bukkit.entity.Player;

/**
 * Represents an Animal.
 */
public interface Animals extends Ageable {

    /**
     * Determines if this animal is currently breeding.
     * An animal is considered to be breeding when it has been given food (EG: wheat) and is looking for another animal of its species to mate with and produce a baby animal.
     * Will return false again if the breeding attempt timed out or after the mating was successful and a baby is born.
     * Will return true if the entity is actively breeding but hasn't yet had a child.
     *
     * @return true if it is breeding.
     */
    public boolean isBreeding();

    /**
     * Set whether this animal is currently trying to breed.
     * An animal is considered to be breeding when it has been given food (EG: wheat) and is looking for another animal of its species to mate with and produce a baby animal.
     * If 'breeding' is true, this function has the same result as given the entity its breeding food (EG: wheat).
     * If 'breeding' is false, this function will take away any desire to breed from the entity, and even stop an in-progress mating session.
     * Specify a non-null player to indicate that the given player was the one who induced breeding, which will give any built in or plugin created rewards (EG: experience) to that player when the breeding is successful.
     *
     * @param breeding whether the animal should try to breed.
	 * @param player the player that induced the breeding.
     */
    public void setBreeding(boolean breeding, Player player);

    /**
     * Set whether this animal is currently trying to breed.
     * An animal is considered to be breeding when it has been given food (EG: wheat) and is looking for another animal of its species to mate with and produce a baby animal.
     * If 'breeding' is true, this function has the same result as given the entity its breeding food (EG: wheat).
     * If 'breeding' is false, this function will take away any desire to breed from the entity, and even stop an in-progress mating session.
     * This will assume a null player, meaning that no player will be given any rewards (EG: experience) for a successful breeding.
     *
     * @param breeding whether the animal should try to breed.
     */
    public void setBreeding(boolean breeding);

}
