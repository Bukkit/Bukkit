package org.bukkit.plugin.java;

import org.bukkit.ChatColor;
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

        if (!cmdSuccess && !usageMessage.isEmpty()) {
            String tmpMsg = usageMessage.replace("<command>", commandLabel);
            String[] usageLines = tmpMsg.split("\\n");
            for(String line: usageLines) {
                while (line.length() > 0) {
                    int stripChars = (line.length() > 53 ? 53:line.length());
                    sender.sendMessage(ChatColor.RED + line.substring(0, stripChars));
                    line = line.substring(stripChars);
                }
            }
        }
        return cmdSuccess;
    }
}
