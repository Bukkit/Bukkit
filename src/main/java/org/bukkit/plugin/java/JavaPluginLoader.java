package org.bukkit.plugin.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.*;

/**
 * Represents a Java plugin loader, allowing plugins in the form of .jar
 */
public final class JavaPluginLoader implements PluginLoader {
    private static final Logger log = Logger.getLogger(JavaPluginLoader.class.getName());
    private final Server server;
    private final File pluginFolder;
    private final List<File> systemPlugins;
    private final ArrayList<PluginClassLoader> pluginClassLoaders = new ArrayList<PluginClassLoader>();

    public JavaPluginLoader(Server server, File pluginFolder, List<File> systemPlugins) {
        this.server = server;
        this.pluginFolder = pluginFolder;
        this.systemPlugins = systemPlugins;
    }

    /**
     * {@inheritDoc}
     */
    public Plugin getContainingPlugin() {
        return null;
    }

    /**
     * Get a list of ClassLoaders in use.
     *
     * This allows PluginClassLoader to access neighbouring plugins'
     * ClassLoaders, and thus their classes.
     *
     * @return A list of ClassLoaders.
     */
    public List<PluginClassLoader> getClassLoaders() {
        return pluginClassLoaders;
    }

    /**
     * {@inheritDoc}
     */
    public void discoverPlugins() {
        PluginManager manager = server.getPluginManager();
        for (File file : pluginFolder.listFiles()) {
            if (!file.getName().endsWith(".jar")) {
                continue;
            }
            try {
                PluginDescription description = readDescription(file);
                manager.register(description);
            } catch (InvalidDescriptionException ex) {
                log.log(Level.SEVERE, "Could not load " + file.getPath() + " in " + pluginFolder.getPath() + ": " + ex.getMessage(), ex);
            }
        }

        for (File file : systemPlugins) {
            try {
                PluginDescription description = readSystemPluginDescription(file);
                manager.register(description);
            } catch (InvalidDescriptionException ex) {
                log.log(Level.SEVERE, "Could not load " + file.getPath() + " in " + pluginFolder.getPath() + ": " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * Reads the description for the plugin in the specified JAR-file
     *
     * @param file The JAR-file containing the plugin
     * @return A PluginDescription instance
     * @throws InvalidDescriptionException Thrown when the metadata was not understood
     */
    private PluginDescription readDescription(File file) throws InvalidDescriptionException {
        JavaPluginDescription result = null;

        if (!file.exists()) {
            throw new InvalidDescriptionException(new FileNotFoundException(String.format("%s does not exist", file.getPath())));
        }
        try {
            JarFile jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("plugin.yml");
            if (entry == null) {
                throw new InvalidDescriptionException(new FileNotFoundException("Jar does not contain plugin.yml"));
            }

            InputStream stream = jar.getInputStream(entry);
            result = new JavaPluginDescription(this, file, stream, false);

            stream.close();
            jar.close();
        } catch (IOException ex) {
            throw new InvalidDescriptionException(ex);
        }
        return result;
    }

    /**
     * Similar to readDescription, but for system plugins.
     *
     * This builds a PluginDescription for a system plugin. Because a system
     * plugin does not have a containing JAR file, the file points directly
     * to the YAML description file.
     *
     * @param file The YAML description file
     * @return A filled PluginDescription object
     * @throws InvalidDescriptionException Thrown when the metadata was not understood
     */
    private PluginDescription readSystemPluginDescription(File file) throws InvalidDescriptionException {
        if (!file.exists()) {
            throw new InvalidDescriptionException(new FileNotFoundException(String.format("%s does not exist", file.getPath())));
        }
        JavaPluginDescription result = null;
        try {
            InputStream stream = new FileInputStream(file);
            result = new JavaPluginDescription(this, file, stream, true);
            stream.close();
        } catch (IOException ex) {
            throw new InvalidDescriptionException(ex);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Plugin enablePlugin(PluginDescription abstractDescription) throws InvalidPluginException {
        JavaPluginDescription description = (JavaPluginDescription)abstractDescription;
        JavaPlugin plugin = null;

        // The reflection voodoo, where we find the class and constructor.
        PluginClassLoader cloader = description.getClassLoader();
        Class<? extends JavaPlugin> pluginClass;
        try {
            Class<?> jarClass = Class.forName(description.getMain(), true, cloader);
            pluginClass = jarClass.asSubclass(JavaPlugin.class);
        }
        catch (ClassNotFoundException ex) {
            throw new InvalidPluginException(ex);
        }

        // Instantiate the plugin.
        pluginClassLoaders.add(cloader);
        try {
            try {
                Constructor<? extends JavaPlugin> constructor = pluginClass.getConstructor(Server.class, JavaPluginDescription.class);
                plugin = constructor.newInstance(server, description);
            } catch (NoSuchMethodException ex) {
                Constructor<? extends JavaPlugin> constructor = pluginClass.getConstructor();
                plugin = constructor.newInstance();
            }
        }
        catch (Throwable ex) {
            pluginClassLoaders.remove(cloader);
            throw new InvalidPluginException(ex, plugin);
        }

        // Set up private fields.
        plugin.initialize(server, description);

        // Install commands from plugin.yml.
        List<Command> pluginCommands = description.buildCommands(plugin);
        if (!pluginCommands.isEmpty()) {
            final CommandMap commandMap = server.getPluginManager().getCommandMap();
            commandMap.registerAll(plugin.getDescription().getName(), pluginCommands);
        }

        // Call the onEnable method.
        try {
            plugin.onEnable();
        }
        catch (Throwable ex) {
            pluginClassLoaders.remove(cloader);
            throw new InvalidPluginException(ex, plugin);
        }

        return plugin;
    }

    /**
     * {@inheritDoc}
     */
    public void disablePlugin(Plugin plugin) {
        JavaPlugin jPlugin = (JavaPlugin)plugin;
        JavaPluginDescription description = (JavaPluginDescription)plugin.getDescription();

        try {
            jPlugin.onDisable();
        }
        catch (Throwable ex) {
            log.log(Level.SEVERE, "Plugin " + description.getName() + " threw an exception while disabling.", ex);
        }

        pluginClassLoaders.remove(description.getClassLoader());
        description.clearClassLoader();
    }
}
