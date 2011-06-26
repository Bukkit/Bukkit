package org.bukkit.event;

/**
 * Handles all custom events
 */
public class CustomEventListener implements Listener {

    public CustomEventListener() {}
    
    public void onEvent(Event event) {}

    /**
     * Called when a player joins a server
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.CUSTOM_EVENT)
    public void onCustomEvent(Event event) {}
}
