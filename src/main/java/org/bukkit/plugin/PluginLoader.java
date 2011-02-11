package org.bukkit.plugin;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

/**
 * Represents a loader of, and interface to a type of plugin
 *
 * New plugin interfaces may be defined by subclassing this class,
 * and registering them with {@link PluginManager#registerInterface(Class)}.
 *
 * This interface and its implementations are generally not used
 * outside of the PluginManager.
 *
 * @see JavaPluginLoader
 */
public interface PluginLoader {
    /**
     * Return the plugin containing this loader.
     *
     * This is null for the default JavaPluginLoader, but should return an
     * instance otherwise.
     *
     * @return A plugin instance.
     */
    public Plugin getContainingPlugin();

    /**
     * Creates and returns an event executor
     *
     * @param type Type of the event executor to create
     * @param listener the object that will handle the eventual call back
     */
    public EventExecutor createExecutor(Event.Type type, Listener listener);

    /**
     * Find plugins, and read their descriptions.
     *
     * Called by the PluginManager to find out about, or get an update on
     * plugins handled by this loader.
     *
     * The loader should look for plugins in the data folder of it's own
     * containing plugin, which can be retrieved using
     * {@link PluginDescription#getDataFolder()}.
     *
     * For each plugin it finds, the implementation constructs an instance of
     * PluginDescription, and registers it using
     * {@link PluginManager#register(PluginDescription)}.
     *
     * @see PluginDescription
     */
    public void discoverPlugins();

    /**
     * Called by PluginManager to enable a plugin
     *
     * Typically, a Plugin instance is created, the plugin is allowed
     * to do initialization, and the instance is then returned.
     *
     * This method must not be used outside of the PluginManager. To enable a
     * plugin from elsewhere, use
     * {@link PluginManager#enablePlugin(PluginDescription)} instead.
     *
     * @param description The plugin description object identifying the plugin
     * @return The plugin that was loaded
     * @throws InvalidPluginException Thrown when the specified file is not a plugin
     */
    public Plugin enablePlugin(PluginDescription description) throws InvalidPluginException;

    /**
     * Called by PluginManager to disable a plugin
     *
     * Typically, the plugin is allowed to do some cleanup, after which the
     * loader releases any resources it may have associated with the plugin.
     *
     * The PluginManager takes care of unregistering commands, listeners,
     * loaders and scheduled tasks.
     *
     * This method must not be used outside of the PluginManager. To disable a
     * plugin from elsewhere, use {@link PluginManager#disablePlugin(Plugin)}
     * instead.
     *
     * @param plugin The plugin to unload
     */
    public void disablePlugin(Plugin plugin);
}
