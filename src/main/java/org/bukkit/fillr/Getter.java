package org.bukkit.fillr;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import java.util.logging.Level;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescription;
import org.bukkit.plugin.PluginManager;

public class Getter {
    private Server server;

    public Getter(Server server) {
        this.server = server;
    }

    public void get(String string, Player player) {
        FillReader reader = new FillReader(string);
        player.sendMessage("Downloading " + reader.getName() + " " + reader.getCurrVersion());
        if (new Downloader(server).download(reader.getFile())) {
            if (reader.getNotes() != null && !reader.getNotes().equals("")) {
                player.sendMessage("Notes: " + reader.getNotes());
            }
            player.sendMessage("Finished Download!");

            player.sendMessage("Loading " + reader.getName());
            enablePlugin(reader);
        }
    }

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
}
