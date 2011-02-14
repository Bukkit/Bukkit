
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
