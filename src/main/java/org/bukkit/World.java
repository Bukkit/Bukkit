
package org.bukkit;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.bukkit.entity.Creature;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDrop;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Boat;

/**
 * Represents a world.
 *
 * Currently there is only one world in the default Minecraft spec, but this
 * may change with the addition of a functional Nether world
 */
public interface World {
    /**
     * Gets the block at the given location
     *
     * This block will always represent the latest state
     *
     * @param x X-coordinate of the block
     * @param y Y-coordinate of the block
     * @param z Z-coordinate of the block
     * @return Block at the given location
     */
    public Block getBlockAt(int x, int y, int z);

    /**
     * Gets the block type-id at the given location
     *
     * @param x X-coordinate of the block
     * @param y Y-coordinate of the block
     * @param z Z-coordinate of the block
     * @return TypeId of the block at the given location
     */
    public int getBlockTypeIdAt(int x, int y, int z);

    /**
     * Gets the highest non-air coordinate at the given (x,z) location
     * @param x X-coordinate of the blocks
     * @param z Z-coordinate of the blocks
     * @return Y-coordinate of the highest non-air block
     */
    public int getHighestBlockYAt(int x, int z);

    /**
     * Gets the chunk at the given location
     *
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @return Chunk at the given location
     */
    public Chunk getChunkAt(int x, int z);

    /**
     * Gets the chunk which contains the given block
     *
     * @param block Block to get the parent chunk from
     * @return Chunk that contains the given block
     */
    public Chunk getChunkAt(Block block);

    /**
     * Checks if the specified chunk is loaded
     *
     * @return true if the chunk is loaded, otherwise false
     */
    public boolean isChunkLoaded(Chunk chunk);

    /**
     * Gets an array of all loaded chunks
     *
     * @return Chunk array of all loaded chunks
     */
    public Chunk[] getLoadedChunks();

    /**
     * Loads the specified chunk
     *
     */
    public void loadChunk(Chunk chunk);

    /**
     * Checks if the chunk at the specified coordinates is loaded
     *
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @return true if the chunk is loaded, otherwise false
     */
    public boolean isChunkLoaded(int x, int z);

    /**
     * Loads the chunk at the specified coordinates and generates the chunk when it is non-existing
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     */
    public void loadChunk(int x, int z);

    /**
     * Loads the chunk at the specified coordinates and generates the chunk when it is non-existing if generate is enabled
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @param generate Controls whether non-generated chunks are generated
     * @return Whether the chunk has loaded
     */
    public boolean loadChunk(int x, int z, boolean generate);

    /**
     * Safely unloads and saves the chunk at the specified coordinates
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @return Whether the chunk was actually unloaded
     */
    public boolean unloadChunk(int x, int z);

    /**
     * Safely unloads and optionally saves the chunk at the specified coordinates
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @param save Controls whether the chunk is saved
     * @return Whether the chunk was actually unloaded
     */
    public boolean unloadChunk(int x, int z, boolean save);

    /**
     * Unloads and optionally saves the chunk at the specified coordinates
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @param save Controls whether the chunk is saved
     * @param safe Controls whether to unload the chunk when players are nearby
     * @return Whether the chunk was actually unloaded
     */
    public boolean unloadChunk(int x, int z, boolean save, boolean safe);

    /**
     * Safely queues the chunk at the specified coordinates for unloading
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @return Whether the chunk was actually queued
     */
    public boolean unloadChunkRequest(int x, int z);

    /**
     * Queues the chunk at the specified coordinates for unloading
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @param safe Controls whether to queue the chunk when players are nearby
     * @return Whether the chunk was actually queued
     */
    public boolean unloadChunkRequest(int x, int z, boolean safe);

    /**
     * Drop an item exactly at the specified location.
     *
     * @param loc
     * @param item
     * @return dropped item entity
     */
    public ItemDrop dropItem(Location loc, ItemStack item);

    /**
     * Drop an item as if it was mined (randomly placed).
     *
     * @param loc
     * @param item
     * @return dropped item entity
     */
    public ItemDrop dropItemNaturally(Location loc, ItemStack item);

    /**
     * Spawns an arrow.
     *
     * @param loc
     * @param velocity velocity vector
     * @param speed a reasonable speed is 0.6
     * @param spread a reasonable spread is 12
     * @return the arrow entity
     */
    public Arrow spawnArrow(Location loc, Vector velocity, float speed, float spread);

    /**
     * Spawns a tree at a location.
     *
     * @param loc
     * @param type
     * @return whether the tree was created
     */
    public boolean generateTree(Location loc, TreeType type);

    /**
     * Spawns a tree at a location.
     *
     * @param loc
     * @param type
     * @param delegate
     * @return whether the tree was created
     */
    public boolean generateTree(Location loc, TreeType type, BlockChangeDelegate delegate);

    /**
     * Spawns a regular passenger minecart.
     *
     * @param loc
     * @return
     */
    public Minecart spawnMinecart(Location loc);

    /**
     * Spawns a storage minecart.
     *
     * @param loc
     * @return
     */
    public StorageMinecart spawnStorageMinecart(Location loc);

    /**
     * Spawns a powered minecart.
     *
     * @param loc
     * @return
     */
    public PoweredMinecart spawnPoweredMinecart(Location loc);

    /**
     * Spawn a boat.
     *
     * @param loc
     * @return
     */
    public Boat spawnBoat(Location loc);

    /**
     * Spawn a creature at the given location.
     * 
     * @param loc The location to spawn at.
     * @param creatureType The creature to spawn.
     * 
     * @return The Creature if it was spawned, null otherwise.
     */
    public Creature spawnCreature(Location loc, CreatureType creatureType);

    /**
     * Get a list of all entities.
     *
     * @return
     */
    public List<Entity> getEntities();

    /**
     * Get a list of all living entities.
     *
     * @return
     */
    public List<LivingEntity> getLivingEntities();

    /**
     * Gets the name of this world. This is not guaranteed to be unique.
     *
     * @return Name of this world
     */
    public String getName();

    /**
     * Gets a semi-unique identifier for this world. While it is highly unlikely
     * that this may be shared with another World, it is not guaranteed to be
     * unique.
     *
     * @return Id of this world
     */
    public long getId();

    /**
     * Gets the default spawn location.
     */
    public Location getSpawnLocation();

    /**
     * Gets the relative in-game time on this world (in hours*1000)
     *
     * @return The current relative time in hours*1000
     * @see getFullTime
     */
    public long getTime();

    /**
     * Sets the relative in-game time on the server (in hours*1000)<br />
     * <br />
     * Note that setting the relative time below the current relative time will
     * actually move the clock forward a day. If you require to rewind time, please
     * see setFullTime
     *
     * @param time The new relative time to set the in-game time to (in hours*1000)
     * @see setFullTime
     */
    public void setTime(long time);

    /**
     * Gets the full in-game time on this world (in hours*1000)
     *
     * @return The current time in hours*1000
     * @see setTime
     */
    public long getFullTime();

    /**
     * Sets the in-game time on the server (in hours*1000)<br />
     * <br />
     * Note that this sets the full time of the world, which may cause adverse
     * effects such as breaking redstone clocks and any scheduled events
     *
     * @param time The new time to set the in-game time to (in hours*1000)
     * @see setTime
     */
    public void setFullTime(long time);

    /**
     * Gets the environment type of this world
     *
     * @return This worlds Environment type
     */
    public Environment getEnvironment();

    /**
     * Represents various map environment types that a world may be
     */
    public enum Environment {
        /**
         * Represents the "normal"/"surface world" map
         */
        NORMAL,

        /**
         * Represents a nether based map
         */
        NETHER
    }
}
