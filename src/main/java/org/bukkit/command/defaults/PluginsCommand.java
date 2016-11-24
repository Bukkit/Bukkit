package org.bukkit.command.defaults;

import java.util.Arrays;
import java.util.Comparator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

        sender.sendMessage("Plugins " + getPluginList());
        return true;
    }

    private static final Comparator<Plugin> PLUGIN_COMPARATOR = new Comparator<Plugin>() {
        public int compare(Plugin a, Plugin b) {
            if (a.isEnabled() != b.isEnabled()) {
                return a.isEnabled() ? -1 : 1;
            } else {
                return String.CASE_INSENSITIVE_ORDER.compare(a.getDescription().getName(), b.getDescription().getName());
            }
        }
    };

    private String getPluginList() {
        StringBuilder pluginList = new StringBuilder();
        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
        Arrays.sort(plugins, PLUGIN_COMPARATOR); 

        for (Plugin plugin : plugins) {
            if (pluginList.length() > 0) {
                pluginList.append(ChatColor.WHITE);
                pluginList.append(", ");
            }

            pluginList.append(plugin.isEnabled() ? ChatColor.GREEN : ChatColor.RED);
            pluginList.append(plugin.getDescription().getName());
        }

        return "(" + plugins.length + "): " + pluginList.toString();
    }
}
