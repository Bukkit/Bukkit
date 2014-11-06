package org.bukkit.event.player;

import java.net.InetAddress;

import org.apache.commons.lang.Validate;
import org.bukkit.chat.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Stores details for players attempting to log in
 */
public class PlayerLoginEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final InetAddress address;
    private final String hostname;
    private Result result;
    private Message message;

    /**
     * @deprecated Address and hostname should be provided in other constructor.
     *     Use {@link #PlayerLoginEvent(Player, String, InetAddress)} instead.
     */
    @Deprecated
    public PlayerLoginEvent(final Player player) {
        this(player, "", null);
    }

    /**
     * @deprecated Address should be provided in other constructor. Use
     *     {@link #PlayerLoginEvent(Player, String, InetAddress)} instead.
     */
    @Deprecated
    public PlayerLoginEvent(final Player player, final String hostname) {
        this(player, hostname, null);
    }

    /**
     * This constructor createes a new PlayerLoginEvent with the result
     * {@link Result#ALLOWED} and no kick message.
     *
     * @param player The {@link Player} for this event
     * @param hostname The hostname that was used to connect to the server
     * @param address The address the player used to connect, provided for
     *     timing issues
     */
    public PlayerLoginEvent(final Player player, final String hostname, final InetAddress address) {
        this(player, hostname, address, Result.ALLOWED, (Message) null);
    }

    /**
     * @deprecated Address and hostname should be provided in other constructor.
     *     Use {@link #PlayerLoginEvent(Player, String, InetAddress, Result, Message)}
     *     instead.
     */
    @Deprecated
    public PlayerLoginEvent(final Player player, final Result result, final String message) {
        this(player, "", null, result, message);
    }

    /**
     * This constructor pre-configures the event with a result and message.
     *
     * @param player The {@link Player} for this event
     * @param hostname The hostname that was used to connect to the server
     * @param address The address the player used to connect, provided for
     *     timing issues
     * @param result The result status for this event
     * @param message The message to be displayed if result denies login
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #PlayerLoginEvent(Player, String, InetAddress, Result, Message)}
     *     instead.
     */
    @Deprecated
    public PlayerLoginEvent(final Player player, String hostname, final InetAddress address, final Result result, final String message) {
        this(player, hostname, address, result, (Message) null);
        setKickMessage(message);
    }

    /**
     * This constructor pre-configures the event with a result and message.
     *
     * @param player The {@link Player} for this event
     * @param hostname The hostname that was used to connect to the server
     * @param address The address the player used to connect, provided for
     *     timing issues
     * @param result The result status for this event
     * @param message The message to be displayed if result denies login
     */
    public PlayerLoginEvent(final Player player, String hostname, final InetAddress address, final Result result, final Message message) {
        super(player);
        Validate.notNull(result, "Result cannot be null.");
        this.hostname = hostname;
        this.address = address;
        this.result = result;
        this.message = message;
    }

    /**
     * Gets the current result of the login, as an enum.
     *
     * @return Current Result of the login
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets the new result of the login, as an enum. If the result is set to
     * something different than {@link Result#ALLOWED} an appropriate kick
     * message should be set as well. Cannot be null.
     *
     * @param result the new result to set
     */
    public void setResult(final Result result) {
        Validate.notNull(result, "Result cannot be null.");
        this.result = result;
    }

    /**
     * Gets the current kick message that will be used if {@link #getResult()}
     * does not match {@link Result#ALLOWED}. Can be null.
     *
     * @return the current kick message being used if the login was not allowed
     */
    public String getKickMessage() {
        return message == null ? null : message.toString();
    }

    /**
     * Sets the kick message to display to the joining player if
     * {@link #getResult()} does not match {@link Result#ALLOWED}. Can be null.
     *
     * @param message the new kick message being used if the login was not
     *     allowed
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #setMessage(Message)} instead.
     */
    @Deprecated
    public void setKickMessage(final String message) {
        this.message = message == null || message.isEmpty() ? null : Message.of(message);
    }

    /**
     * Gets the current kick message that will be used if {@link #getResult()}
     * does not match {@link Result#ALLOWED}. Can be null.
     *
     * @return the current kick message being used if the login was not allowed
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Sets the kick message to display to the joining player if
     * {@link #getResult()} does not match {@link Result#ALLOWED}. Can be null.
     *
     * @param message the new kick message being used if the login was not
     *     allowed
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Gets the hostname that the player used to connect to the server, or blank
     * if unknown.
     *
     * @return The hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Allows the player to log in and clears any kick reason currently set.
     */
    public void allow() {
        result = Result.ALLOWED;
        message = null;
    }

    /**
     * Disallows the player from logging in, with the given reason.
     *
     * @param result the new result for disallowing the player
     * @param message the new kick message being used
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #disallow(Result, Message)} instead.
     */
    @Deprecated
    public void disallow(final Result result, final String message) {
        Validate.notNull(result, "Cannot disallow with result null.");
        Validate.isTrue(result != Result.ALLOWED, "Cannot disallow with status ALLOW");
        this.result = result;
        setKickMessage(message);
    }

    /**
     * Disallows the player from logging in, with the given reason.
     *
     * @param result the new result for disallowing the player
     * @param message the new kick message being used
     */
    public void disallow(final Result result, final Message message) {
        Validate.notNull(result, "Cannot disallow with result null.");
        Validate.isTrue(result != Result.ALLOWED, "Cannot disallow with status ALLOW");
        this.result = result;
        this.message = message;
    }

    /**
     * Gets the {@link InetAddress} for the Player associated with this event.
     * This method is provided as a workaround for player.getAddress()
     * returning null during PlayerLoginEvent.
     *
     * @return The address for this player. For legacy compatibility, this may
     *     be null.
     */
    public InetAddress getAddress() {
        return address;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Basic kick reasons for communicating to plugins
     */
    public enum Result {

        /**
         * The player is allowed to log in
         */
        ALLOWED,
        /**
         * The player is not allowed to log in, due to the server being full
         */
        KICK_FULL,
        /**
         * The player is not allowed to log in, due to them being banned
         */
        KICK_BANNED,
        /**
         * The player is not allowed to log in, due to them not being on the
         * white list
         */
        KICK_WHITELIST,
        /**
         * The player is not allowed to log in, for reasons undefined
         */
        KICK_OTHER
    }
}
