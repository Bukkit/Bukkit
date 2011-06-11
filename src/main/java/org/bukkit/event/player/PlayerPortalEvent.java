package org.bukkit.event.player;

import org.bukkit.TravelAgent;
import org.bukkit.entity.Player;
import org.bukkit.location.DirectionalLocation;

/**
 * Called when a player completes the portaling process by standing in a portal
 */
public class PlayerPortalEvent extends PlayerTeleportEvent {

    private boolean useTravelAgent = true;
    protected TravelAgent travelAgent;

    public PlayerPortalEvent(Player player, DirectionalLocation from, DirectionalLocation to, TravelAgent pta) {
        super(Type.PLAYER_PORTAL, player, from, to);
        this.travelAgent = pta;
    }

    public void useTravelAgent(boolean useTravelAgent) {
        this.useTravelAgent = useTravelAgent;
    }

    public boolean useTravelAgent() {
        return useTravelAgent;
    }

    public TravelAgent getPortalTravelAgent() {
        return this.travelAgent;
    }

    public void setPortalTravelAgent(TravelAgent travelAgent) {
        this.travelAgent = travelAgent;
    }
}