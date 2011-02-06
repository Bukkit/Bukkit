package org.bukkit.command;

import org.bukkit.plugin.Plugin;

public abstract class PluginCommand extends Command {
    private final Plugin owningPlugin;

    public PluginCommand(String name, Plugin owner) {
        super(name);
        this.owningPlugin = owner;
        this.usageMessage = "";
    }

    public Plugin getPlugin() {
        return owningPlugin;
    }

    public abstract boolean execute(CommandSender sender, String currentAlias, String[] args);
}