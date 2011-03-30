package org.bukkit.event.server;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * Server Command events
 */
public class InternalMessageEvent extends Event implements Cancellable {
    
    private boolean cancel = false;
    private MessageType type;
    private long thread_time = 0;
    
    public enum MessageType {
        /**
         * "Can't keep up! Did the system time change, or is the server overloaded?"
         */
        TOO_SLOW,
        
        /**
         * "Time ran backwards! Did the system time change?"
         */
        TOO_FAST,
    }
    
    public InternalMessageEvent( MessageType type ) {
        super(Type.INTERNAL_MESSAGE);
        this.type = type;
    }
    
    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
    
    public MessageType getMessageType() {
	return type;
    }

    public long getThreadTime() {
        return thread_time;
    }

    public void setThreadTime(long thread_time) {
        this.thread_time = thread_time;
    }
    
}