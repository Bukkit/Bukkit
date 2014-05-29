package org.bukkit.block;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import com.google.common.base.Predicate;

/**
 * Represents the result of calling a method to determine blocks intersected
 * by an entity's line of sight:
 * <p>
 * <ul>
 * <li>
 *     {@link LivingEntity#getLineOfSight(Predicate, int, boolean)}
 * </li>
 * <li>
 *     {@link LivingEntity#getTargetBlock(Predicate, int, boolean)}
 * </li>
 * <li>
 *     {@link LivingEntity#getLastTwoTargetBlocks(Predicate, int, boolean)}
 * </li>
 * </ul>
 */
public class BlockTargetResult {
    private final Block block;
    private final BlockFace face;
    private final Location location;

    public BlockTargetResult(Block block, BlockFace face, Location location) {
        this.block = block;
        this.face = face;
        this.location = location;
    }

    /**
     * Get the block which is intersected by the entity's line of sight.
     *
     * @return the block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Get the closest face of the block intersected by the entity's line of
     * sight.  This may be null if the line of sight did not intersect this
     * block's hit box.
     *
     * @return the intersected block face
     */
    public BlockFace getTargetFace() {
        return face;
    }

    /**
     * Get the precise closest location where the entity's line of sight
     * intersected the block's hit box.  This may be null if the line of sight
     * did not intersect this block's hit box.
     *
     * @return the intersected location
     */
    public Location getTargetLocation() {
        return location;
    }
}
