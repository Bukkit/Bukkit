
package org.bukkit.plugin;

import org.bukkit.command.CommandMap;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Listener;

/**
 * Handles all plugin management from the Server
 */
public interface PluginManager {

    /**
     * Registers the specified plugin loader
     *
     * @param loader Class name of the PluginLoader to register
     * @throws IllegalArgumentException Thrown when the given Class is not a valid PluginLoader
     */
    public void registerInterface(Class<? extends PluginLoader> loader) throws IllegalArgumentException;

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
     * Loads the plugins contained within the specified directory
     *
     * @return A list of all plugins loaded
     */
    public void loadPlugins();

    /**
     * Returns the given plugin's description from the index
     *
     * Please note that the name of the plugin is case-sensitive
     *
     * @param name Name of the plugin to check
     * @return PluginDescription if it exists, otherwise null
     */
    public PluginDescription getPluginDescription(String name);

    public PluginDescription[] getPluginDescriptions();

    /**
     * Disables all the loaded plugins
     */
    public void disablePlugins();

    /**
     * Disables and removes all plugins
     */
    public void clearPlugins();

    /**
     * Calls a player related event with the given details
     *
     * @param type Type of player related event to call
     * @param event Event details
     */
    public void callEvent(Event event);

    /**
     * Registers the given event to the specified listener
     *
     * @param type EventType to register
     * @param listener Listener to register
     * @param priority Priority of this event
     * @param plugin Plugin to register
     */
    public void registerEvent(Event.Type type, Listener listener, Priority priority, Plugin plugin);

    /**
     * Registers the given event to the specified executor
     *
     * @param type EventType to register
     * @param listener Listener to register
     * @param executor EventExecutor to register
     * @param priority Priority of this event
     * @param plugin Plugin to register
     */
    public void registerEvent(Event.Type type, Listener listener, EventExecutor executor, Priority priority, Plugin plugin);

    /**
     * Enables the specified plugin
     *
     * @param descriptionPluginDescription of the plugin to load
     * @return The Plugin loaded, or null if it was invalid
     * @throws InvalidPluginException Thrown when the specified file is not a valid plugin
     * @throws InvalidDescriptionException Thrown when the specified file contains an invalid description
     */
    public Plugin enablePlugin(PluginDescription description);

    /**
     * Disables the specified plugin
     *
     * Attempting to disable a plugin that is not enabled will have no effect
     *
     * @param plugin Plugin to disable
     */
    public void disablePlugin(Plugin plugin);

    /**
     * Get the command map used to keep track of plugin commands
     *
     * @return A CommandMap instance
     */
    public CommandMap getCommandMap();
}
