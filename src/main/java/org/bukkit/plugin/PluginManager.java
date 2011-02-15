
package org.bukkit.plugin;

import org.bukkit.command.CommandMap;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;

/**
 * Handles all plugin management from the Server
 */
public interface PluginManager {
    /**
     * Registers the specified plugin loader
     *
     * @param loader The PluginLoader instance to register
     * @see PluginLoader
     */
    public void registerInterface(PluginLoader loader);

    /**
     * Registers a plugin description
     *
     * Typically called from {@link PluginLoader#discoverPlugins()}.
     *
     * @param description The description to register
     */
    public void register(PluginDescription description);

    /**
     * Rediscover and reindex all plugins
     *
     * Walks the plugin directory, reads descriptions and rebuilds the index
     * of all plugins it can find for the registered loaders.
     */
    public void rebuildIndex();

    /**
     * Checks if the given plugin is loaded and returns it when applicable
     *
     * Please note that the name of the plugin is case-sensitive
     *
     * @param name Name of the plugin to check
     * @return Plugin if it exists, otherwise null
     */
    public Plugin getPlugin(String name);

    /**
     * Gets a list of all currently loaded plugins
     *
     * @return Array of Plugins
     */
    public Plugin[] getPlugins();

    /**
     * Returns the given plugin's description from the index
     *
     * Please note that the name of the plugin is case-sensitive
     *
     * @param name Name of the plugin to check
     * @return PluginDescription if it exists, otherwise null
     */
    public PluginDescription getPluginDescription(String name);

    /**
     * Gets a list of all currently known plugins' descriptions
     *
     * @return Array of PluginDescriptions
     */
    public PluginDescription[] getPluginDescriptions();

    /**
     * Calls a player related event with the given details
     *
     * @param event Event details
     */
    public void callEvent(Event event);

    /**
     * Registers the given event to the specified executor
     *
     * @param plugin Plugin that is registering a handler
     * @param type Event type to register
     * @param priority Priority of this event
     * @param executor EventExecutor used to invoke the handler
     */
    public void registerEvent(Plugin plugin, Event.Type type, Priority priority, EventExecutor executor);

    /**
     * Enables the specified plugin
     *
     * Attempting to enable a plugin that is already enabled will simply
     * return the existing Plugin object.
     *
     * @param description Description of the plugin to enable
     */
    public Plugin enablePlugin(PluginDescription description);

    /**
     * Enable all plugins in the index
     */
    public void enableAllPlugins();

    /**
     * Disables the specified plugin
     *
     * @param plugin Plugin to disable
     */
    public void disablePlugin(Plugin plugin);

    /**
     * Disables all the loaded plugins
     */
    public void disableAllPlugins();

    /**
     * Get the command map used to keep track of plugin commands
     *
     * @return A CommandMap instance
     */
    public CommandMap getCommandMap();
}
