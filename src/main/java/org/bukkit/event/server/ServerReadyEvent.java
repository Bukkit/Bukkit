package org.bukkit.event.server;

/**
 * Called when Server is ready, after plugins have loaded
 */
public class ServerReadyEvent extends ServerEvent {
    public ServerReadyEvent() {
        super(Type.SERVER_READY);
    }
}
