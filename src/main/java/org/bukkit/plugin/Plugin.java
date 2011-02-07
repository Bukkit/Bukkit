
package org.bukkit.plugin;

import org.bukkit.Server;

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
     * Returns the Server instance currently running this plugin
     *
     * @return Server running this plugin
     */
    public Server getServer();
}
