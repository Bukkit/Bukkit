package org.bukkit.event.player;

import org.bukkit.chat.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a player gets kicked from the server
 */
public class PlayerKickEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private Message leaveMessage;
    private Message kickReason;
    private Boolean cancel;

    /**
     * Creates a new PlayerKickEvent for the given player with the given default
     * message sent to all online players after the event finished being
     * processed.
     * 
     * @param playerKicked the player who has been kicked from the server
     * @param kickReason the reason why the player has been kicked
     * @param leaveMessage the kick message being send to all online players
     */
    public PlayerKickEvent(final Player playerKicked, final Message kickReason, final Message leaveMessage) {
        super(playerKicked);
        this.kickReason = kickReason;
        this.leaveMessage = leaveMessage;
        this.cancel = false;
    }

    /**
     * Creates a new PlayerKickEvent for the given player with the given default
     * message sent to all online players after the event finished being
     * processed.
     * 
     * @param playerKicked the player who has been kicked from the server
     * @param kickReason the reason why the player has been kicked
     * @param leaveMessage the kick message being send to all online players
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #PlayerKickEvent(Player, Message, Message)} instead.
     */
    @Deprecated
    public PlayerKickEvent(final Player playerKicked, final String kickReason, final String leaveMessage) {
        super(playerKicked);
        setReason(kickReason);
        setLeaveMessage(leaveMessage);
        this.cancel = false;
    }

    /**
     * Gets the reason why the player is getting kicked. Can be null/empty and
     * may contain color codes.
     *
     * @return the kick reason
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #getReasonMessage()} instead.
     */
    @Deprecated
    public String getReason() {
        return kickReason == null ? null : kickReason.toString();
    }

    /**
     * Gets the reason why the player is getting kicked. Can be null.
     *
     * @return the kick reason
     */
    public Message getReasonMessage() {
        return kickReason;
    }

    /**
     * Gets the leave message send to all online players. Can be null/empty and
     * may contain color codes.
     *
     * @return the leave message being shown
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #getMessage()} instead.
     */
    @Deprecated
    public String getLeaveMessage() {
        return leaveMessage == null ? null : leaveMessage.toString();
    }

    /**
     * Gets the leave message send to all online players. Can be null.
     *
     * @return the leave message being shown
     */
    public Message getMessage() {
        return leaveMessage;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Sets the reason why the player is getting kicked. Can be null/empty and
     * may contain color codes.
     *
     * @param kickReason the kick reason
     */
    @Deprecated
    public void setReason(String kickReason) {
        this.kickReason = kickReason == null || kickReason.isEmpty() ? null : Message.of(kickReason);
    }

    /**
     * Sets the reason why the player is getting kicked
     *
     * @param kickReason the kick reason
     */
    public void setReason(Message kickReason) {
        this.kickReason = kickReason;
    }

    /**
     * Sets the leave message send to all online players. Set to null or empty
     * to prevent the message being shown.
     *
     * @param leaveMessage the leave message to show
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #setMessage(Message)} instead.
     */
    @Deprecated
    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage == null || leaveMessage.isEmpty() ? null : Message.of(leaveMessage);
    }

    /**
     * Sets the leave message send to all online players. Set to null to prevent
     * the message being shown.
     *
     * @param leaveMessage the leave message to show
     */
    public void setLeaveMessage(Message leaveMessage) {
        this.leaveMessage = leaveMessage;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
