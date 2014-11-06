package org.bukkit.event.server;

import java.net.InetAddress;
import java.util.Iterator;

import org.apache.commons.lang.Validate;
import org.bukkit.chat.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.util.CachedServerIcon;

/**
 * Called when a server list ping is coming in. Displayed players can be
 * checked and removed by {@link #iterator() iterating} over this event.
 */
public class ServerListPingEvent extends ServerEvent implements Iterable<Player> {
    private static final int MAGIC_PLAYER_COUNT = Integer.MIN_VALUE;
    private static final HandlerList handlers = new HandlerList();
    private final InetAddress address;
    private Message motd;
    private final int numPlayers;
    private int maxPlayers;

    /**
     * Creates a new ServerListPingEvent showing fixed number of player
     * currently online.
     * 
     * @param address the address the ping is coming from
     * @param motd the message of the day message to show to that player
     * @param numPlayers the number of player currently online
     * @param maxPlayers the maximum allowed player on this server
     */
    public ServerListPingEvent(final InetAddress address, final Message motd, final int numPlayers, final int maxPlayers) {
        Validate.notNull(motd, "Motd cannot be null!");
        Validate.isTrue(numPlayers >= 0, "Cannot have negative number of players online", numPlayers);
        this.address = address;
        this.motd = motd;
        this.numPlayers = numPlayers;
        this.maxPlayers = maxPlayers;
    }

    /**
     * Creates a new ServerListPingEvent showing fixed number of player
     * currently online.
     * 
     * @param address the address the ping is coming from
     * @param motd the message of the day message to show to that player
     * @param numPlayers the number of player currently online
     * @param maxPlayers the maximum allowed player on this server
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #ServerListPingEvent(InetAddress, Message, int, int)}
     *     instead.
     */
    @Deprecated
    public ServerListPingEvent(final InetAddress address, final String motd, final int numPlayers, final int maxPlayers) {
        this(address, motd == null ? null : Message.of(motd), numPlayers, maxPlayers);
    }

    /**
     * This constructor is intended for implementations that provide the
     * {@link #iterator()} method, thus provided the {@link #getNumPlayers()}
     * count.
     * 
     * @param address the address the ping is coming from
     * @param motd the message of the day message to show to that player
     * @param maxPlayers the maximum allowed player on this server
     */
    protected ServerListPingEvent(final InetAddress address, final Message motd, final int maxPlayers) {
        Validate.notNull(motd, "Motd cannot be null!");
        this.numPlayers = MAGIC_PLAYER_COUNT;
        this.address = address;
        this.motd = motd;
        this.maxPlayers = maxPlayers;
    }

    /**
     * This constructor is intended for implementations that provide the
     * {@link #iterator()} method, thus provided the {@link #getNumPlayers()}
     * count.
     * 
     * @param address the address the ping is coming from
     * @param motd the message of the day message to show to that player
     * @param maxPlayers the maximum allowed player on this server
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #ServerListPingEvent(InetAddress, Message, int)} instead.
     */
    @Deprecated
    protected ServerListPingEvent(final InetAddress address, final String motd, final int maxPlayers) {
        this(address, motd == null ? null : Message.of(motd), maxPlayers);
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
     * Gets the message of the day message. May contain color codes.
     *
     * @return the message of the day
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #getMotd()} instead.
     */
    @Deprecated
    public String getMotd() {
        return motd.toString();
    }

    /**
     * Changes the message of the day message. Can contain color codes but
     * cannot be null.
     *
     * @param motd the message of the day
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #setMotdMessage(Message)} instead.
     */
    @Deprecated
    public void setMotd(String motd) {
        Validate.notNull(motd, "Motd cannot be null!");
        this.motd = Message.of(motd);
    }

    /**
     * Gets the message of the day message.
     *
     * @return the message of the day
     */
    public Message getMotdMessage() {
        return motd;
    }

    /**
     * Changes the message of the day message. Cannot be null.
     *
     * @param motd the message of the day
     */
    public void setMotdMessage(Message motd) {
        Validate.notNull(motd, "Motd cannot be null!");
        this.motd = motd;
    }

    /**
     * Get the number of players sent.
     *
     * @return the number of players
     */
    public int getNumPlayers() {
        int numPlayers = this.numPlayers;
        if (numPlayers == MAGIC_PLAYER_COUNT) {
            numPlayers = 0;
            for (@SuppressWarnings("unused") final Player player : this) {
                numPlayers++;
            }
        }
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
     * Sets the server-icon sent to the client.
     *
     * @param icon the icon to send to the client
     * @throws IllegalArgumentException if the {@link CachedServerIcon} is not
     *     created by the caller of this event; null may be accepted for some
     *     implementations
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

    /**
     * {@inheritDoc}
     * <p>
     * Calling the {@link Iterator#remove()} method will force that particular
     * player to not be displayed on the player list, decrease the size
     * returned by {@link #getNumPlayers()}, and will not be returned again by
     * any new iterator.
     *
     * @throws UnsupportedOperationException if the caller of this event does
     *     not support removing players
     */
    @Override
    public Iterator<Player> iterator() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
