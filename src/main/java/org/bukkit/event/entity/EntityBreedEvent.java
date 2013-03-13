package org.bukkit.event.entity;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when an entity breeds with another
 * <p />
 * If an EntityBreedEvent is cancelled the entities will not breed, no child is produced,
 * and no experience is dropped.
 */
public class EntityBreedEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Ageable mate;
    private final Ageable child;
    private int experienceToDrop;
    private boolean cancel;

    public EntityBreedEvent(Ageable what, Ageable mate, Ageable child, int experienceToDrop) {
        super(what);
        this.mate = mate;
        this.experienceToDrop = experienceToDrop;
        this.child = child;
        this.cancel = false;
    }

    /**
     * Gets the child that would be produced by this event.
     *
     * @return Ageable the child entity.
     */
    public Ageable getChild() {
        return child;
    }

    /**
     * Gets the Ageable entity that is breeding with the one that 
     * fired this event.
     *
     * @return Ageable the other entity involved in this breeding event.
     */
    public Ageable getMate() {
        return mate;
    }

    /**
     * Gets the amount of experience to drop if this event is successful.
     *
     * @return int experience points to drop.
     */
    public int getExperienceToDrop() {
        return experienceToDrop;
    }

    /**
     * Sets the amount of experience to drop if this event is successful.
     *
     * @param experienceToDrop the amount of experience to drop of this event is successful.
     */
    public void setExperienceToDrop(int experienceToDrop) {
        this.experienceToDrop = experienceToDrop;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
