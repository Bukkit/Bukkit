package org.bukkit.block;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import com.google.common.base.Predicate;

/**
 * Represents the result of calling the following methods to determine blocks
 * or block hit boxes intersected by an entity's line of sight:
 * <ul>
 * <li>
 *     {@link LivingEntity#getLineOfSight(Predicate, int, boolean)}
 * </li><li>
 *     {@link LivingEntity#getTargetBlock(Predicate, int, boolean)}
 * </li><li>
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
     * Get the block which the entity's line of sight passed through.
     * <p>
     * While the return value from this method will always be non-null, it
     * does not necessarily indicate that this block was actually intersected
     * by the entity's line of sight, only that this block was considered for
     * intersection by a block targeting method.  To check for intersection,
     * use the {@link #getTargetFace()} and/or
     * {@link #getTargetLocation()} methods.
     *
     * @return the block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Get the closest face of the block intersected by the entity's line of
     * sight.  This may be null if the line of sight did not actually
     * intersect this block's hit box, as determined by
     * {@link Block#getHitBox(org.bukkit.Location, org.bukkit.util.Vector)}.
     *
     * @return the intersected block face
     */
    public BlockFace getTargetFace() {
        return face;
    }

    /**
     * Get the precise location where the entity's line of sight intersected
     * the block's hit box (on the face of the hit box closest to the entity).
     * This may be null if the line of sight did not actually intersect this
     * block's hit box, as determined by
     * {@link Block#getHitBox(org.bukkit.Location, org.bukkit.util.Vector)}.
     *
     * @return the intersected location
     */
    public Location getTargetLocation() {
        return location;
    }
}
