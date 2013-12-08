package org.bukkit.plugin.localisation;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class YamlResourceBundle extends ResourceBundle {
    private FileConfiguration lookup;

    /**
     * Load a new YamlResource from a InputStream
     *
     * @param stream
     */
    public YamlResourceBundle(InputStream stream) {
        try {
            lookup = new YamlConfiguration();
            lookup.load(stream);
        } catch (IOException e) {
            lookup = null;
        } catch (InvalidConfigurationException e) {
            lookup = null;
        }
    }

    /**
     * Get all keys which can be handled by this Resource
     *
     * @return Collection of keys available
     */
    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(lookup.getKeys(true));
    }

    /**
     * Get the key from the YamlConfiguration
     *
     * @param key Key to get
     * @return The object from YAML or null if YAML loading was an error
     */
    @Override
    protected Object handleGetObject(String key) {
        return lookup != null ? lookup.get(key) : null;
    }
}
