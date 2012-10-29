package org.bukkit.event.hanging;


import org.bukkit.entity.Hanging;
import org.bukkit.event.Event;

/**
 * Represents a hanging-related event.
 */
public abstract class HangingEvent extends Event {
    protected Hanging hanging;

    protected HangingEvent(final Hanging hanging) {
        this.hanging = hanging;
    }

    /**
     * Gets the hanging involved in this event.
     *
     * @return the hanging
     */
    public Hanging getHanging() {
        return hanging;
    }
}
