
package org.bukkit.event.server;

import org.bukkit.event.Listener;

/**
 * Handles all miscellaneous server events
 */
public class ServerListener implements Listener {
    /**
     * Called when a plugin is enabled
     *
     * @param event Relevant event details
     */
    public void onPluginEnabled(PluginEvent event) {
    }

    /**
     * Called when a plugin is disabled
     *
     * @param event Relevant event details
     */
    public void onPluginDisabled(PluginEvent event) {
    }
    
    /**
     * Called when a timer has expired
     *
     * @param event Relevant event details
     */
    public void onTimerExpired(TimerEvent event) {
    }
    
    /**
     * Called when a timer is unscheduled
     *
     * @param event Relevant event details
     */
    public void onTimerUnregistered(TimerEvent event) {
    }
}
