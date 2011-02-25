package org.bukkit.plugin.java;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class JavaPluginCommand extends Command {
    public JavaPluginCommand(Plugin plugin, String name) {
        super(plugin, name);
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        boolean cmdSuccess;

        JavaPlugin plugin = (JavaPlugin)getPlugin();
        try {
            cmdSuccess = plugin.onCommand(sender, this, commandLabel, args);
        } catch (Throwable ex) {
            throw new CommandException("Unhandled exception executing command '" + commandLabel + "' in plugin " + plugin.getDescription().getFullName(), ex);
        }

        if (!cmdSuccess)
            displayUsage(sender, commandLabel);
        return cmdSuccess;
    }
}
