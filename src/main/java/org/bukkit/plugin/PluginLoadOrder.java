package org.bukkit.plugin;

/**
 * Represents the order in which a plugin should be initialized and enabled
 */
public enum PluginLoadOrder {
    /**
     * Indicates that the plugin will be loaded at startup
     */
    STARTUP,
    /**
     * Indicates that the plugin will be loaded after the first/default world was created
     */
    POSTWORLD
    /**
     * Indicates that the plugin will be loaded right before the Server is done with it's booting
     */
    DONE
}
