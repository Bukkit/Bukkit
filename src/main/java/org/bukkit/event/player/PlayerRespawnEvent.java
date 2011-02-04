package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerRespawnEvent extends PlayerEvent {
	private Location respawnLocation;
	
	public PlayerRespawnEvent(Type type, Player respawnPlayer, Location respawnLocation) {
		super(type, respawnPlayer);
		this.respawnLocation = respawnLocation;
	}

	public Location getRespawnLocation() {
		return this.respawnLocation;
	}
	
	public void setRespawnLocation(Location respawnLocation) {
		this.respawnLocation = respawnLocation;
	}
}
