package org.bukkit.event.entity;

import java.util.List;

import org.bukkit.entity.Ageable;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when an Ageable entity is born
 * <p />
 * If an EntityBornEvent is cancelled the parents will not breed, no child is produced,
 * and no experience is dropped.
 */
public class EntityBornEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final List<Ageable> parents;
    private int experienceToDrop;
    private boolean cancel;

    public EntityBornEvent(Ageable what, List<Ageable> parents, int experienceToDrop) {
        super(what);
        this.parents = parents;
        this.experienceToDrop = experienceToDrop;
        this.cancel = false;
    }

    /**
     * Gets the parents of child that would be produced by this event.
     *
     * @return Ageable the child entity's parents
     */
    public List<Ageable> getParents() {
        return parents;
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
