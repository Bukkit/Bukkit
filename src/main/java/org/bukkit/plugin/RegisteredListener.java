package org.bukkit.plugin;

import org.bukkit.event.Event;
import org.bukkit.plugin.EventExecutor;

/**
 * Stores relevant information for plugin listeners
 */
public class RegisteredListener {
    private final Event.Priority priority;
    private final Plugin plugin;
    private final EventExecutor executor;

    public RegisteredListener(final Plugin plugin, final Event.Priority priority, final EventExecutor executor) {
        this.priority = priority;
        this.plugin = plugin;
        this.executor = executor;
    }

    /**
     * Gets the plugin for this registration
     * @return Registered Plugin
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the priority for this registration
     * @return Registered Priority
     */
    public Event.Priority getPriority() {
        return priority;
    }

    /**
     * Calls the event executor
     * @return Registered Priority
     */
    public void callEvent(Event event) {
        executor.execute(event);
    }
}
