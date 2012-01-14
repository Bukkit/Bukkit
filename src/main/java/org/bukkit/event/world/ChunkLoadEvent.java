package org.bukkit.event.world;

import org.bukkit.Chunk;
import org.bukkit.event.HandlerList;

/**
 * Called when a chunk is loaded
 */
@SuppressWarnings("serial")
public class ChunkLoadEvent extends ChunkEvent {
    private final boolean newChunk;

    public ChunkLoadEvent(final Chunk chunk, final boolean newChunk) {
        super(Type.CHUNK_LOAD, chunk);
        this.newChunk = newChunk;
    }

    /**
     * Gets if this chunk was newly created or not.
     * Note that if this chunk is new, it will not be populated at this time.
     *
     * @return true if the chunk is new, otherwise false
     */
    public boolean isNewChunk() {
        return newChunk;
    }
    
    private static final HandlerList handlers = new HandlerList();
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
