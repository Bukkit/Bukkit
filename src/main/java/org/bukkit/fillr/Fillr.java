package org.bukkit.fillr;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.*;
import org.bukkit.event.*;

public class Fillr extends JavaPlugin {
    public static final String NAME = "Fillr";
    public static final String VERSION = "1.0";
    public static final String DIRECTORY = "plugins";

    private static final Logger logger = Logger.getLogger(Fillr.class.getName());

    public void onEnable() {
        if (getServer().getPluginManager() instanceof SimplePluginManager) {
            registerEvents();
        } else {
            logger.log(Level.WARNING, "Fillr only works with SimplePluginManager");
        }
    }

    public void onDisable() {
    }

    private void registerEvents() {
        FillrListener listener = new FillrListener(getServer());
        registerEvent(Event.Type.PLAYER_COMMAND, Event.Priority.Normal, listener);
    }
}
