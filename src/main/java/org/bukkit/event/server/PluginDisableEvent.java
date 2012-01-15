package org.bukkit.event.server;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

/**
 * Called when a plugin is disabled.
 */
@SuppressWarnings("serial")
public class PluginDisableEvent extends PluginEvent {
    public PluginDisableEvent(Plugin plugin) {
        super(Type.PLUGIN_DISABLE, plugin);
    }
    
    private static final HandlerList handlers = new HandlerList();
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
