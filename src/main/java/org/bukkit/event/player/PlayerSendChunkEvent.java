package org.bukkit.event.player;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.entity.Player;

public class PlayerSendChunkEvent extends PlayerEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ChunkSnapshot chunkSnapshot;
	protected Chunk chunk;

	public PlayerSendChunkEvent(Type type, Player who, ChunkSnapshot chunkSnapshot, Chunk chunk) {
		super(type, who);
		this.chunkSnapshot = chunkSnapshot;
		this.chunk = chunk;
	}
	
	public ChunkSnapshot getChunkSnapshot(){
		return this.chunkSnapshot;
	}

	public Chunk getChunk(){
		return this.chunk;
	}
}
