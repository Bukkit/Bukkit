package org.bukkit.event.server;

import org.bukkit.event.Event;

/**
 * Used when the server is fully loaded
 * @author Cruz Bishop
 */
public class ServerLoadedEvent extends Event {
    
    /**
     * Create a new server loaded event
     */
    public ServerLoadedEvent() {
        super(Type.SERVER_LOADED);
    }

}
