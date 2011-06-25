package org.bukkit.event.server;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.event.Listener;

/**
 * Handles all miscellaneous server events
 */
public class ServerListener implements Listener {

    public void onEvent(Type type, Event event) {
        switch (type) {
            case PLUGIN_ENABLE:
                this.onPluginEnable((PluginEnableEvent) event);
                break;
            case PLUGIN_DISABLE:
                this.onPluginDisable((PluginDisableEvent) event);
                break;
            case SERVER_COMMAND:
                this.onServerCommand((ServerCommandEvent) event);
                break;
        }
    }

    /**
     * Called when a plugin is enabled
     *
     * @param event Relevant event details
     */
    public void onPluginEnable(PluginEnableEvent event) {
    }

    /**
     * Called when a plugin is disabled
     *
     * @param event Relevant event details
     */
    public void onPluginDisable(PluginDisableEvent event) {
    }

    /**
     * Called when a server command is used
     *
     * @param event Relevant event details
     */
    public void onServerCommand(ServerCommandEvent event) {
    }
}
