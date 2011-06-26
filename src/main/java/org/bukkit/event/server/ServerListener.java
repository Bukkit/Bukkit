package org.bukkit.event.server;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Handles all miscellaneous server events
 */
public class ServerListener implements Listener {

    public void onEvent(Event event) {}
    
    /**
     * Called when a plugin is enabled
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLUGIN_ENABLE)
    public void onPluginEnable(PluginEnableEvent event) {}

    /**
     * Called when a plugin is disabled
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLUGIN_DISABLE)
    public void onPluginDisable(PluginDisableEvent event) {}

    /**
     * Called when a server command is used
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.SERVER_COMMAND)
    public void onServerCommand(ServerCommandEvent event) {}
}
