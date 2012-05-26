package org.bukkit.event;

/**
 * Represents an event
 */
public abstract class Event {
    private String name;
    private boolean propagating = true;

    /**
     * @return Name of this event
     */
    public String getEventName() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        return name;
    }

    public abstract HandlerList getHandlers();
    
    /**
     * @return If this event is still propagating
     */
    public boolean isPropagating() {
        return propagating;
    }
    
    /**
     * Makes this event stop propagating, stopping in it's tracks at this listener
     */ 
    public void stopPropagating() {
        propagating = false;
    }

    public enum Result {

        /**
         * Deny the event.
         * Depending on the event, the action indicated by the event will either not take place or will be reverted.
         * Some actions may not be denied.
         */
        DENY,
        /**
         * Neither deny nor allow the event.
         * The server will proceed with its normal handling.
         */
        DEFAULT,
        /**
         * Allow / Force the event.
         * The action indicated by the event will take place if possible, even if the server would not normally allow the action.
         * Some actions may not be allowed.
         */
        ALLOW;
    }
}
