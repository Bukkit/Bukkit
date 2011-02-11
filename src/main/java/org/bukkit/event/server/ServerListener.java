
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
     * Called when a server command is used
     *
     * @param event Relevant event details
     */
    public void onServerCommand(ServerCommandEvent event) {
    }
    
    /**
     * Called when a user gets banned
     *
     * @param event Relevant event details
     */    
    public void onUserBan(UserBanEvent event) {
    }
    
    /**
     * Called when a user gets unbanned
     *
     * @param event Relevant event details
     */
    public void onUserUnban(UserUnbanEvent event) {
    }
    
    /**
     * Called when an IP gets banned
     *
     * @param event Relevant event details
     */
    public void onIpBan(IpBanEvent event) {
    }
    
    /**
     * Called when an IP gets unbanned
     *
     * @param event Relevant event details
     */
    public void onIpUnban(IpUnbanEvent event) {
    }
}
