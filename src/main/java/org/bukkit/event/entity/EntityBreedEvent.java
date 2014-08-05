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
    private boolean cancelled;
    private int xp;

    public EntityBreedEvent(final Animals entity, final Animals entityTwo, final Ageable baby, final int xp) {
        super(entity);
        this.entityTwo = entityTwo;
        this.baby = baby;
        this.xp = xp;
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
     * Returns an array containing both parent entities.
     * The array always has a length of 2.
     *
     * @return an array of both parent entities.
     */
    public Animals[] getParents() {
        return new Animals[] { (Animals)entity, entityTwo };
    }

    /**
    * Returns the (currently unspawned) baby entity.
    *
    * @return the baby entity.
    */
    public Ageable getBaby() {
        return baby;
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

    /**
     * Gets the amount of experience that will be dropped by this event.
     *
     * @return the total amount of experience.
     */
    public int getExperience() {
        return xp;
    }

    /**
     * Changes the amount of XP this event will drop.
     *
     * @param newXP the amount of XP to drop.
     */
    public void setExperience(int newXP) {
        xp = newXP;
    }
}
