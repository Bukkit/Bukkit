package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.TravelAgent;
import org.bukkit.entity.Player;

/**
 * Called when a player completes the portaling process by standing in a portal
 */
@SuppressWarnings("serial")
public class PlayerPortalEvent extends PlayerTeleportEvent {

    protected boolean useTravelAgent = true;

    protected Player player;
    protected TravelAgent travelAgent;
    
    private PortalType portalType = PortalType.UNKNOWN; 

    public PlayerPortalEvent(Player player, Location from, Location to, TravelAgent pta) {
        super(Type.PLAYER_PORTAL, player, from, to);
        this.travelAgent = pta;
    }
    
    public PlayerPortalEvent(Player player, Location from, Location to, TravelAgent pta, PortalType portalType) {
        super(Type.PLAYER_PORTAL, player, from, to);
        this.travelAgent = pta;
        this.portalType = portalType;
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
    
    /**
     * Gets the portal type of this portaling event
     *
     * @return Portal type of the event
     */
    public PortalType getPortalType() {
        return portalType;
    }
    
    public enum PortalType {
        /**
         * Indicates the portaling event was caused by a player entering a nether portal
         */
        NETHER_PORTAL,
        /**
         * Indicates the portaling event was caused by a player entering an end portal
         */
        END_PORTAL,
        /**
         * Indicates the portaling event was caused by a plugin
         */
        PLUGIN,
        /**
         * Indicates the portaling event was caused by an event not covered by this enum
         */
        UNKNOWN;
    }
}