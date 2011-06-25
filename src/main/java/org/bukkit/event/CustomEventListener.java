package org.bukkit.event;

/**
 * Handles all custom events
 */
public class CustomEventListener implements Listener {

    public CustomEventListener() {}
    
    public void onEvent(Event event) {
        switch (event.getType()) {
            case CUSTOM_EVENT:
                this.onCustomEvent(event);
        }
    }

    /**
     * Called when a player joins a server
     *
     * @param event Relevant event details
     */
    public void onCustomEvent(Event event) {}
}
