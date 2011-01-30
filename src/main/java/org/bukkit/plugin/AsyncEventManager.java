package org.bukkit.plugin;

import org.bukkit.event.Event;

/**
 * Allows events to be sent to the main thread in a thread safe way
 */
public interface AsyncEventManager {

    /**
     * Sends an event to the main thread and returns immediately
     *
     * Fields in the event must be stable
     *
     * @param event Event details
     * @param long Delay before dispatching event
     */
    public void callAsyncEvent(Event event, long Delay);

    /**
     * Sends an event to the main thread and returns immediately
     *
     * Fields in the event must be stable
     *
     * @param event Event details
     */
    public void callAsyncEvent(Event event);

}
