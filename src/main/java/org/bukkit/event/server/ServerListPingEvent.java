package org.bukkit.event.server;

import java.net.InetAddress;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when a server list ping is coming in.
 */
@SuppressWarnings("serial")
public class ServerListPingEvent extends ServerEvent {
    private static final HandlerList handlers = new HandlerList();

    private InetAddress address;
    private String motd;
    private int numPlayers;
    private int maxPlayers;

    public ServerListPingEvent(InetAddress address, String motd, int numPlayers, int maxPlayers) {
        super(Event.Type.SERVER_LIST_PING);
        this.address = address;
        this.motd = motd;
        this.numPlayers = numPlayers;
        this.maxPlayers = maxPlayers;
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
     * @return the the maximum number of player
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

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
