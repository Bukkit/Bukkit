package org.bukkit.event.player;

import java.net.InetAddress;
import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.bukkit.chat.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Stores details for players attempting to log in.
 * <p>
 * This event is asynchronous, and not run using main thread.
 */
public class AsyncPlayerPreLoginEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Result result;
    private Message message;
    private final String name;
    private final InetAddress ipAddress;
    private final UUID uniqueId;

    /**
     * This constructor creates a new PlayerLoginEvent with the result
     * {@link Result#ALLOWED} and no kick message.
     *
     * @param name the {@link Player}'s name this event is triggered for
     * @param ipAddress the address the player used to connect, provided for
     *     timing issues
     * @param uniqueId the player's {@link UUID} this event is triggered for
     * @deprecated Players are now identified by their {@link UUID}. Use
     *     {@link #AsyncPlayerPreLoginEvent(String, InetAddress, UUID)}
     *     instead.
     */
    @Deprecated
    public AsyncPlayerPreLoginEvent(final String name, final InetAddress ipAddress) {
        this(name, ipAddress, null);
    }

    /**
     * This constructor creates a new PlayerLoginEvent with the result
     * {@link Result#ALLOWED} and no kick message.
     *
     * @param name the {@link Player}'s name this event is triggered for
     * @param ipAddress the address the player used to connect, provided for
     *     timing issues
     * @param uniqueId the player's {@link UUID} this event is triggered for
     */
    public AsyncPlayerPreLoginEvent(final String name, final InetAddress ipAddress, final UUID uniqueId) {
        super(true);
        this.result = Result.ALLOWED;
        this.message = null;
        this.name = name;
        this.ipAddress = ipAddress;
        this.uniqueId = uniqueId;
    }

    /**
     * Gets the current result of the login, as an enum.
     *
     * @return Current Result of the login
     */
    public Result getLoginResult() {
        return result;
    }

    /**
     * Gets the current result of the login, as an enum.
     *
     * @return Current Result of the login
     * @deprecated This method uses a deprecated enum from {@link
     *     PlayerPreLoginEvent}
     * @see #getLoginResult()
     */
    @Deprecated
    public PlayerPreLoginEvent.Result getResult() {
        return result == null ? null : result.old();
    }

    /**
     * Sets the new result of the login, as an enum.
     *
     * @param result New result to set
     */
    public void setLoginResult(final Result result) {
        this.result = result;
    }

    /**
     * Sets the new result of the login, as an enum.
     *
     * @param result New result to set
     * @deprecated This method uses a deprecated enum from
     *     {@link PlayerPreLoginEvent}
     * @see #setLoginResult(Result)
     */
    @Deprecated
    public void setResult(final PlayerPreLoginEvent.Result result) {
        this.result = result == null ? null : Result.valueOf(result.name());
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
     * @return Current kick message
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
     * @deprecated This method uses a deprecated enum from
     *     {@link PlayerPreLoginEvent}. Use
     *     {@link #disallow(Result, Message)} instead.
     * @see #disallow(Result, Message)
     */
    @Deprecated
    public void disallow(final PlayerPreLoginEvent.Result result, final String message) {
        Validate.notNull(result, "Cannot disallow with result null.");
        Validate.isTrue(result != PlayerPreLoginEvent.Result.ALLOWED, "Cannot disallow with status ALLOW");
        this.result = Result.valueOf(result.name());
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
     * Gets the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player IP address.
     *
     * @return The IP address
     */
    public InetAddress getAddress() {
        return ipAddress;
    }

    /**
     * Gets the player's unique ID.
     *
     * @return The unique ID
     */
    public UUID getUniqueId() {
        return uniqueId;
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
        KICK_OTHER;

        @Deprecated
        private PlayerPreLoginEvent.Result old() {
            return PlayerPreLoginEvent.Result.valueOf(name());
        }
    }
}
