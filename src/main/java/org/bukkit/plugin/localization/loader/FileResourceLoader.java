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

    public FileResourceLoader() {

    }

    public FileResourceLoader(Plugin plugin) {
        this.plugin = plugin;
    }

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

    protected void cleanup() {
        this.plugin = null;
    }
}
