package org.bukkit.plugin;

import java.io.File;
import java.util.regex.Pattern;

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
     * Returns a list of all filename filters expected by this PluginLoader
     */
    public Pattern[] getPluginFileFilters();

    /**
     * Creates and returns an event executor
     *
     * @param type Type of the event executor to create
     * @param listener the object that will handle the eventual call back
     */
    public EventExecutor createExecutor(Event.Type type, Listener listener);

    /**
     * Reads the description for the plugin in the specified file
     *
     * The implementation gathers the necessary information, usually
     * with help from metadata included with the plugin, and returns
     * a PluginDescription subclass.
     *
     * @param pluginFile The file containing the plugin
     * @return A filled PluginDescription object
     * @throws InvalidDescriptionException Thrown when the metadata was not understood
     * @see PluginDescription
     */
    public PluginDescription readDescription(File pluginFile) throws InvalidDescriptionException;

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
     * The PluginManager takes care of unregistering commands, listeners and
     * scheduled tasks.
     *
     * This method must not be used outside of the PluginManager. To disable a
     * plugin from elsewhere, use {@link PluginManager#disablePlugin(Plugin)}
     * instead.
     *
     * @param plugin The plugin to unload
     */
    public void disablePlugin(Plugin plugin);
}
