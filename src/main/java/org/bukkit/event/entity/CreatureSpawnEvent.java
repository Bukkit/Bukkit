package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * Event for handling creature (animal and monster) spawns.
 */
public class CreatureSpawnEvent extends EntityEvent implements Cancellable {
    private boolean cancelled;

    public CreatureSpawnEvent(Entity spawned)
    {
        super(Event.Type.CREATURE_SPAWN, spawned);
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * If a creature spawn event is cancelled, the creature will not be spawned.
     * This will not fire an event.
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * If a creature spawn event is cancelled, the creature will not be spawned.
     * This will not fire an event.
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
