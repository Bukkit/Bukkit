
package org.bukkit.plugin;

import java.io.File;
import java.util.regex.Pattern;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

/**
 * Represents a plugin loader, which handles direct access to specific types
 * of plugins
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
     * Enables the specified plugin
     *
     * Attempting to enable a plugin that is already enabled will have no effect
     *
     * @param description Description of the plugin to enable
     * @return Plugin instance that was enabled
     * @throws InvalidPluginException Thrown when the specified file is not a plugin
     */
    public Plugin enablePlugin(PluginDescription description) throws InvalidPluginException;

    /**
     * Disables the specified plugin
     *
     * Attempting to disable a plugin that is not enabled will have no effect
     *
     * @param plugin Plugin to disable
     */
    public void disablePlugin(Plugin plugin);
}
