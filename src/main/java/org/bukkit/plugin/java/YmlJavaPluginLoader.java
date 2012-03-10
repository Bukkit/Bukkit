package org.bukkit.plugin.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import org.bukkit.Server;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * Represents a Java plugin loader which loads plugins from class path, read from .yml files.
 */
public class YmlJavaPluginLoader extends JavaPluginLoader {

    protected final Pattern[] fileFilters = new Pattern[] { Pattern.compile("\\.yml$"), };

    public YmlJavaPluginLoader(Server instance) {
        super(instance);
    }

    @Override
    public PluginDescriptionFile getPluginDescription(File file) throws InvalidDescriptionException {
        try {
            return getYmlPluginDescription(file);
        } catch (IOException e) {
            throw new InvalidDescriptionException(e);
        }
    }

    private PluginDescriptionFile getYmlPluginDescription(File file) throws InvalidDescriptionException, IOException {
        InputStream stream = new FileInputStream(file);
        try {
            return new PluginDescriptionFile(stream);
        } finally {
            stream.close();
        }
    }

    @Override
    public Pattern[] getPluginFileFilters() {
        return fileFilters;
    }

}
