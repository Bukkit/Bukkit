
package org.bukkit.event.world;

import org.bukkit.event.Listener;

/**
 * Handles all World related events
 */
public class WorldListener implements Listener {
    /**
     * Called when a chunk is loaded
     *
     * @param event Relevant event details
     */
    public void onChunkLoaded(ChunkLoadEvent event) {
    }

    /**
     * Called when a chunk is unloaded
     *
     * @param event Relevant event details
     */
    public void onChunkUnloaded(ChunkUnloadEvent event) {
    }

   /**
     * Called when a chunk is generated
     *
     * @param event Relevant event details
     */
    public void onChunkGenerated(ChunkGeneratedEvent event) {
    }

   /**
     * Called when a chunk is decorated
     * This occurs shortly after the chunk is generated
     * It is guaranteed that the 3 neighbouring chunks (x+1, z), (x+1, z+1), (x, z+1) will be loaded
     *
     * @param event Relevant event details
     */
    public void onChunkDecorated(ChunkDecoratedEvent event) {
    }

   /**
    * Called when a world is saved
    *
    * param event Relevant event details
    */
    public void onWorldSaved(WorldEvent event) {
    }

    /**
     * Called when a World is loaded
     *
     * @param event Relevant event details
     */
    public void onWorldLoaded(WorldEvent event) {
    }
}
