
package org.bukkit.plugin.java;

import java.util.ArrayList;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescription;

/**
 * Represents a Java plugin
 */
public abstract class JavaPlugin implements Plugin {
    private boolean isEnabled = false;
    private boolean initialized = false;
    private Server server = null;
    private PluginDescription description = null;
    private ClassLoader classLoader = null;

    /**
     * Constructs a new Java plugin instance
     *
     * @param instance Server instance that is running this plugin
     * @param desc PluginDescriptionFile containing metadata on this plugin
     * @param cLoader ClassLoader which holds this plugin
     */
    public JavaPlugin(Server instance, PluginDescription desc, ClassLoader cLoader) {
        initialize(instance, desc, cLoader);
        
        JavaPluginDescription jDesc = (JavaPluginDescription)desc;
        server.getLogger().warning("Using the stupidly long constructor " + jDesc.getMain() + "(PluginLoader, Server, PluginDescriptionFile, File, File, ClassLoader) is no longer recommended. Go nag the plugin author of " + jDesc.getName() + " to remove it! (Nothing is broken, we just like to keep code clean.)");

        ArrayList<String> authors = desc.getAuthors();
        if (authors.size() > 0) {
            server.getLogger().info("Hint! It's probably someone called '" + authors.get(0) + "'");
        }
    }

    public JavaPlugin() {
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
     * Returns the plugin.yaml file containing the details for this plugin
     *
     * @return Contents of the plugin.yaml file
     */
    public PluginDescription getDescription() {
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

    /**
     * Called when a command registered by this plugin is received.
     */
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        return false; // default implementation:  do nothing!
    }

    /**
     * Initializes this plugin with the given variables.
     *
     * This method should never be called manually.
     *
     * @param loader PluginLoader that is responsible for this plugin
     * @param server Server instance that is running this plugin
     * @param description PluginDescriptionFile containing metadata on this plugin
     * @param dataFolder Folder containing the plugin's data
     * @param file File containing this plugin
     * @param classLoader ClassLoader which holds this plugin
     */
    protected final void initialize(Server server, PluginDescription description, ClassLoader classLoader) {
        if (initialized) {
            throw new UnsupportedOperationException("Cannot reinitialize a plugin");
        }

        this.initialized = true;
        this.server = server;
        this.description = description;
        this.classLoader = classLoader;
    }

    /**
     * Gets the initialization status of this plugin
     *
     * @return true if this plugin is initialized, otherwise false
     */
    public boolean isInitialized() {
        return initialized;
    }
}
