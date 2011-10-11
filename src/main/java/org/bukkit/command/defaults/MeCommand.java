package org.bukkit.command.defaults;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MeCommand extends VanillaCommand {
    public MeCommand() {
        super("me");
        this.description = "Performs the specified action in chat";
        this.usageMessage = "/me <action>";
        this.setPermission("bukkit.command.me");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;
        if (args.length < 1)  {
            sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            return false;
        }

        StringBuilder message = new StringBuilder();
        message.append(sender.getName());
        if (args.length > 0) {
	        message.append(args[0]);
	        for (int i = 1; i < args.length; i++) {
	            message.append(" ");
	            message.append(args[i]);
	        }
        }

        Bukkit.broadcastMessage("* " + message.toString());

        return true;
    }

    @Override
    public boolean matches(String input) {
        return input.startsWith("me ") || input.equalsIgnoreCase("me");
    }
}
