package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 *
 * @author CodeInfection
 */
public class PlayerBanEvent extends PlayerEvent implements Cancellable {
    private String banReason;
    private String leaveMessage;
    private boolean cancel;
    private BanType banType;

    public enum BanType {
        NAME,
        IP
    }
    
    public PlayerBanEvent(Player playerBanned, String banReason, String leaveMessage, PlayerBanEvent.BanType banType) {
        super(Type.PLAYER_BAN, playerBanned);
        this.banReason = banReason;
        this.leaveMessage = leaveMessage;
        this.cancel = false;
        this.banType = banType;
    }

    /**
     * Gets the cancellation state of this event. Set to true if you
     * want to prevent the player from getting banned.
     *
     * @return boolean cancellation state
     */
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Gets the reason why the player is getting banned
     *
     * @return string ban reason
     */
    public String getReason() {
        return banReason;
    }

    /**
     * Gets the leave message send to all online players
     *
     * @return string ban reason
     */
    public String getLeaveMessage() {
        return leaveMessage;
    }
    
    /**
     * Gets the ban type
     * 
     * @return PlayerBanEvent.BanType type of the ban
     */
    public PlayerBanEvent.BanType getBanType() {
        return this.banType;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * Cancelling this event will prevent the ban of the targetted player
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Sets the reason why the player is getting banned
     *
     * @param banReason ban reason
     */
    public void setReason(String banReason) {
        this.banReason = banReason;
    }

    /**
     * Sets the leave message send to all online players
     *
     * @param leaveMessage leave message
     */
    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage;
    }
    
}
