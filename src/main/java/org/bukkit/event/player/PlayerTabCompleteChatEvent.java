package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.List;

public class PlayerTabCompleteChatEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final List<String> completions;
    private final String message;
    private final String lastWord;

    public PlayerTabCompleteChatEvent(Player player, String message, List<String> completions) {
        super(player);
        this.completions = completions;
        this.message = message;

        int lastSpace = message.lastIndexOf(' ');
        if (lastSpace == -1) {
            lastWord = message;
        } else {
            lastWord = message.substring(lastSpace + 1);
        }
    }

    /**
     * Gets the chat message to be completed.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Convenience method. Gets the last word of the message to be completed.
     *
     * @return the last word of the chat message
     */
    public String getLastWord() {
        return lastWord;
    }

    /**
     * Gets the list of tab-complete suggestions.
     *
     * @return the current list of suggestions for tab-complete
     */
    public List<String> getCompletions() {
        return completions;
    }

    /**
     * Convenience method. Adds tab-completions to the list if they are not
     * already present.
     *
     * @param suggestions a list of tab-completions to add
     */
    public void addCompletions(List<String> suggestions) {
        for (String suggestion : suggestions) {
            boolean add = true;
            for (String existing : this.completions) {
                if (existing.equalsIgnoreCase(suggestion)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                this.completions.add(suggestion);
            }
        }
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
