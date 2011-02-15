package org.bukkit.plugin;

import org.bukkit.event.Event;

/**
 * Interface which defines the class for event call backs to plugins
 */
public interface EventExecutor {
    public void execute(Event event);
}