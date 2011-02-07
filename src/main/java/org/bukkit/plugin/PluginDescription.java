package org.bukkit.plugin;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.util.config.Configuration;

/**
 * Encapsulates all the metadata on a plugin.
 *
 * Plugin interfaces implement a subclass of this, and return instances from
 * {@link PluginLoader#readDescription(File)}. Plugins themselves can use
 * {@link Plugin#getDescription()} to get access to all this metadata.
 *
 * This class contains all the metadata necessary for PluginManager and
 * PluginLoader to run the plugin, and for the plugin itself to easily
 * follow guidelines on where to store data, for example. This includes
 * the metadata included with plugins, such as plugin.yml for Java plugins.
 */
public abstract class PluginDescription {
    private final File file;
    private final PluginLoader loader;
    private final File dataFolder;
    private final Configuration config;
    protected String name;
    protected String version;
    protected String description;
    protected ArrayList<String> authors;
    protected String website;

    protected PluginDescription(final PluginLoader loader, final File file) {
        this.file = file;
        this.loader = loader;

        String filename = file.getName();
        int index = file.getName().lastIndexOf(".");
        if (index != -1) {
            String name = filename.substring(0, index);
            dataFolder = new File(file.getParentFile(), name);
        } else {
            // This is if there is no extension, which should not happen
            // Using _ to prevent name collision
            dataFolder = new File(file.getParentFile(), filename + "_");
        }

        config = new Configuration(new File(dataFolder, "config.yml"));
        config.load();
    }

    /**
     * Returns the file which contains this plugin
     *
     * @return File containing this plugin
     */
    public File getFile() {
        return file;
    }

    /**
     * Returns the name of a plugin
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the version of a plugin
     *
     * @return String name
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns the name of a plugin including the version
     *
     * @return String name
     */
    public String getFullName() {
        return name + " v" + version;
    }

    /**
     * Gets the description of this plugin
     *
     * return Description of this plugin
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the authors of this plugin.
     *
     * @return A list of authors
     */
    public ArrayList<String> getAuthors() {
        return authors;
    }

    /**
     * Gets the website of this plugin.
     *
     * @return A string containing the URL
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Returns the loader responsible for this plugin
     *
     * Used internally by PluginManager. A plugin should never have to
     * use a PluginLoader, and should instead talk to the PluginManager.
     *
     * @return The associated plugin loader
     */
    public PluginLoader getLoader() {
        return loader;
    }

    /**
     * Returns a folder the plugin may store data in
     *
     * There is no requirement for the plugin to use this folder, and plugin
     * itself is responsible for creating it if it wants to use it.
     *
     * @return File The data folder
     */
    public File getDataFolder() {
        return dataFolder;
    }

    /**
     * Returns the plugin configuration.
     *
     * The configuration lives in a YAML file in the plugin's data directory,
     * and it's use is completely optional. To use it, a plugin should first
     * call {@link Configuration#load()}.
     *
     * @return The plugin Configuration instance.
     * @see Configuration
     */
    public Configuration getConfiguration() {
        return config;
    }
}
