package org.bukkit.command;

/**
 * Represents a command that delegates to one or more other commands
 */
public class MultipleCommandAlias extends Command {
    private Command[] commands;

    public MultipleCommandAlias(String name, Command[] commands) {
        super(name);
        this.commands = commands;
    }

    /**
     * Returns all command this alias issue.
     * 
     * @return all command this alias issue.
     */
    public Command[] getCommands() {
        return this.commands.clone();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        boolean result = false;

        for (Command command : commands) {
            result |= command.execute(sender, commandLabel, args);
        }

        return result;
    }
}
