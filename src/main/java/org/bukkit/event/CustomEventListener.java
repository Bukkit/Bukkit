package org.bukkit.event;

import org.bukkit.event.Event.Type;

/**
 * Handles all custom events
 */
public class CustomEventListener implements Listener {

    public CustomEventListener() {
    }
    
    public void onEvent(Type type, Event event) {
        switch (type) {
            case CUSTOM_EVENT:
                this.onCustomEvent(event);
        }
    }

    /**
     * Called when a player joins a server
     *
     * @param event Relevant event details
     */
    public void onCustomEvent(Event event) {
    }
}
