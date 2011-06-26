package org.bukkit.event;

/**
 * 
 * @author t3hk0d3
 */
public class EventHandlerException extends RuntimeException {
    
    public EventHandlerException(Event.Type type, Throwable e){
        super("Exception while processing " + type.toString() + " event", e);
    }
}
