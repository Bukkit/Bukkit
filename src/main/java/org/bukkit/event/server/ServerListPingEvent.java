package org.bukkit.event.server;

import java.net.InetAddress;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.util.CachedServerIcon;

/**
 * Called when a server list ping is coming in.
 */
public class ServerListPingEvent extends ServerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final InetAddress address;
    private String motd;
    private final int numPlayers;
    private int maxPlayers;
    private List<Player> playerSample;

    /**
     * 
     * @deprecated Use {@link #ServerListPingEvent(InetAddress, String, int, int, List)}
     */
    public ServerListPingEvent(final InetAddress address, final String motd, final int numPlayers, final int maxPlayers) {
        this(address, motd, numPlayers, maxPlayers, null);
    }
    
    
    public ServerListPingEvent(final InetAddress address, final String motd, final int numPlayers, final int maxPlayers, final List<Player> playerSample) {
        this.address = address;
        this.motd = motd;
        this.numPlayers = numPlayers;
        this.maxPlayers = maxPlayers;
        this.playerSample = playerSample;
    }

    /**
     * Get the address the ping is coming from.
     *
     * @return the address
     */
    public InetAddress getAddress() {
        return address;
    }

    /**
     * Get the message of the day message.
     *
     * @return the message of the day
     */
    public String getMotd() {
        return motd;
    }

    /**
     * Change the message of the day message.
     *
     * @param motd the message of the day
     */
    public void setMotd(String motd) {
        this.motd = motd;
    }

    /**
     * Get the number of players sent.
     *
     * @return the number of players
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Get the maximum number of players sent.
     *
     * @return the maximum number of players
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Set the maximum number of players sent.
     *
     * @param maxPlayers the maximum number of player
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
    
    /**
     * Get the list of players to be sent to the client. 
     * @return The sample of players
     */
    public List<Player> getPlayerSample(){
        return this.playerSample;
    }

    /**
     * Sets the server-icon sent to the client.
     *
     * @param icon the icon to send to the client
     * @throws IllegalArgumentException if the {@link CachedServerIcon} is
     *     not created by the caller of this event; null may be accepted for
     *     some implementations
     * @throws UnsupportedOperationException if the caller of this event does
     *     not support setting the server icon
     */
    public void setServerIcon(CachedServerIcon icon) throws IllegalArgumentException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
