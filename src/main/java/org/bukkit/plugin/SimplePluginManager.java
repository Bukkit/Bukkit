package org.bukkit.plugin;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
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
import org.bukkit.event.server.PluginEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPluginLoader;

/**
 * Handles all plugin management for the Server
 *
 * This is the default implementation of {@link PluginManager}.
 */
public final class SimplePluginManager implements PluginManager {
    private final Server server;
    private final File pluginFolder;
    private final List<File> systemPlugins;
    private final CommandMap commandMap;
    private final List<PluginLoader> pluginLoaders = new ArrayList<PluginLoader>();
    private final JavaPluginLoader javaPluginLoader;
    private final Map<String, PluginDescription> pluginDescriptions = new HashMap<String, PluginDescription>();
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

    /**
     * The constructor for this plugin manager
     *
     * The SimplePluginManager registers the Java interface by default
     *
     * @param server The server instance that is hosting plugins
     * @param pluginFolder The plugin folder to use
     * @param systemPlugins YAML files of additional Java system plugins to load
     */
    public SimplePluginManager(Server server, File pluginFolder, List<File> systemPlugins) {
        this.server = server;
        this.pluginFolder = pluginFolder;
        this.systemPlugins = systemPlugins;
        this.commandMap = new SimpleCommandMap(server);

        javaPluginLoader = (JavaPluginLoader)registerInterface(JavaPluginLoader.class);
    }

    /**
     * {@inheritDoc}
     */
    public PluginLoader registerInterface(Class<? extends PluginLoader> loader) throws IllegalArgumentException {
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

        pluginLoaders.add(instance);

        return instance;
    }

    /**
     * {@inheritDoc}
     */
    public void rebuildIndex() {
        pluginDescriptions.clear();

        for (PluginLoader loader : pluginLoaders) {
            updateIndexForInterface(loader);
        }

        for (File file : systemPlugins) {
            try {
                PluginDescription description = javaPluginLoader.readSystemPluginDescription(file);
                String name = description.getName();
                if (pluginDescriptions.containsKey(name)) {
                    throw new InvalidDescriptionException("A plugin with this name already exists: " + name);
                }
                pluginDescriptions.put(name, description);
            } catch (InvalidDescriptionException ex) {
                log.log(Level.SEVERE, "Could not load " + file.getPath() + " in " + pluginFolder.getPath() + ": " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public PluginDescription[] updateIndexForInterface(PluginLoader loader) {
        if (!pluginFolder.exists()) {
            pluginFolder.mkdir();
        }

        File[] files = pluginFolder.listFiles();
        Pattern[] filters = loader.getPluginFileFilters();
        ArrayList<PluginDescription> result = new ArrayList<PluginDescription>();
        for (Pattern filter : filters) {
            for (File file : files) {
                Matcher match = filter.matcher(file.getName());
                if (match.find()) {
                    try {
                        PluginDescription description = loader.readDescription(file);
                        String name = description.getName();
                        if (pluginDescriptions.containsKey(name)) {
                            throw new InvalidDescriptionException("A plugin with this name already exists: " + name);
                        }
                        pluginDescriptions.put(name, description);
                        result.add(description);
                    } catch (InvalidDescriptionException ex) {
                        server.getLogger().log(Level.SEVERE, "Could not load " + file.getPath() + " in " + pluginFolder.getPath() + ": " + ex.getMessage(), ex);
                    }
                }
            }
        }

        return result.toArray(new PluginDescription[0]);
    }

    /**
     * {@inheritDoc}
     */
    public PluginDescription getPluginDescription(String name) {
        return pluginDescriptions.get(name);
    }

    /**
     * {@inheritDoc}
     */
    public PluginDescription[] getPluginDescriptions() {
        return pluginDescriptions.values().toArray(new PluginDescription[0]);
    }

    /**
     * {@inheritDoc}
     */
    public Plugin getPlugin(String name) {
        return plugins.get(name);
    }

    /**
     * {@inheritDoc}
     */
    public Plugin[] getPlugins() {
        return plugins.values().toArray(new Plugin[0]);
    }

    /**
     * {@inheritDoc}
     */
    public Plugin enablePlugin(final PluginDescription description) {
        String name = description.getName();
        if (plugins.containsKey(name)) {
            return plugins.get(name);
        }

        PluginLoader loader = description.getLoader();
        Plugin plugin;
        try {
            plugin = loader.enablePlugin(description);
        } catch (InvalidPluginException ex) {
            server.getLogger().log(Level.SEVERE, "Could not load " + description.getName() + ": " + ex.getMessage(), ex);
            return null;
        }
        plugins.put(description.getName(), plugin);

        callEvent(new PluginEvent(Event.Type.PLUGIN_ENABLE, plugin));

        return plugin;
    }

    /**
     * {@inheritDoc}
     */
    public void enableAllPlugins() {
        for (PluginDescription description : pluginDescriptions.values()) {
            enablePlugin(description);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void disablePlugin(final Plugin plugin) {
        PluginDescription description = plugin.getDescription();

        callEvent(new PluginEvent(Event.Type.PLUGIN_DISABLE, plugin));

        description.getLoader().disablePlugin(plugin);
        plugins.remove(description.getName());
        clearEvents(plugin);
        commandMap.clearCommands(plugin);
        server.getScheduler().cancelTasks(plugin);
    }

    /**
     * {@inheritDoc}
     */
    public void disableAllPlugins() {
        while (!plugins.isEmpty()) {
            Plugin plugin = plugins.values().iterator().next();
            disablePlugin(plugin);
        }
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    public void registerEvent(Event.Type type, Listener listener, Priority priority, Plugin plugin) {
        getEventListeners( type ).add(new RegisteredListener(listener, priority, plugin, type));
    }

    /**
     * {@inheritDoc}
     */
    public void registerEvent(Event.Type type, Listener listener, EventExecutor executor, Priority priority, Plugin plugin) {
        getEventListeners( type ).add(new RegisteredListener(listener, executor, priority, plugin));
    }

    /**
     * Returns a SortedSet of RegisteredListeners for the specified event
     * type, creating a new set object if needed.
     *
     * @param type Event type to lookup
     * @return The SortedSet for the specified type
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

    /**
     * Clears all events for a specific plugin.
     *
     * @param plugin The plugin to filter on.
     */
    private void clearEvents(Plugin plugin) {
        for (SortedSet<RegisteredListener> eventListeners : listeners.values()) {
            Iterator<RegisteredListener> iterator = eventListeners.iterator();
            while (iterator.hasNext()) {
                RegisteredListener listener = iterator.next();
                if (listener.getPlugin() == plugin) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public CommandMap getCommandMap() {
        return commandMap;
    }

    /**
     * Returns the folder that is used as the primary source of plugins for
     * this manager
     *
     * @return The plugin folder
     */
    public File getPluginFolder() {
        return pluginFolder;
    }
}
