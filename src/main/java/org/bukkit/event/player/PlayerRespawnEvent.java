package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.location.DirectionalLocation;

public class PlayerRespawnEvent extends PlayerEvent {

    private DirectionalLocation respawnLocation;
    private boolean isBedSpawn;

    public PlayerRespawnEvent(Player respawnPlayer, DirectionalLocation respawnLocation, boolean isBedSpawn) {
        super(Type.PLAYER_RESPAWN, respawnPlayer);
        this.respawnLocation = respawnLocation;
        this.isBedSpawn = isBedSpawn;
    }

    /**
     * Gets the current respawn location
     *
     * @return Location current respawn location
     */
    public DirectionalLocation getRespawnLocation() {
        return this.respawnLocation;
    }

    /**
     * Sets the new respawn location
     *
     * @param respawnLocation new location for the respawn
     */
    public void setRespawnLocation(DirectionalLocation respawnLocation) {
        this.respawnLocation = respawnLocation;
    }

    /**
     * Gets whether the respawn location is the player's bed.
     *
     * @return true if the respawn location is the player's bed.
     */
    public boolean isBedSpawn() {
        return this.isBedSpawn;
    }
}
