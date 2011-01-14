
package org.bukkit.plugin.java;

import java.io.File;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;

/**
 * Represents a Java plugin
 */
public abstract class JavaPlugin implements Plugin {
    private boolean isEnabled = false;
    private PluginLoader loader;
    private Server server;
    private File file;
    private PluginDescriptionFile description;
    private ClassLoader classLoader;
   
    /**
     * Sets this plugin's PluginLoader instance
     *
     * @param pluginLoader PluginLoader that is responsible for this plugin
     */
    public void setPluginLoader(PluginLoader pluginLoader) {
        loader = pluginLoader;
    }

    /**
     * Sets this plugin's Server instance
     *
     * @param instance Server instance that is running this plugin
     */
    public void setServer(Server instance) {
        server = instance;
    }
    
    /**
     * Sets this plugin's PluginLoader
     *
     * @param desc PluginDescriptionFile containing metadata on this plugin
     */
    public void setDescription(PluginDescriptionFile desc) {
        description = desc;
    }
    
    /**
     * Sets this plugin's File instance
     *
     * @param plugin File containing this plugin
     */
    public void setFile(File pluginFile) {
        file = pluginFile;
    }
    
    /**
     * Sets this plugin's ClassLoader instance
     *
     * @param cLoader ClassLoader which holds this plugin
     */
    public void setClassLoader(ClassLoader cLoader) {
        classLoader = cLoader;
    }
        
    /**
     * Gets the associated PluginLoader responsible for this plugin
     *
     * @return PluginLoader that controls this plugin
     */
    public final PluginLoader getPluginLoader() {
        return loader;
    }

    /**
     * Returns the Server instance currently running this plugin
     *
     * @return Server running this plugin
     */
    public final Server getServer() {
        return server;
    }

    /**
     * Returns a value indicating whether or not this plugin is currently enabled
     *
     * @return true if this plugin is enabled, otherwise false
     */
    public final boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Returns the file which contains this plugin
     *
     * @return File containing this plugin
     */
    protected File getFile() {
        return file;
    }

    /**
     * Returns the plugin.yaml file containing the details for this plugin
     *
     * @return Contents of the plugin.yaml file
     */
    public PluginDescriptionFile getDescription() {
        return description;
    }

    /**
     * Returns the ClassLoader which holds this plugin
     *
     * @return ClassLoader holding this plugin
     */
    protected ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * Sets the enabled state of this plugin
     *
     * @param enabled true if enabled, otherwise false
     */
    protected void setEnabled(final boolean enabled) {
        if (isEnabled != enabled) {
            isEnabled = enabled;

            if (isEnabled) {
                onEnable();
            }  else {
                onDisable();
            }
        }
    }
}
