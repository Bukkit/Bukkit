package org.bukkit.plugin;

import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

/**
 * Provides access to a Plugins description file, plugin.yaml
 */
public final class PluginDescriptionFile {
    private static final Yaml yaml = new Yaml(new SafeConstructor());
    private String name = null;
    private String main = null;
    private ArrayList<String> depend = null;
    private String version = null;
    private Object commands = null;
    private String description = null;
    private ArrayList<String> authors = new ArrayList<String>();
    private String website = null;
    private boolean database = false;

    @SuppressWarnings("unchecked")
    public PluginDescriptionFile(final InputStream stream) throws InvalidDescriptionException {
        loadMap((Map<String, Object>)yaml.load(stream));
    }

    /**
     * Loads a PluginDescriptionFile from the specified reader
     * @param reader
     */
    @SuppressWarnings("unchecked")
    public PluginDescriptionFile(final Reader reader) throws InvalidDescriptionException {
        loadMap((Map<String, Object>)yaml.load(reader));
    }

    /**
     * Creates a new PluginDescriptionFile with the given detailed
     *
     * @param pluginName Name of this plugin
     * @param mainClass Full location of the main class of this plugin
     */
    public PluginDescriptionFile(final String pluginName, final String pluginVersion, final String mainClass) {
        name = pluginName;
        version = pluginVersion;
        main = mainClass;
    }

    /**
     * Saves this PluginDescriptionFile to the given writer
     *
     * @param writer Writer to output this file to
     */
    public void save(Writer writer) {
        yaml.dump(saveMap(), writer);
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
     * Returns the main class for a plugin
     *
     * @return Java classpath
     */
    public String getMain() {
        return main;
    }

    public Object getCommands() {
        return commands;
    }

    public Object getDepend() {
        return depend;
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

    public boolean isDatabaseEnabled() {
        return database;
    }

    public void setDatabaseEnabled(boolean database) {
        this.database = database;
    }

    private void loadMap(Map<String, Object> map) throws InvalidDescriptionException {
        // No need for a instanceof check, no type conversion.
        Object tempName = map.get("name");
            
        if (tempName == null) {
            throw new InvalidDescriptionException(ex, "name is not defined");
        }
        else {
            name = tempName.toString();
            else if (!tempName.matches("^[A-Za-z0-9 _.-]+$")) {
                throw new InvalidDescriptionException("name '" + name +  "' contains invalid characters.");
            }
        }
        // END get name

        Object tempVer = map.get("version");
        if (tempVer == null) {
            throw new InvalidDescriptionException(ex, "version is not defined");
        }
        else {
            version = tempVer.toString();
        }
        // END get version

        main = map.get("main").toString();
        if (main == null) {
            throw new InvalidDescriptionException("main is not defined");
        }
        if (main.startsWith("org.bukkit.")) {
            throw new InvalidDescriptionException("main may not be within the org.bukkit namespace");
        }
        // END Get main

        if (map.containsKey("commands")) {
            commands = map.get("commands");
        }

        if (map.containsKey("depend")) {
            Object tempDepend = map.get("depend");
            if (tempDepend instanceof ArrayList) {
                depend = (ArrayList<String>)tempDepend;
            }
            else {
                throw new InvalidDescriptionException("depend is of wrong type");
            }
        }

        if (map.containsKey("database")) {
            Object tempDatabase = map.get("database");
            if (tempDatabase instanceof Boolean) {
                database = (Boolean)tempDatabase;
            }
            else {
                throw new InvalidDescriptionException("database is of wrong type");
            }
        }

        if (map.containsKey("website")) {
            Object tempWebsite = map.get("website");
            if (tempWebsite instanceof String) {
                website = (String)tempWebsite;
            }
            else {
                throw new InvalidDescriptionException("website is of wrong type");
            }
        }

        if (map.containsKey("description")) {
            Object tempDescription = map.get("description");
            if (tempDescription instanceof String) {
                description = (String)tempDescription;
            }
            else {
                throw new InvalidDescriptionException("description is of wrong type");
            }
        }

        if (map.containsKey("author")) {
            Object tempAuthor = map.get("author");
            if (tempAuthor instanceof String) {
                authors.add((String)tempAuthor);
            }
            else {
                throw new InvalidDescriptionException("author is of wrong type");
            }
        }

        if (map.containsKey("authors")) {
            Object tempAuthors = map.get("authors");
            if (tempAuthors instanceof ArrayList) {
                authors.addAll((ArrayList<String>)tempAuthors;
            }
            else {
                throw new InvalidDescriptionException(ex, "authors are of wrong type");
            }
        }
    }

    private Map<String, Object> saveMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("main", main);
        map.put("version", version);
        map.put("database", database);

        if (commands != null) map.put("command", commands);
        if (depend != null) map.put("depend", depend);
        if (website != null) map.put("website", website);
        if (description != null) map.put("description", description);

        if (authors.size() == 1) {
        map.put("author", authors.get(0));
        } else if (authors.size() > 1) {
        map.put("authors", authors);
        }

        return map;
    }
    }
}