package org.bukkit.command.defaults;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand extends VanillaCommand {
    public BanCommand() {
        super("ban");
        this.description = "Prevents the specified player from using this server";
        this.usageMessage = "/ban <player> [reason ...]";
        this.setPermission("bukkit.command.ban.player");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;
        if (args.length == 0)  {
            sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            return false;
        }

        // TODO: Ban Reason support
        Bukkit.getOfflinePlayer(args[0]).setBanned(true);

        Player player = Bukkit.getPlayer(args[0]);
        if (player != null) {
            player.kickPlayer("Banned by admin.");
        }

        Command.broadcastCommandMessage(sender, "Banned player " + args[0]);
        return true;
    }

    @Override
    public boolean matches(String input) {
        return input.equalsIgnoreCase("ban");
    }
}
