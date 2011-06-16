package org.bukkit.event.world;

import org.bukkit.Chunk;

/**
 * Called when a chunk is created for the first time
 */
public class ChunkCreateEvent extends ChunkEvent {
    public ChunkCreateEvent(final Chunk chunk) {
        super(Type.CHUNK_CREATE, chunk);
    }
}
