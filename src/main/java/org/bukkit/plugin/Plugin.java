package org.bukkit.plugin;

import org.bukkit.Server;

/**
 * Represents a plugin
 */
public interface Plugin {
    /**
     * Returns the description object containing the details for this plugin
     *
     * This must return the description object used to load the currently in
     * use version of the plugin. If a newer version of the plugin is
     * installed, a description for the new version can be retrieved using
     * {@link PluginManager#rebuildIndex()} followed by
     * {@link PluginManager#getPluginDescription(String)}
     *
     * @return The PluginDescription object associated with the plugin
     * @see PluginDescription
     */
    public PluginDescription getDescription();

    /**
     * Returns the Server instance currently running this plugin
     *
     * @return Server running this plugin
     */
    public Server getServer();
}
