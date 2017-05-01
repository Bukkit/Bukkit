package org.bukkit.event.server;

import org.bukkit.event.HandlerList;

/**
 * Called when the server is shutdown
 */
public class ServerShutdownEvent extends ServerEvent {
    private static final HandlerList handlers = new HandlerList();
    private String kickMessage;

    public ServerShutdownEvent(String kickMessage){
        this.kickMessage = kickMessage;
    }

    /**
     * Gets the kick message that will be displayed to all players when the server is shutdown
     * @return The kick message
     */

    public String getKickMessage(){
        return kickMessage;
    }

    /**
     * Sets the kick message which will be displayed to all players when the server is shutdown
     * @param message The new kick message
     */

    public void setKickMessage(String message){
        this.kickMessage = message;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

}
