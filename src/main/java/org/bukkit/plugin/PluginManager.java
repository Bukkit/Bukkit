
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
     * Discover and add to the index plugins handled by the given loader.
     *
     * This method is similar to {@link #rebuildIndex()}, but iterates only
     * the plugins that match the given loader's filename filters, and adds
     * those to the index, (rather than clearing the index and rebuilding
     * from scratch.)
     *
     * This method is useful for plugins defining new interfaces. Those
     * should call this method to index newly available plugins, and then
     * call {@link #enablePlugin(PluginDescription)} for each returned.
     *
     * @param loader The loader of the type of plugin to index.
     * @return A list of plugin descriptions that was indexed.
     */
    public PluginDescription[] updateIndexForInterface(PluginLoader loader);

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

    public PluginDescription[] getPluginDescriptions();

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
