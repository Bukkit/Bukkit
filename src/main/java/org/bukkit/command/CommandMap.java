package org.bukkit.command;

import java.util.List;

import org.bukkit.plugin.Plugin;

/**
 * The map of all registered commands
 *
 * Bukkit itself, plugin loaders and plugins register commands with instances
 * of this class to make them available to players.
 */
public interface CommandMap {
    /**
     * Registers all the commands belonging to a certain plugin
     *
     * @param fallbackPrefix A prefix to use when a label conflicts with another command
     * @param commands The Command instances to register
     * @return
     */
    public void registerAll(String fallbackPrefix, List<Command> commands);

    /**
     * Registers a command. Returns true on success; false if name is already
     * taken and fallback had to be used
     *
     * @param label A label for this command, without the '/'-prefix.
     * @param fallbackPrefix A prefix to use when the label conflicts with another command
     * @param command The Command instance to register
     * @return Returns true if command was registered; false if label was already in use.
     */
    public boolean register(String label, String fallbackPrefix, Command command);

    /**
     * Looks for the requested command and executes it if found
     *
     * @param sender The sender of the command
     * @param cmdLine The exact line of text send, for example: "/test abc 123"
     * @return False if no target is found; true otherwise
     * @throws CommandException Thrown when the executor for the given command fails with an unhandled exception
     */
    public boolean dispatch(CommandSender sender, String cmdLine);

    /**
     * Clears all registered commands
     */
    public void clearCommands();

    /**
     * Clears all registered commands associated with the given plugin
     *
     * @param plugin The plugin to filter on
     */
    public void clearCommands(Plugin plugin);
}
