package org.bukkit.event.server;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class BanIpEvent extends Event implements Cancellable {
    private String ip;
    private boolean cancel;
    
    public BanIpEvent(String ip) {
        super(Type.BAN_IP);
        this.ip = ip;
        this.cancel = false;
    }
    
    /**
     * Returns the IP address
     * 
     * @return the IP
     */
    public String getIp() {
        return this.ip;
    }

    public boolean isCancelled() {
        return this.cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
