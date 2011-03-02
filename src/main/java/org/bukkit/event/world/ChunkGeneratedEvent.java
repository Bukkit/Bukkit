
package org.bukkit.event.world;

import org.bukkit.Chunk;

/**
 * Called when a chunk is generated
 */
public class ChunkGeneratedEvent extends WorldEvent {
    private final Chunk chunk;
    private byte[] typeId = null;

    public ChunkGeneratedEvent(final Type type, final Chunk chunk) {
        super(type, chunk.getWorld());

        this.chunk = chunk;

        this.typeId = typeId;
    }

    /**
     * Gets the chunk being loaded/unloaded
     *
     * @return Chunk that triggered this event
     */
    public Chunk getChunk() {
        return chunk;
    }

    /**
     * Sets the byte array and sets override to true.  The byte array must be 32768 bytes long.
     *
     * @param typeId Array containing byte data
     * @return Returns true if the byte array is accepted
     */
    public boolean setTypeIdArray(byte[] typeId) {
        if(typeId.length == 32768) {
            this.typeId = typeId;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the byte array and sets override to true.  The byte array must be 32768 bytes long.
     *
     * @return Returns the byte array
     */
    public byte[] getTypeIdArray() {
        return this.typeId;
    }

}
