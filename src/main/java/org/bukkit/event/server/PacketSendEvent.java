package org.bukkit.event.server;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class PacketSendEvent extends ServerEvent implements Cancellable {
	private Player player;
	private IPacket packet;
	private boolean cancelled = false;

	public PacketSendEvent(Player player, IPacket packet) {
		super(Type.PACKET_SEND);
		this.player = player;
		this.packet = packet;
	}

	/**
	 * Get the bukkit player associated with the the implementation's network
	 * handler
	 * 
	 * @return bukkit player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Returns the packet. For now, there are no "user-friendly" packets so
	 * normal ones are used.
	 * 
	 * @return bukkit player
	 */
	public IPacket getPacket() {
		return packet;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
}
