package org.bukkit.event.player;

import org.bukkit.chat.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Called when a player leaves a server
 */
public class PlayerQuitEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private Message quitMessage;

    /**
     * Creates a new PlayerKickEvent for the given player with the given default
     * message sent to all online players after the event finished being
     * processed.
     * 
     * @param who the player that had left the server
     * @param quitMessage the quit message being send to all online players
     */
    public PlayerQuitEvent(final Player who, final Message quitMessage) {
        super(who);
        this.quitMessage = quitMessage;
    }

    /**
     * Creates a new PlayerKickEvent for the given player with the given default
     * message sent to all online players after the event finished being
     * processed.
     * 
     * @param who the player that had left the server
     * @param quitMessage the quit message being send to all online players
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #PlayerQuitEvent(Player, Message)} instead.
     */
    @Deprecated
    public PlayerQuitEvent(final Player who, final String quitMessage) {
        super(who);
        setQuitMessage(quitMessage);
    }

    /**
     * Gets the quit message to send to all online players. Can be null/empty
     * and may contain color codes.
     *
     * @return the quit message being shown.
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #getMessage()} instead.
     */
    @Deprecated
    public String getQuitMessage() {
        return quitMessage == null ? null : quitMessage.toString();
    }

    /**
     * Sets the quit message to send to all online players. Set to null or empty
     * to prevent this message from being shown.
     *
     * @param quitMessage the quit message to show. Can be null, empty and can
     *     contain color codes.
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #setMessage(Message)} instead.
     */
    @Deprecated
    public void setQuitMessage(String quitMessage) {
        this.quitMessage = quitMessage == null || quitMessage.isEmpty() ? null : Message.of(quitMessage);
    }

    /**
     * Gets the quit message to send to all online players. Can be null.
     *
     * @return the join message being shown
     */
    public Message getMessage() {
        return quitMessage;
    }

    /**
     * Sets the join message to send to all online players. Set to null to
     * prevent the message from being shown.
     *
     * @param quitMessage the quit message to show
     */
    public void setMessage(Message quitMessage) {
        this.quitMessage = quitMessage;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
