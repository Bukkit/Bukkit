package org.bukkit.command.defaults;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.util.CollectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BanListCommand extends VanillaCommand {
    private static final List<String> BANLIST_TYPES = Collections.unmodifiableList(Arrays.asList("ips", "players"));

    public BanListCommand() {
        super("banlist");
        this.description = "View all players banned from this server";
        this.usageMessage = "/banlist [ips|players]";
        this.setPermission("bukkit.command.ban.list");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;

        // TODO: ips support
        StringBuilder message = new StringBuilder();
        OfflinePlayer[] banlist = Bukkit.getServer().getBannedPlayers().toArray(new OfflinePlayer[0]);

        for (int x = 0; x < banlist.length; x++) {
            if (x != 0) {
                if (x == banlist.length - 1) {
                    message.append(" and ");
                } else {
                    message.append(", ");
                }
            }
            message.append(banlist[x].getName());
        }

        sender.sendMessage("There are " + banlist.length + " total banned players:");
        sender.sendMessage(message.toString());
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");

        if (args.length == 2) {
            return CollectionUtil.filterPartialMatches(args[1], new ArrayList<String>(BANLIST_TYPES));
        }
        return Collections.emptyList();
    }

    @Override
    public boolean matches(String input) {
        return input.equalsIgnoreCase("banlist");
    }
}
