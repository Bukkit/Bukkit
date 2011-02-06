
package org.bukkit.plugin;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import org.bukkit.Server;
import java.util.regex.Pattern;

import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPluginLoader;

/**
 * Handles all plugin management from the Server
 */
public final class SimplePluginManager implements PluginManager {
    private final Server server;
    private final File pluginFolder;
    private final CommandMap commandMap;
    private final Map<Pattern, PluginLoader> fileAssociations = new HashMap<Pattern, PluginLoader>();
    private final Map<String, Plugin> plugins = new HashMap<String, Plugin>();
    private final Map<Event.Type, SortedSet<RegisteredListener>> listeners = new EnumMap<Event.Type, SortedSet<RegisteredListener>>(Event.Type.class);
    private final Comparator<RegisteredListener> comparer = new Comparator<RegisteredListener>() {
        public int compare(RegisteredListener i, RegisteredListener j) {
            int result = i.getPriority().compareTo(j.getPriority());

            if ((result == 0) && (i != j)) {
                result = 1;
            }

            return result;
        }
    };

    public SimplePluginManager(Server server, File pluginFolder) {
        this.server = server;
        this.pluginFolder = pluginFolder;
        this.commandMap = new SimpleCommandMap(server);

        registerInterface(JavaPluginLoader.class);
    }

    /**
     * Registers the specified plugin loader
     *
     * @param loader Class name of the PluginLoader to register
     * @throws IllegalArgumentException Thrown when the given Class is not a valid PluginLoader
     */
    public void registerInterface(Class<? extends PluginLoader> loader) throws IllegalArgumentException {
        PluginLoader instance;

        if (PluginLoader.class.isAssignableFrom(loader)) {
            Constructor<? extends PluginLoader> constructor;
            try {
                constructor = loader.getConstructor(Server.class);
                instance = constructor.newInstance(server);
            } catch (NoSuchMethodException ex) {
                throw new IllegalArgumentException(String.format("Class %s does not have a public %s(Server) constructor", loader.getName()), ex);
            } catch (Exception ex) {
                throw new IllegalArgumentException(String.format("Unexpected exception %s while attempting to construct a new instance of %s", ex.getClass().getName(), loader.getName()), ex);
            }
        } else {
            throw new IllegalArgumentException(String.format("Class %s does not implement interface PluginLoader", loader.getName()));
        }

        Pattern[] patterns = instance.getPluginFileFilters();

        for (Pattern pattern : patterns) {
            fileAssociations.put(pattern, instance);
        }
    }

    /**
     * Loads the plugins contained within the specified directory
     *
     * @return A list of all plugins loaded
     */
    public Plugin[] loadPlugins() {
        if (!pluginFolder.exists()) {
            pluginFolder.mkdir();
        }
        File[] files = pluginFolder.listFiles();

        List<Plugin> result = new ArrayList<Plugin>();
        for (File file : files) {
            Plugin plugin = null;

            try {
                plugin = enablePlugin(file);
            } catch (InvalidPluginException ex) {
                server.getLogger().log(Level.SEVERE, "Could not load " + file.getPath() + " in " + pluginFolder.getPath() + ": " + ex.getMessage(), ex);
            } catch (InvalidDescriptionException ex) {
                server.getLogger().log(Level.SEVERE, "Could not load " + file.getPath() + " in " + pluginFolder.getPath() + ": " + ex.getMessage(), ex);
            }

            if (plugin != null) {
                result.add(plugin);
            }
        }

        return result.toArray(new Plugin[result.size()]);
    }

    /**
     * Checks if the given plugin is loaded and returns it when applicable
     *
     * Please note that the name of the plugin is case-sensitive
     *
     * @param name Name of the plugin to check
     * @return Plugin if it exists, otherwise null
     */
    public Plugin getPlugin(String name) {
        return plugins.get(name);
    }

    public Plugin[] getPlugins() {
        return plugins.values().toArray(new Plugin[0]);
    }

    public Plugin enablePlugin(final File file) throws InvalidDescriptionException, InvalidPluginException {
        Set<Pattern> filters = fileAssociations.keySet();
        Plugin plugin = null;

        for (Pattern filter : filters) {
            String name = file.getName();
            Matcher match = filter.matcher(name);

            if (match.find()) {
                PluginLoader loader = fileAssociations.get(filter);
                PluginDescription description = loader.readDescription(file);
                plugin = loader.enablePlugin(description);
                break;
            }
        }

        if (plugin != null) {
            plugins.put(plugin.getDescription().getName(), plugin);
        }

        return plugin;
    }

    public void disablePlugins() {
        for(Plugin plugin: getPlugins()) {
            disablePlugin(plugin);
        }
    }

    public void disablePlugin(final Plugin plugin) {
        plugin.getDescription().getLoader().disablePlugin(plugin);
        server.getScheduler().cancelTasks(plugin);
    }

    public void clearPlugins() {
        synchronized (this) {
            disablePlugins();
            plugins.clear();
            listeners.clear();
            commandMap.clearCommands();
        }
    }

    /**
     * Calls a player related event with the given details
     *
     * @param type Type of player related event to call
     * @param event Event details
     */
    public void callEvent(Event event) {
        SortedSet<RegisteredListener> eventListeners = listeners.get(event.getType());

        if (eventListeners != null) {
            for (RegisteredListener registration : eventListeners) {
                try {
                    registration.callEvent( event );
                } catch (Throwable ex) {
                    server.getLogger().log(Level.SEVERE, "Could not pass event " + event.getType() + " to " + registration.getPlugin().getDescription().getName(), ex);
                }
            }
        }
    }

    /**
     * Registers the given event to the specified listener
     *
     * @param type EventType to register
     * @param listener PlayerListener to register
     * @param priority Priority of this event
     * @param plugin Plugin to register
     */
    public void registerEvent(Event.Type type, Listener listener, Priority priority, Plugin plugin) {
        getEventListeners( type ).add(new RegisteredListener(listener, priority, plugin, type));
    }

    /**
     * Registers the given event to the specified listener using a directly passed EventExecutor
     *
     * @param type EventType to register
     * @param listener PlayerListener to register
     * @param executor EventExecutor to register
     * @param priority Priority of this event
     * @param plugin Plugin to register
     */
    public void registerEvent(Event.Type type, Listener listener, EventExecutor executor, Priority priority, Plugin plugin) {
        getEventListeners( type ).add(new RegisteredListener(listener, executor, priority, plugin));
    }

    /**
     * Returns a SortedSet of RegisteredListener for the specified event type creating a new queue if needed
     *
     * @param type EventType to lookup
     * @return SortedSet<RegisteredListener> the looked up or create queue matching the requested type
     */
    private SortedSet<RegisteredListener> getEventListeners(Event.Type type) {
        SortedSet<RegisteredListener> eventListeners = listeners.get(type);

        if (eventListeners != null) {
            return eventListeners;
        }

        eventListeners = new TreeSet<RegisteredListener>(comparer);
        listeners.put(type, eventListeners);
        return eventListeners;
    }

    public CommandMap getCommandMap() {
        return commandMap;
    }
}
