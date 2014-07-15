package org.bukkit.event.entity;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when an {@link Animals} breeds with another animal.
 */
public class EntityBreedEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Animals entityTwo;
    private final Ageable baby;
    private Player breeder;
    private boolean cancelled;

    public EntityBreedEvent(final Animals entity, final Animals entityTwo, final Player breeder, final Ageable baby) {
        super(entity);
        this.entityTwo = entityTwo;
        this.breeder = breeder;
        this.baby = baby;
    }

    @Override
    public Animals getEntity() {
        return (Animals) entity;
    }

    /**
    * Returns the living entity that the main entity has bred with.
    *
    * @return the living entity that was bred with.
    */
    public Animals getBredWith() {
        return entityTwo;
    }

    /**
    * Returns the (currently unspawned) baby entity.
    *
    * @return the baby entity.
    */
    public Ageable getBaby() {
        return baby;
    }

    /**
    * Gets the player that started the breeding, if any.
    * If breeding was induced by plugin, may return null.
    *
    * @returns the breeder player, or null if none.
    */
    public Player getBreeder() {
        return breeder;
    }

    /**
    * Sets the player that is considered to have started the breeding,
    * if any.
    * The player set will receive potential rewards for breeding,
    * (EG: an achievement).
    * Set the player null to give nobody the reward.
    *
    * @param player the new breeder player, or null for none.
    */
    public void setBreeder(Player player) {
        breeder = player;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
