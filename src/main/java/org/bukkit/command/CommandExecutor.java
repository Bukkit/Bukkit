package org.bukkit.command;

/**
 * Represents a class which contains a single method for executing commands
 */
public interface CommandExecutor {

    /**
     * Executes the given command, returning its success
     *
     * @param sender Source of the command
     * @param cmd Command which was executed
     * @param CommandLabel Alias of the command which was used
     * @param args Passed command arguments
     * @return true if a valid command, otherwise false
     */
    public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {}
}
