package org.bukkit.fillr;

import org.bukkit.entity.Player;
import org.bukkit.*;
import org.bukkit.plugin.*;

public class Checker {
    private Server server;

    public Checker(Server server) {
        this.server = server;
    }

    /**
     * Checks all the installed plugins for updates
     *
     * @param player The player to send info to
     */
    public void check(Player player) {
        PluginDescription[] descriptions = server.getPluginManager().getPluginDescriptions();
        if (descriptions.length == 0) {
            player.sendMessage("No plugins to update.");
        } else {
            player.sendMessage("Status for " + descriptions.length + " plugins:");
            for (PluginDescription description : descriptions) {
                checkForUpdate(description, player);
            }
        }
    }

    /**
     * Checks for an update for a given plugin
     *
     * @param description The plugin to check for an update
     * @param player The player to send info to
     */
    private void checkForUpdate(PluginDescription description, Player player) {
        FillReader reader = needsUpdate(description);
        if (reader != null) {
            player.sendMessage(ChatColor.RED + reader.getName() + " " + description.getVersion()
                    + " has an update to " + reader.getCurrVersion());
        } else {
            player.sendMessage(description.getName() + " " + description.getVersion()
                    + " is up to date!");
        }
    }

    /**
     * Compares the version of the installed plugin with the repository version
     *
     * @param description The plugin to check for an update
     * @return If an update is needed, the reader for the repository, otherwise
     *         null
     */
    public static FillReader needsUpdate(PluginDescription description) {
        FillReader reader = new FillReader(description.getName());
        String version = description.getVersion();
        String currVersion = reader.getCurrVersion();
        if (currVersion.equalsIgnoreCase(version) && description.getFile().exists()) {
            return null;
        } else {
            return reader;
        }
    }
}