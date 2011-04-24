package org.bukkit.event.server;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class BanPlayerEvent extends Event implements Cancellable {
    private String playerName;
    private boolean cancel;
    
    public BanPlayerEvent(String playerName) {
        super(Type.BAN_PLAYER);
        this.playerName = playerName;
        this.cancel = false;
    }
    
    /**
     * Returns the player name
     * 
     * @return the player name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    public boolean isCancelled() {
        return this.cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
