package org.bukkit.plugin;

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

public class YamlPluginDescription extends PluginDescription {
    private static final Yaml yaml = new Yaml(new SafeConstructor());

    @SuppressWarnings("unchecked")
    public YamlPluginDescription(final InputStream stream) throws InvalidDescriptionException {
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
                commands = map.get("commands");
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

    @SuppressWarnings("unchecked")
    public static List<Command> parse(Plugin plugin) {
        List<Command> pluginCmds = new ArrayList<Command>();
        Object object = plugin.getDescription().getCommands();
        if (object == null)
            return pluginCmds;

        Map<String, Map<String, Object>> map = (Map<String, Map<String, Object>>)object;

        if (map != null) {
            for(Entry<String, Map<String, Object>> entry : map.entrySet()) {
                Command newCmd = new PluginCommand(entry.getKey(),plugin);
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
        }
        return pluginCmds;
    }
}
