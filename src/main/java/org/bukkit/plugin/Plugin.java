
package org.bukkit.plugin;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Represents a Plugin
 */
public interface Plugin {
    /**
     * Returns the plugin.yaml file containing the details for this plugin
     *
     * @return Contents of the plugin.yaml file
     */
    public PluginDescription getDescription();

    /**
     * Gets the associated PluginLoader responsible for this plugin
     *
     * @return PluginLoader that controls this plugin
     */
    public PluginLoader getPluginLoader();

    /**
     * Returns the Server instance currently running this plugin
     *
     * @return Server running this plugin
     */
    public Server getServer();

    /**
     * Returns a value indicating whether or not this plugin is currently enabled
     *
     * @return true if this plugin is enabled, otherwise false
     */
    public boolean isEnabled();

    /**
     * Called when this plugin is disabled
     */
    public void onDisable();

    /**
     * Called when this plugin is enabled
     */
    public void onEnable();

    /**
     * Called when a command registered by this plugin is received.
     * @param commandLabel
     * @return TODO
     */
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args);
}
