package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;

/**
 * Holds information for when a player completes a portal events
 */
public class PlayerPortalEvent extends PlayerTeleportEvent {
    private boolean useTravelAgent = true;
    public PlayerPortalEvent(Player player, Location from, Location to) {
        super(Type.PLAYER_PORTAL, player, from, to);
    }

    public void useTravelAgent(boolean useTravelAgent){
        this.useTravelAgent = useTravelAgent;
    }
    public boolean useTravelAgent(){
        return useTravelAgent;
    }
}
