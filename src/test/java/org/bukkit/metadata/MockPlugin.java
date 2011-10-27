package org.bukkit.metadata;

import com.avaje.ebean.EbeanServer;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.util.config.Configuration;

import java.io.File;
import java.io.InputStream;

/**
 * Including a mocking framework would be useful :)
 */
public class MockPlugin implements Plugin {
    public File getDataFolder() {
        return null;
    }

    public PluginDescriptionFile getDescription() {
        return null;
    }

    public Configuration getConfiguration() {
        return null;
    }

    public FileConfiguration getConfig() {
        return null;
    }

    public InputStream getResource(String filename) {
        return null;
    }

    public void saveConfig() {

    }

    public void reloadConfig() {

    }

    public PluginLoader getPluginLoader() {
        return null;
    }

    public Server getServer() {
        return null;
    }

    public boolean isEnabled() {
        return false;
    }

    public void onDisable() {

    }

    public void onLoad() {

    }

    public void onEnable() {

    }

    public boolean isNaggable() {
        return false;
    }

    public void setNaggable(boolean canNag) {

    }

    public EbeanServer getDatabase() {
        return null;
    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return null;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
