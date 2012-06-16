package org.bukkit.event.server;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Server Broadcast events
 */
public class ServerBroadcastEvent extends ServerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private String message;
    private final String permission;
    private boolean cancelled;

    public ServerBroadcastEvent(final String message, final String permission) {
        this.message = message;
        this.permission = permission;
    }

    /**
     * Gets the message that is being broadcasted to the server
     *
     * @return Message that is being broadcast
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message that the server will broadcast
     *
     * @param message New message that the server will broadcast
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * Gets the permission that will be required to see the message.
     *
     * @return Permission required to view message
     */
    public String getPermission() {
        return message;
    }  
    
    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }    

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
