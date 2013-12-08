package org.bukkit.event.player;

import org.bukkit.ChatMode;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Called when the Settings of the player is changed.
 */
public class PlayerSettingsChangeEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final String newLocale;
    private final ChatMode newChatMode;
    private final int newViewDistance;

    public PlayerSettingsChangeEvent(final Player player, final String newLocale, final ChatMode newChatMode, final int newViewDistance) {
        super(player);
        this.newLocale = newLocale;
        this.newViewDistance = newViewDistance;
        this.newChatMode = newChatMode;
    }

    /**
     * Get the Players new Locale which he has selected in the Client
     *
     * @return The locale in ISO Format
     */
    public String getNewLocale() {
        return newLocale;
    }

    /**
     * Get the Players Chat Settings
     *
     * @return ChatMode Enum if a valid one was found otherwise its null
     */
    public ChatMode getNewChatMode() {
        return newChatMode;
    }

    /**
     * Get the Players View distance set in the Client
     *
     * @return View distance
     */
    public int getNewViewDistance() {
        return newViewDistance;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
