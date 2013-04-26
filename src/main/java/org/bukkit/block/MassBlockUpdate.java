package org.bukkit.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;

/**
 * Represents a mass block update in a world.  This provides a
 * transactional-style interface for updating a large collection of
 * blocks at once.
 * <p>
 * Use {@link Server#createMassBlockUpdate(Plugin)} to create a new
 * MassBlockUpdate object.
 */
public interface MassBlockUpdate {

    /**
     * Prepare a block update at the given coordinates.  The change
     * will not actually be made until {@link #applyUpdates()} is
     * called.
     * <p>
     * Either this method <em>or</em>
     * {@link #setBlockUpdateProvider(Iterable)} should be used to
     * prepare block updates; mixing both methods is not allowed.
     *
     * @param record a {@link BlockUpdateRecord} object describing the location and new block type and data
     * @throws IllegalStateException if this is called after {@link #setUpdateProvider(Iterable)} has been called
     */
    public void addBlockUpdate(BlockUpdateRecord record);

    /**
     * Specify an object which will provide {@link BlockUpdateRecord}
     * objects on-demand when {@link #applyUpdates()} is called.
     * <p>
     * Either this method <em>or</em>
     * {@link #addBlockUpdate(BlockUpdateRecord)} should be used to
     * prepare block updates; mixing both methods is not allowed.
     *
     * @param updates an object which implements {@link Iterable}
     * @throws IllegalStateException if this is called after {@link #addBlockUpdate(BlockUpdateRecord)} has been called
     * @throws IllegalArgumentException if the update provider object is null
     */
    public void setBlockUpdateProvider(Iterable<BlockUpdateRecord> updates);

    /**
     * Apply all the pending block changes for this object.
     * <p>
     * Note that this may be run over multiple server ticks, depending
     * on the number of changes to be made.
     */
    public void applyUpdates();

    /**
     * Specify the maximum time per tick that should be spent applying
     * block updates once {@link #applyUpdates()} is called.  The
     * default is 5%.  Raising this value will mean large updates are
     * applied sooner, but increases the risk of server-side lag.
     *
     * @param percent the percentage of one server tick that should be spent applying block updates
     * @throws IllegalArgumentException if the percentage is not in the range 1 to 100 inclusive
     */
    public void setMaxUpdateTimePerTick(int percent);

    /**
     * Set the initial size of the pending updates buffer when updating
     * with {@link #addBlockUpdate(BlockUpdateRecord)}.
     * <p>
     * If you know in advance the number of block updates to be made,
     * it is recommended to use this to avoid the overhead of buffer
     * reallocation while preparing the block updates.
     *
     * @param size the size of the update buffer to preallocate
     * @throws IllegalStateException if this method is called after {@link #setBlockUpdateProvider(Iterable)} has been called
     * @throws IllegalArgumentException if the size is less than 0
     */
    public void setUpdateBufferSize(int size);

    /**
     * Set a static light level to apply to all blocks when making
     * block updates.
     * <p>
     * The default, if this method is not called, is to dynamically
     * calculate each block's natural light level.
     *
     * @param level the brightness level
     * @throws IllegalArgumentException if the brightness level is not in the range 0 to 15 inclusive
     */
    public void setStaticLightLevel(int level);

    /**
     * Set an object to notify when all block updates have been
     * completed.
     *
     * @param callback the object to notify, must implement {@link Runnable}
     */
    public void setNotifyOnCompletion(Runnable callback);

    /**
     * Represents a single block update.
     */
    public class BlockUpdateRecord {
        private final Location loc;
        private int materialId;
        private byte data;

        /**
         * Construct a new block update record for the given location
         * and material.
         * <p>
         * If the new block data is known in advance, consider using
         * the {@link BlockUpdateRecord(Location, Material, byte)}
         * constructor instead.
         *
         * @param loc the location at which the update should be applied
         * @param material the new material to apply to the location
         */
        public BlockUpdateRecord(Location loc, Material material) {
            this(loc, material, loc.getBlock().getData());
        }

        /**
         * Construct a new block update record for the given location,
         * material and data.
         *
         * @param loc the location at which the update should be applied
         * @param material the new material to apply to the location
         * @param data the new data to apply to the location
         */
        public BlockUpdateRecord(Location loc, Material material, byte data) {
            this.loc = loc.clone();
            this.materialId = material.getId();
            this.data = data;
        }

        /**
         * Get the location for this update record.
         *
         * @return
         */
        public Location getLocation() {
            return loc;
        }

        /**
         * Get the material for this update record.
         *
         * @return the material
         */
        public Material getMaterial() {
            return Material.getMaterial(materialId);
        }

        /**
         * Set the material for this update record.
         *
         * @param material the material
         */
        public void setMaterial(Material material) {
            materialId = material.getId();
        }

        /**
         * Get the material ID for this update record.
         *
         * @return the material ID
         */
        public int getMaterialId() {
            return materialId;
        }

        /**
         * Set the material ID for this update record.
         *
         * @param materialId the material ID
         */
        public void setMaterialId(int materialId) {
            this.materialId = materialId;
        }

        /**
         * Get the block data for this update record.
         *
         * @return the block data
         */
        public byte getData() {
            return data;
        }

        /**
         * Set the block data for this update record.
         *
         * @param data the block data
         */
        public void setData(byte data) {
            this.data = data;
        }
    }
}
