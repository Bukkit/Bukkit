package org.bukkit.plugin.java;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.YamlPluginDescription;

public final class JavaPluginDescription extends YamlPluginDescription {
    private static final Logger log = Logger.getLogger(JavaPluginDescription.class.getName());
    private final boolean systemPlugin;
    private String main;
    private PluginClassLoader classLoader = null;

    public JavaPluginDescription(final PluginLoader loader, final File file,
            final InputStream stream, final boolean systemPlugin) throws InvalidDescriptionException {
        super(loader, file, stream);
        this.systemPlugin = systemPlugin;
    }

    /**
     * Returns the main class for a plugin
     *
     * @return Java classpath
     */
    public String getMain() {
        return main;
    }

    /**
     * Returns the ClassLoader which holds this plugin
     *
     * The ClassLoader instance is lazily instantiated as an instance of
     * {@link PluginClassLoader}. Using a separate ClassLoader ensures the JRE
     * can properly unload the plugin classes at runtime. It's also used to
     * share classes among plugins.
     *
     * @return ClassLoader holding this plugin
     */
    protected PluginClassLoader getClassLoader() {
        if (classLoader == null) {
            final JavaPluginLoader pluginLoader = (JavaPluginLoader)getLoader();
            final ClassLoader parentLoader = getClass().getClassLoader();
            final ArrayList<URL> urls = new ArrayList<URL>();
            if (!systemPlugin) {
                try {
                    urls.add(getFile().toURI().toURL());
                }
                catch (MalformedURLException ex) {
                    log.log(Level.SEVERE, "Unable to turn JAR-path into URL", ex);
                }
            }
            classLoader = new PluginClassLoader(pluginLoader, urls.toArray(new URL[0]), parentLoader);
        }
        return classLoader;
    }

    /**
     * Clear the ClassLoader instance, if there is one.
     *
     * This allows the plugin to be garbage collected.
     */
    public void clearClassLoader() {
        classLoader = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadMap(Map<String, Object> map) throws InvalidDescriptionException {
        super.loadMap(map);

        try {
            main = map.get("main").toString();
        } catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "main is not defined");
        } catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, "main is of wrong type");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveMap(Map<String, Object> map) {
        super.saveMap(map);

        map.put("main", main);
    }

    /**
     * {@inheritDoc}
     */
    protected Command createCommand(Plugin plugin, String name) {
        return new JavaPluginCommand(plugin, name);
    }
}
