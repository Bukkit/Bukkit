package org.bukkit.plugin;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.util.config.Configuration;

/**
 * Encapsulates description and other metadata on a plugin.
 */
public abstract class PluginDescription {
    private final File file;
    private final File dataFolder;
    private final Configuration config;
    protected String name;
    protected String version;
    protected Object commands;
    protected String description;
    protected ArrayList<String> authors;
    protected String website;

    protected PluginDescription(final File file) {
        this.file = file;

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

    public Object getCommands() {
        return commands;
    }

    /**
     * Gets the description of this plugin
     *
     * return Description of this plugin
     */
    public String getDescription() {
        return description;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getWebsite() {
        return website;
    }

    /**
     * Returns the folder that the plugin data's files are located in. The
     * folder may not yet exist.
     *
     * @return
     */
    public File getDataFolder() {
        return dataFolder;
    }

    /**
     * Returns the main configuration file. It should be loaded.
     *
     * @return
     */
    public Configuration getConfiguration() {
        return config;
    }
}
