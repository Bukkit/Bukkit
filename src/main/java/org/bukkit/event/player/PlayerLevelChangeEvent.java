package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerLevelChangeEvent extends PlayerEvent {
    private final int oldLevel;
    private final int newLevel;

    public PlayerLevelChangeEvent(Player player, int oldLevel, int newLevel) {
        super(Type.PLAYER_LEVEL_CHANGE, player);
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    public int getOldLevel() {
        return oldLevel;
    }

    public int getNewLevel() {
        return newLevel;
    }
}
