
package org.bukkit.plugin;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

/**
 * Stores relevant information for plugin listeners
 */
public class RegisteredListener {
	private final Event.Type type;
    private final Listener listener;
    private final Event.Priority priority;
    private final Plugin plugin;

    public RegisteredListener(final Event.Type eventType, final Listener pluginListener, final Event.Priority eventPriority, final Plugin registeredPlugin) {
        type = eventType;
        listener = pluginListener;
        priority = eventPriority;
        plugin = registeredPlugin;
    }

    /**
	 * Gets the type for which this listener was registered
	 * @return Registered Type
	 */
	public Event.Type getType() {
		return type;
	}

    /**
     * Gets the listener for this registration
     * @return Registered Listener
     */
    public Listener getListener() {
        return listener;
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
}
