package org.bukkit.event.world;

import org.bukkit.Chunk;
import org.bukkit.event.Cancellable;

public class ChunkGeneratedEvent extends ChunkLoadEvent {
    public ChunkGeneratedEvent(final Type type, final Chunk chunk) {
        super(type, chunk);
    }
}