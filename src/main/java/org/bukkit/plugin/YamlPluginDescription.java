package org.bukkit.plugin;

import java.io.File;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

public abstract class YamlPluginDescription extends PluginDescription {
    private static final Yaml yaml = new Yaml(new SafeConstructor());
    private Map<String, Map<String, Object>> commands = null;

    @SuppressWarnings("unchecked")
    public YamlPluginDescription(final PluginLoader loader, final File file, final InputStream stream) throws InvalidDescriptionException {
        super(loader, file);
        loadMap((Map<String, Object>)yaml.load(stream));
    }

    /**
     * Saves this PluginDescriptionFile to the given writer
     *
     * @param writer Writer to output this file to
     */
    public void save(Writer writer) {
        Map<String, Object> map = new HashMap<String, Object>();
        saveMap(map);
        yaml.dump(map, writer);
    }

    protected void loadMap(Map<String, Object> map) throws InvalidDescriptionException {
        try {
            name = map.get("name").toString();
        } catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "name is not defined");
        } catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, "name is of wrong type");
        }

        try {
            version = map.get("version").toString();
        } catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "version is not defined");
        } catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, "version is of wrong type");
        }

        if (map.containsKey("commands")) {
            try {
                commands = (Map<String, Map<String, Object>>)map.get("commands");
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "commands are of wrong type");
            }
        }

        if (map.containsKey("website")) {
            try {
                website = (String)map.get("website");
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "website is of wrong type");
            }
        }

        if (map.containsKey("description")) {
            try {
                description = (String)map.get("description");
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "description is of wrong type");
            }
        }

        if (map.containsKey("author")) {
            try {
                authors = new ArrayList<String>();
                authors.add((String)map.get("author"));
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "author is of wrong type");
            }
        }

        if (map.containsKey("authors")) {
            try {
                authors = (ArrayList<String>)map.get("authors");
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "authors are of wrong type");
            }
        }
    }

    protected void saveMap(Map<String, Object> map) {
        map.put("name", name);
        map.put("version", version);

        if (commands != null) map.put("command", commands);
        if (website != null) map.put("website", website);
        if (description != null) map.put("description", description);

        if (authors.size() == 1) {
            map.put("author", authors.get(0));
        } else if (authors.size() > 1) {
            map.put("authors", authors);
        }
    }

    /**
     * A factory method for creating Command instances
     *
     * {@link #buildCommands()} relies on this method to be overridden by
     * subclasses and provide it with the instances of the Command class.
     * This way, plugins can provide instances of Command subclasses.
     *
     * The arguments are extracted from the YAML input.
     *
     * @param name The primary command name
     * @param plugin The plugin to create the command for
     * @return The new Command instance
     */
    protected abstract Command createCommand(String name, Plugin plugin);

    /**
     * Build Command instances for the command descriptions
     *
     * Loaders can call this after they have instantiated the plugin to
     * get the command objects they have to register.
     *
     * @return A list of Command instances
     */
    @SuppressWarnings("unchecked")
    public final List<Command> buildCommands(Plugin plugin) {
        List<Command> pluginCmds = new ArrayList<Command>();
        if (commands == null) {
            return pluginCmds;
        }

        for(Entry<String, Map<String, Object>> entry : commands.entrySet()) {
            Command newCmd = createCommand(entry.getKey(), plugin);
            Object description = entry.getValue().get("description");
            Object usage = entry.getValue().get("usage");
            Object aliases = entry.getValue().get("aliases");

            if (description != null)
                newCmd.setTooltip(description.toString());

            if (usage != null) {
                newCmd.setUsage(usage.toString());
            }

            if (aliases != null) {
                List<String> aliasList = new ArrayList<String>();

                if (aliases instanceof List) {
                    for (Object o : (List<Object>)aliases) {
                        aliasList.add(o.toString());
                    }
                } else {
                    aliasList.add(aliases.toString());
                }

                newCmd.setAliases(aliasList);
            }

            pluginCmds.add(newCmd);
        }
        return pluginCmds;
    }
}
