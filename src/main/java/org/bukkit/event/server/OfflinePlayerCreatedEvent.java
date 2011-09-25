package org.bukkit.event.server;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

/**
 * Called when an OfflinePlayer is created.
 */
public class OfflinePlayerCreatedEvent extends ServerEvent {
    private OfflinePlayer offlinePlayer;
    public OfflinePlayerCreatedEvent(OfflinePlayer offlinePlayer) {
        super(Event.Type.SERVER_OFFLINEPLAYERCREATED);
        this.offlinePlayer = offlinePlayer;
    }

    /**
     * Get the OfflinePlayer that is created.
     * @return The sender
     */
    public OfflinePlayer getOfflinePlayer() {
        return offlinePlayer;
    }
}
