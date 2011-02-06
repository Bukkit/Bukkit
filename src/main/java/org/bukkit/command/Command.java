package org.bukkit.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public abstract class Command {
    private final Plugin plugin;
    private final String name;
    private List<String> aliases;
    protected String tooltip = "";
    protected String usageMessage;

    public Command(Plugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        this.aliases = new ArrayList<String>();
        this.usageMessage = "/" + name;
    }

    public abstract boolean execute(CommandSender sender, String currentAlias, String[] args);

    /**
     * Get the Plugin associated with this command.
     *
     * May be null for built-in commands.
     *
     * @return A loaded Plugin.
     */
    public Plugin getPlugin() {
        return plugin;
    }

    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getTooltip() {
        return tooltip;
    }

    public String getUsage() {
        return usageMessage;
    }

    public Command setAliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }

    public Command setTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public Command setUsage(String usage) {
        this.usageMessage = usage;
        return this;
    }
}