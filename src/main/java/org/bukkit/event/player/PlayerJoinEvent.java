package org.bukkit.event.player;

import org.bukkit.chat.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Called when a player joins a server
 */
public class PlayerJoinEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private Message joinMessage;

    /**
     * Creates a new PlayerJoinEvent for the given player with the given default
     * message sent to all online players after the event finished being
     * processed.
     * 
     * @param playerJoined the player that had joined the server
     * @param joinMessage the join message being send to all online players
     */
    public PlayerJoinEvent(final Player playerJoined, final Message joinMessage) {
        super(playerJoined);
        this.joinMessage = joinMessage;
    }

    /**
     * Creates a new PlayerJoinEvent for the given player with the given default
     * message sent to all online players after the event finished being
     * processed.
     * 
     * @param playerJoined the player that had joined the server
     * @param joinMessage the join message being send to all online players
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #PlayerJoinEvent(Player, Message)} instead.
     */
    @Deprecated
    public PlayerJoinEvent(final Player playerJoined, final String joinMessage) {
        super(playerJoined);
        setJoinMessage(joinMessage);
    }

    /**
     * Gets the join message to send to all online players. Can be null/empty
     * and may contain color codes.
     *
     * @return string the join message to show
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #getMessage()} instead.
     */
    @Deprecated
    public String getJoinMessage() {
        return joinMessage == null ? null : joinMessage.toString();
    }

    /**
     * Sets the join message to send to all online players. Set to null or empty
     * to prevent the message being shown.
     *
     * @param joinMessage the join message to show. Can be null, empty and can
     *     contain color codes.
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #setMessage(Message)} instead.
     */
    @Deprecated
    public void setJoinMessage(String joinMessage) {
        this.joinMessage = joinMessage == null || joinMessage.isEmpty() ? null : Message.of(joinMessage);
    }

    /**
     * Gets the join message to send to all online players. Can be null.
     *
     * @return the join message being shown
     */
    public Message getMessage() {
        return joinMessage;
    }

    /**
     * Sets the join message to send to all online players. Set to null to
     * prevent the message being shown.
     *
     * @param joinMessage the join message to show
     */
    public void setMessage(Message joinMessage) {
        this.joinMessage = joinMessage;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
