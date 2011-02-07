package org.bukkit.plugin.java;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.YamlPluginDescription;

public final class JavaPluginDescription extends YamlPluginDescription {
    private String main;

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
