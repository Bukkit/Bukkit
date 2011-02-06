package org.bukkit.fillr;

import org.bukkit.entity.Player;
import org.bukkit.*;
import org.bukkit.plugin.*;

import java.util.logging.Level;

public class Updater {
    private final Server server;

    Updater(Server server) {
        this.server = server;
    }

    /**
     * Checks and updates all installed plugins
     *
     * @param player The player to send info to
     */
    void updateAll(Player player) {
        PluginDescription[] descriptions = server.getPluginManager().getPluginDescriptions();
        if (descriptions.length == 0) {
            player.sendMessage("No plugins to update.");
        } else {
            player.sendMessage("Updating " + descriptions.length + " plugins:");
            for (PluginDescription description : descriptions) {
                FillReader reader = Checker.needsUpdate(description);
                if (reader != null) {
                    update(reader, player);
                }
            }
            player.sendMessage("Updates completed.");
        }
    }

    /**
     * Checks and updates the given plugin
     *
     * @param name The name of the plugin
     * @param player The player to send info to
     */
    void update(String name, Player player) {
        PluginDescription description = server.getPluginManager().getPluginDescription(name);
        if (description != null) {
            FillReader reader = Checker.needsUpdate(description);
            if (reader != null) {
                update(reader, player);
            } else {
                player.sendMessage(name + " is up to date");
            }
        } else {
            player.sendMessage("Can't find " + name);
        }
    }

    /**
     * Downloads the plugin specified by the FillReader
     *
     * @param update The FillReader with all the plugin info
     * @param player The player to send info to
     */
    private void update(FillReader update, Player player) {
        player.sendMessage("Disabling " + update.getName() + " for update");
        disablePlugin(update);

        player.sendMessage("Downloading " + update.getName() + " " + update.getCurrVersion());
        try {
            if (new Downloader(server).download(update.getFile())) {
                if (update.getNotes() != null && !update.getNotes().equals("")) {
                    player.sendMessage("Notes: " + update.getNotes());
                }
                player.sendMessage("Finished Download!");
            }
        } finally {
            player.sendMessage("Re-enabling " + update.getName());
            enablePlugin(update);
        }
    }

    /**
     * Enables the plugin specified by the FillReader
     *
     * @param update The FillReader with all the plugin info
     */
    private void enablePlugin(FillReader update) {
        final String name = update.getName();

        PluginManager pm = server.getPluginManager();
        Plugin oldPlugin = pm.getPlugin(name);
        if (oldPlugin != null) {
            pm.disablePlugin(oldPlugin);
        }

        pm.rebuildIndex();
        PluginDescription description = pm.getPluginDescription(name);
        if (description == null) {
            server.getLogger().log(Level.SEVERE,
                    "Could not re-enable plugin, it was no longer in the index after update.");
        } else {
            pm.enablePlugin(description);
        }
    }

    /**
     * Disables the plugin specified by the FillReader
     *
     * @param update The FillReader with all the plugin info
     */
    private void disablePlugin(FillReader update) {
        PluginManager pm = server.getPluginManager();
        Plugin plugin = pm.getPlugin(update.getName());
        if (plugin != null) {
            pm.disablePlugin(plugin);
        }
    }
}
