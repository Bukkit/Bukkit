package org.bukkit.event.block;

import java.util.IllegalFormatException;

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a command block is changed by a player.
 * <p />
 * If a Command Block Change event is cancelled, the command will not be changed.
 */
public class CommandBlockChangeEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private final Player player;
    private String command;

    /**
     * This is the default message format ({@code Command set: %1$s}) when the command was successfully set.
     * @see #getMessageFormat()
     */
    public static final String DEFAULT_FORMAT = "Command set: %1$s";
    /**
     * This is the default message format ({@code Command NOT set: %1$s}) when the event was cancelled.
     * @see #getMessageFormat()
     */
    public static final String DEFAULT_CANCEL_FORMAT = "Command " + ChatColor.RED + "NOT" + ChatColor.WHITE + " set: %1$s";
    private String playerMessageFormat;

    public CommandBlockChangeEvent(final Block theBlock, final Player thePlayer, final String command) {
        super(theBlock);
        this.player = thePlayer;
        this.command = command;
        this.playerMessageFormat = DEFAULT_FORMAT;
    }

    /**
     * Gets the player that is changing the command.
     *
     * @return The Player involved in this event.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the new command.
     *
     * @see #setCommand(String)
     * @return The command line.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the command.
     *
     * @see #getCommand()
     * @param cmd command to set
     */
    public void setCommand(String cmd) {
        Validate.notNull(cmd);
        command = cmd;
    }

    /**
     * Gets the message that will be sent to the player.
     * <p>
     * An empty string means that no message will be sent.
     *
     * @see #getMessageFormat()
     * @see #setMessageFormat(String)
     * @return The formatted message.
     */
    public String getMessage() {
        return String.format(playerMessageFormat, command);
    }

    /**
     * Gets the message that will be sent to the player.
     * This is a format string and will be formatted with the new command.
     * <p>
     * Please note that this message will be sent <b>even if the event was cancelled</b>!
     * <p>
     * By default, this is {@link #DEFAULT_FORMAT} or {@link #DEFAULT_CANCEL_FORMAT}, depending on the cancellation state.
     * <p>
     * An empty string means "no message".
     *
     * @see #getMessage()
     * @see #setMessageFormat(String)
     * @see #DEFAULT_FORMAT
     * @see #DEFAULT_CANCEL_FORMAT
     * @return The format for the message that is sent to the player.
     */
    public String getMessageFormat() {
        return playerMessageFormat;
    }

    /**
     * Sets the message that will be sent to the player.
     * This is a format string and will be formatted with {@link String#format(String, Object...)} and the new command.
     * <p>
     * Please note that this message will be sent <b>even if the event was cancelled</b>!
     * <p>
     * By default, this is {@link #DEFAULT_FORMAT} or {@link #DEFAULT_CANCEL_FORMAT}, depending on the cancellation state.
     * Setting the format to a custom value will break this behavior. You can re-enable it with {@link #resetMessageFormat()}.
     * <p>
     * An empty string means "no message".
     *
     * @see #getMessage()
     * @see #getMessageFormat()
     * @param format The new message format or an empty string for "no message".
     * @throws IllegalFormatException If the format string is invalid.
     */
    public void setMessageFormat(String format) {
        Validate.notNull(format);
        String.format(format, command); // verify that they didn't mess up the format string
        playerMessageFormat = format;
    }

    /**
     * Resets the message format.
     * <p>
     * By default, the message format is {@link #DEFAULT_FORMAT} or {@link #DEFAULT_CANCEL_FORMAT}, depending on the cancellation state.
     * Setting it to a custom value will break this behavior. You can use this method to re-enable it.
     *
     * @see #setMessageFormat(String)
     */
    public void resetMessageFormat() {
        playerMessageFormat = cancel ? DEFAULT_CANCEL_FORMAT : DEFAULT_FORMAT;
    }

    public boolean isCancelled() {
        return cancel;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Additionally, this will change the message format from {@link #DEFAULT_FORMAT} to {@link #DEFAULT_CANCEL_FORMAT}
     * and back, depending on the cancellation state, as long as the message format is unchanged.
     *
     * @see #setMessageFormat(String)
     */
    public void setCancelled(boolean cancel) {
        if (cancel && (playerMessageFormat == DEFAULT_FORMAT))
            setMessageFormat(DEFAULT_CANCEL_FORMAT);
        if (!cancel && (playerMessageFormat == DEFAULT_CANCEL_FORMAT))
            setMessageFormat(DEFAULT_FORMAT);
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
