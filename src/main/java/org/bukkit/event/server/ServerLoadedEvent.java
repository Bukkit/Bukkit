package org.bukkit.event.server;

import org.bukkit.event.Event;

/**
 * Used when the server is fully loaded
 * @author Cruz Bishop
 */
public class ServerLoadedEvent extends Event {
    
    /**
     * Create a new server loaded event
     * @param type The type to use
     */
    public ServerLoadedEvent(final Type type) {
        super(type);
    }

}
