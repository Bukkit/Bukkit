package org.bukkit.plugin.localization.loader;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.localization.ResourceLoadFailedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public abstract class FileResourceLoader {
    protected Plugin plugin;

    /**
     * Empty Constructor template for the {@link org.bukkit.plugin.localization.ResourceManager#registerLoader(org.bukkit.plugin.localization.ResourceLoader)}
     */
    public FileResourceLoader() {

    }

    /**
     * Load a new FileResource. Every Loader which want to read from either the JAR or the Filesystem should extend this Class.
     * It first checks if the File can be found on the Disk and then if not it gets read from the Jar
     *
     * @param plugin The plugin from and for which this Resource should be loaded
     */
    public FileResourceLoader(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Gets the Resource at the given relative path. It first searches the Plugins Datafolder and then it checks the JAR for
     * an entry in it.
     *
     * @param path The path which should be loaded
     * @return An InputStreamReader for the Resource (either from out of the JAR or from disk)
     * @throws ResourceLoadFailedException if the Encoding is wrong or the File was not found
     */
    protected InputStreamReader getFileInputStreamReader(String path) throws ResourceLoadFailedException {
        File file = new File(plugin.getDataFolder(), path);

        try {
            if (file.isFile()) {
                //File is on the Disk read from it
                return new InputStreamReader(new FileInputStream(file), "UTF8");
            } else {
                //If the file is not on the Disk read it from the JAR
                return new InputStreamReader(plugin.getResource(path), "UTF8");
            }
        } catch (UnsupportedEncodingException e) {
            throw new ResourceLoadFailedException(e);
        } catch (FileNotFoundException e) {
            throw new ResourceLoadFailedException(e);
        }
    }

    /**
     * Remove the Reference on the Plugin
     */
    protected void cleanup() {
        this.plugin = null;
    }
}
