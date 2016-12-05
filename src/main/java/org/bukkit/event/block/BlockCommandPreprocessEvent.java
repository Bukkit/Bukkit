package org.bukkit.event.block;

import org.apache.commons.lang.Validate;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * This event is called whenever a block runs a command.
 * It is called early in the command handling process,
 * and modifications in this event (via {@link #setMessage(String)})
 * will be shown in the behavior.
 * <p>
 * Many plugins will have <b>no use for this event</b>, and you should
 * attempt to avoid using it if it is not necessary.
 * <p>
 * Some examples of valid uses for this event are:
 * <ul>
 * <li>Logging executed commands to a separate file
 * <li>Variable substitution. For example, replacing
 *     <code>${nearbyPlayer}</code> with the name of the nearest other
 *     player, or simulating the <code>@a</code> and <code>@p</code>
 *     decorators used by Command Blocks in plugins that do not handle it.
 * <li>Conditionally blocking commands belonging to other plugins. For
 *     example, blocking the use of the <code>/home</code> command in a
 *     combat arena.
 * <li>Per-sender command aliases. For example, after a block runs the
 *     command <code>/calias cr gamemode creative</code>, the next time they
 *     run <code>/cr</code>, it gets replaced into
 *     <code>/gamemode creative</code>. (Global command aliases should be
 *     done by registering the alias.)
 * </ul>
 * <p>
 * Examples of incorrect uses are:
 * <ul>
 * <li>Using this event to run command logic
 * </ul>
 * <p>
 * If the event is cancelled, processing of the command will halt.
 * <p>
 */
public class BlockCommandPreprocessEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private String message;

    public BlockCommandPreprocessEvent(final Block block, final String message) {
        super(block);
        this.message = message;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the command that the block is attempting to send.
     * <p>
     * All commands begin with a special character; implementations do not
     * consider the first character when executing the content.
     *
     * @return Message the block is attempting to send
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the command that the block will send.
     * <p>
     * All commands begin with a special character; implementations do not
     * consider the first character when executing the content.
     *
     * @param command New message that the block will send
     * @throws IllegalArgumentException if command is null or empty
     */
    public void setMessage(String command) throws IllegalArgumentException {
        Validate.notNull(command, "Command cannot be null");
        Validate.notEmpty(command, "Command cannot be empty");
        message = command;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
