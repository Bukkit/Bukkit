package org.bukkit.plugin;

import java.util.ArrayList;

/**
 * Encapsulates description and other metadata on a plugin.
 */
public abstract class PluginDescription {
    protected String name;
    protected String version;
    protected Object commands;
    protected String description;
    protected ArrayList<String> authors;
    protected String website;

    protected PluginDescription() {
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
}
