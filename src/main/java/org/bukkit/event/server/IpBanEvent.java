package org.bukkit.event.server;

import org.bukkit.event.Cancellable;

/**
 * Called when an IP gets banned
 */
public class IpBanEvent extends ServerEvent implements Cancellable {
    private final String ip;
    private Boolean cancel;
    
    public IpBanEvent(final Type type, final String ip) {
        super(type);
        this.ip = ip;
        this.cancel = false;
    }
    
    /**
     * Gets the cancellation state of this event. Set to true if you
     * want to prevent the IP from getting banned.
     *
     * @return boolean cancellation state
     */
    public boolean isCancelled() {
        return cancel;
    }
    
    /**
     * Gets the ip adress that is getting banned
     *
     * @return String ip adress
     */
    public String getIp() {
        return ip;
    }
    
    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * Cancelling this event will prevent the ban of the IP
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
