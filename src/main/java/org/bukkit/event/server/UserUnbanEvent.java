package org.bukkit.event.server;

import org.bukkit.event.Cancellable;

/**
 * Called when a user gets unbanned
 */
public class UserUnbanEvent extends ServerEvent implements Cancellable {
    private final String userName;
    private Boolean cancel;
    
    public UserUnbanEvent(final Type type, final String userName) {
        super(type);
        this.userName = userName;
        this.cancel = false;
    }
    
    /**
     * Gets the cancellation state of this event. Set to true if you
     * want to prevent the user from getting unbanned.
     *
     * @return boolean cancellation state
     */
    public boolean isCancelled() {
        return cancel;
    }
    
    /**
     * Gets the name of the user that is getting unbanned
     *
     * @return String name of the user
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * Cancelling this event will prevent the unban of the targetted user
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
