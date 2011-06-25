package org.bukkit.event;

/**
 * Simple interface for tagging all EventListeners
 */
public interface Listener {

    public void onEvent(Event event);
}
