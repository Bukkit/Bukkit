package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Thrown when a player is sent a Packet3Chat.
 */
public class PlayerSendMessageEvent extends PlayerEvent implements Cancellable {
    static HandlerList handlers = new HandlerList();
    String message;
    boolean cancel = false;
    
    /**
     * @param player The {@link Player} for this event
     * @param message The message to be sent to the player.
     */
    public PlayerSendMessageEvent(Player player, String message) {
        super(player);
        this.message = message;
    }
    
    /**
     * Gets the message to be sent to the player.
     * 
     * @return The message to be sent to the player.
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Sets the message to be sent to the player.
     * 
     * @param newVal The message to be sent to the player.
     */
    public void setMessage(String newVal) {
        message = newVal;
    }
    /**
     * Gets the cancellation state of this event. Set to true if
     * you don't want the player to receive a message.
     *
     * @return boolean cancellation state
     */
    public boolean isCancelled() {
        return cancel;
    }
    /**
     * Sets the cancellation state of this event. A canceled event will not
     * be executed in the server, but will still pass to other plugins
     * <p />
     * Canceling this event will prevent any message from being sent to the player.
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
       this.cancel = cancel;
        
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
