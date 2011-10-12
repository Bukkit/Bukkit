package org.bukkit.event;

/**
 * Represents an event's priority in execution
 */
public enum EventPriority {
    /**
     * Event call is of very low importance and should be ran first, to allow
     * other plugins to further customise the outcome
     */
    LOWEST,
    /**
     * Event call is of low importance
     */
    LOW,
    /**
     * Event call is neither important or unimportant, and may be ran normally
     */
    NORMAL,
    /**
     * Event call is of high importance
     */
    HIGH,
    /**
     * Event call is critical and must have the final say in what happens
     * to the event
     */
    HIGHEST,
    /**
     * Event is listened to purely for monitoring the outcome of an event.
     * <p/>
     * No modifications to the event should be made under this priority
     */
    MONITOR
}
