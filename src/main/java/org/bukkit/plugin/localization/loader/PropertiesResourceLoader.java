package org.bukkit.plugin.localization.loader;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.localization.ResourceLoadFailedException;
import org.bukkit.plugin.localization.ResourceLoader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertiesResourceLoader extends FileResourceLoader implements ResourceLoader {
    private Properties pro;
    private String file;
    private ArrayList<String> keys = new ArrayList<String>();

    /**
     * Empty Constructor template for the {@link org.bukkit.plugin.localization.ResourceManager#registerLoader(org.bukkit.plugin.localization.ResourceLoader)}
     */
    public PropertiesResourceLoader() {

    }

    /**
     * Load a new PropertiesResource
     *
     * @param plugin The plugin for which this Resource should be loaded
     * @param file The file to load
     * @throws org.bukkit.plugin.localization.ResourceLoadFailedException if the stream could not be closed
     */
    public PropertiesResourceLoader(JavaPlugin plugin, String file) throws ResourceLoadFailedException {
        super(plugin);

        this.file = file;

        try {
            load();
        } catch (ResourceLoadFailedException e) {
            throw e;
        }
    }

    private void load() throws ResourceLoadFailedException {
        InputStreamReader stream = null;
        try {
            //Get the correct InputStreamReader for this file
            stream = getFileInputStreamReader(file);

            //Try to parse the properties
            pro = new Properties();
            pro.load(stream);

            //Get the keys
            keys = new ArrayList<String>();
            for(Object key : pro.keySet()) {
                keys.add((String) key);
            }
        } catch (IOException e) {
            pro = null;
            throw new ResourceLoadFailedException(e);
        } catch (ResourceLoadFailedException e) {
            throw e;
        } finally {
            if(stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    throw new ResourceLoadFailedException(e);
                }
            }
        }
    }

    /**
     * Get all keys which can be handled by this Resource
     *
     * @return List of keys available
     */
    @Override
    public List<String> getKeys() {
        return keys;
    }

    /**
     * Get the key from the Properties
     *
     * @param key Key to get
     * @return The object from Properties or null if Properties loading was an error
     */
    @Override
    public String get(String key) {
        return pro != null ? (String) pro.get(key) : null;
    }

    /**
     * Get the Formats this Loader can load
     *
     * @return A List of String as formats this Loader supports
     */
    @Override
    public List<String> getFormats() {
        return Arrays.asList(".properties");
    }

    /**
     * Force the reload of this Resource
     *
     * @throws org.bukkit.plugin.localization.ResourceLoadFailedException
     */
    @Override
    public void reload() throws ResourceLoadFailedException {
        try {
            load();
        } catch (ResourceLoadFailedException e) {
            throw e;
        }
    }

    /**
     * If plugin gets unloaded remove all refs
     */
    @Override
    public void cleanup() {
        pro = null;
        file = null;

        super.cleanup();
    }
}
