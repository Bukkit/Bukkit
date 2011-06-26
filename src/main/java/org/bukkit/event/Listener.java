package org.bukkit.event;

/**
 * Simple interface for tagging all EventListeners
 */
public interface Listener {

    /**
     * Called when an event is passed to the listener
     *
     * @param event Relevant event details
     */
    public void onEvent(Event event);
}
