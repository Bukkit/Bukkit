package org.bukkit.event.server;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Cancellable;

/**
 * Server Command events
 */
public class ServerCommandEvent extends ServerEvent implements Cancellable {
    private boolean cancelled = false;
    private String command;
    private CommandSender sender;
    public ServerCommandEvent(ConsoleCommandSender console, String message) {
        super(Type.SERVER_COMMAND);
        command = message;
        sender = console;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins.
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    /**
     * Gets the command that the user is attempting to execute from the console
     *
     * @return Command the user is attempting to execute
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the command that the server will execute
     *
     * @param message New message that the server will execute
     */
    public void setCommand(String message) {
        this.command = message;
    }
    
    /**
     * Get the command sender.
     */
    public CommandSender getSender() {
        return sender;
    }
}
