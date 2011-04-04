package org.bukkit.event.server;

import org.bukkit.event.Event;

/**
 * Called when the server is loaded
 * @author Cruz Bishop
 */
public class ServerLoadEvent extends Event {
    
    public ServerLoadEvent() {
        super(Type.SERVER_LOAD);
    }

}
