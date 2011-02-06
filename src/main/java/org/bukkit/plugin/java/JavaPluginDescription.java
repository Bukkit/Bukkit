package org.bukkit.plugin.java;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.YamlPluginDescription;

public final class JavaPluginDescription extends YamlPluginDescription {
    private static final Logger logger = Logger.getLogger(JavaPluginDescription.class.getName());
    private String main;
    private ClassLoader classLoader = null;

    public JavaPluginDescription(final PluginLoader loader, final File file, final InputStream stream) throws InvalidDescriptionException {
        super(loader, file, stream);
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
     * @return ClassLoader holding this plugin
     */
    protected ClassLoader getClassLoader() {
        if (classLoader == null) {
            final JavaPluginLoader pluginLoader = (JavaPluginLoader)getLoader();
            final ClassLoader parentLoader = getClass().getClassLoader();
            final ArrayList<URL> urls = new ArrayList<URL>();
            try {
                urls.add(getFile().toURI().toURL());
            }
            catch (MalformedURLException ex) {
                logger.log(Level.SEVERE, "Unable to turn JAR-path into URL", ex);
            }
            classLoader = new PluginClassLoader(pluginLoader, urls.toArray(new URL[0]), parentLoader);
        }
        return classLoader;
    }

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

    @Override
    protected void saveMap(Map<String, Object> map) {
        super.saveMap(map);

        map.put("main", main);
    }
}
