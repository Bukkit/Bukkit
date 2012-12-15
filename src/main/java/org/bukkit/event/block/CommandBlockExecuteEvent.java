package org.bukkit.event.block;

import org.apache.commons.lang.Validate;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a command block is executed.
 * <p />
 * If the event is cancelled, nothing will happen.
 */
public class CommandBlockExecuteEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private String command;

    public CommandBlockExecuteEvent(final Block theBlock, final String command) {
        super(theBlock);
        this.command = command;
    }

    /**
     * Gets the command that will be executed.
     *
     * @see #setCommand(String)
     * @return The command line.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the command that will be executed.
     *
     * @see #getCommand()
     * @param cmd command to set
     */
    public void setCommand(String cmd) {
        Validate.notNull(cmd);
        command = cmd;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
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
