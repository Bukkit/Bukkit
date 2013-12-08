package org.bukkit.plugin.localization;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class YamlResourceBundle extends ResourceBundle {
    private FileConfiguration lookup;

    /**
     * Load a new YamlResource from a InputStream
     *
     * @param stream
     * @throws IOException if the stream could not be closed
     */
    public YamlResourceBundle(InputStreamReader stream) throws IOException {
        try {
            //Read from the InputStreamReader till he is empty
            BufferedReader br = new BufferedReader(stream);
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

            lookup = new YamlConfiguration();
            lookup.loadFromString(sb.toString());
        } catch (IOException e) {
            lookup = null;
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            lookup = null;
            e.printStackTrace();
        } finally {
            if(stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    throw e;
                }
            }
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
