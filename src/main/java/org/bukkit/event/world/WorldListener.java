package org.bukkit.event.world;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

/**
 * Handles all World related events
 */
public class WorldListener implements Listener {

    public void onEvent(Event event) {
        switch (event.getType()) {
            // World Events
            case CHUNK_LOAD:
                this.onChunkLoad((ChunkLoadEvent) event);
                break;
                
            case CHUNK_POPULATED:
                this.onChunkPopulate((ChunkPopulateEvent) event);
                break;
                
            case CHUNK_UNLOAD:
                this.onChunkUnload((ChunkUnloadEvent) event);
                break;

            case SPAWN_CHANGE:
                this.onSpawnChange((SpawnChangeEvent) event);
                break;

            case WORLD_SAVE:
                this.onWorldSave((WorldSaveEvent) event);
                break;

            case WORLD_INIT:
                this.onWorldInit((WorldInitEvent) event);
                break;

            case WORLD_LOAD:
                this.onWorldLoad((WorldLoadEvent) event);
                break;
            case WORLD_UNLOAD:
                this.onWorldUnload((WorldUnloadEvent) event);
                break;

            case PORTAL_CREATE:
                this.onPortalCreate((PortalCreateEvent) event);
                break;
        }
    }

    /**
     * Called when a chunk is loaded
     *
     * @param event Relevant event details
     */
    public void onChunkLoad(ChunkLoadEvent event) {}

    /**
     * Called when a newly created chunk has been populated.
     *
     * If your intent is to populate the chunk using this event, please see {@link BlockPopulator}
     *
     * @param event Relevant event details
     */
    public void onChunkPopulate(ChunkPopulateEvent event) {}

    /**
     * Called when a chunk is unloaded
     *
     * @param event Relevant event details
     */
    public void onChunkUnload(ChunkUnloadEvent event) {}

    /**
     * Called when a World's spawn is changed
     *
     * @param event Relevant event details
     */
    public void onSpawnChange(SpawnChangeEvent event) {}

    /**
     * Called when the world generates a portal end point
     *
     * @param event Relevant event details
     */
    public void onPortalCreate(PortalCreateEvent event) {}

    /**
     * Called when a world is saved
     *
     * @param event Relevant event details
     */
    public void onWorldSave(WorldSaveEvent event) {}

    /**
     * Called when a World is initializing
     *
     * @param event Relevant event details
     */
    public void onWorldInit(WorldInitEvent event) {}

    /**
     * Called when a World is loaded
     *
     * @param event Relevant event details
     */
    public void onWorldLoad(WorldLoadEvent event) {}

    /**
     * Called when a World is unloaded
     *
     * @param event Relevant event details
     */
    public void onWorldUnload(WorldUnloadEvent event) {}
}
