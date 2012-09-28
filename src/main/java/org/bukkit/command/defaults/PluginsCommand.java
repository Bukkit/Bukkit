package org.bukkit.command.defaults;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class PluginsCommand extends BukkitCommand {
    public PluginsCommand(String name) {
        super(name);
        this.description = "Gets a list of plugins running on the server";
        this.usageMessage = "/plugins";
        this.setPermission("bukkit.command.plugins");
        this.setAliases(Arrays.asList("pl"));
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;

        sender.sendMessage(formatNames(Arrays.asList(Bukkit.getPluginManager().getPlugins()), "Plugins (%1$d): %2$s"));
        return true;
    }

    /** @param format a {@link java.util.Formatter format string} with arguments as 1 = count, 2 = names */
    static String formatNames(List<Plugin> plugins, String format) {
        StringBuilder pluginList = new StringBuilder();

        for (Plugin plugin : plugins) {
            if (pluginList.length() > 0) {
                pluginList.append(ChatColor.WHITE);
                pluginList.append(", ");
            }

            pluginList.append(plugin.isEnabled() ? ChatColor.GREEN : ChatColor.RED);
            pluginList.append(plugin.getDescription().getName());
        }

        return String.format(format, plugins.size(), pluginList.toString());
    }
}
