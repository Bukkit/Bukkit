package org.bukkit.location;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public interface Location {

    /**
     * Gets the floored value of the x-coordinate, indicating the block that
     * is at this location.
     *
     * @return integer x-coordinate.
     */
    int getBlockX();
    int getBlockY();
    int getBlockZ();

    double getX();
    
    /**
     * Gets the floating point y-coordinate of this location.
     *
     * @return floating point y-coordinate.
     */
    double getY();
    double getZ();

    /*
     * This value is a int, if the location is int based (r = x² + y² + z² where
     * x, y, z ∈ integer → r ∈ integer), but could be overflow
     */
    double lengthSquared();
    double length();

    /**
     * Get the distance between this location and another one. The method uses a
     * costly square-root function, so do not repeatedly call this method to get
     * the location's magnitude. NaN will be returned if the inner result of the
     * sqrt() function overflows, which will be caused if the distance is too
     * long.
     * 
     * @param location Other location which will be measured.
     * @return the distance between both locations.
     */
    double distance(Location location);
    /**
     * Get the squared distance between this location and another one.
     *
     * @return the squared distance between both locations.
     */
    double distanceSquared(Location location);

    double dot(Location other);
    Location getMidpoint(Location other);

    /**
     * Gets the location at the given offsets.
     *
     * @param modX X-coordinate offset
     * @param modY Y-coordinate offset
     * @param modZ Z-coordinate offset
     * @return Block at the given offsets
     */
    Location getRelative(int modX, int modY, int modZ);

    /**
     * Gets the block at the given face<br />
     * <br />
     * This method is equal to getRelative(face, 1).
     *
     * @param face Face of this location to return
     * @return location at the given face
     * @see {@link Location#getRelative(BlockFace, int)}
     * @see {@link Block#getRelative(BlockFace)}
     */
    Location getRelative(BlockFace face);

    /**
     * Gets the location at the given distance of the given face<br />
     * <br />
     * For example, the following method places water at (100, 102, 100) which is two blocks
     * above (100, 100, 100).
     * <pre>
     * Location location = new FixedBlockLocation(100,100,100);
     * Location shower = location.getRelative(BlockFace.UP, 2);
     * world.getBlockAt(shower).setType(Material.WATER);
     * </pre>
     *
     * @param face Face of this location to return
     * @param distance Distance to the location
     * @return location at the given face
     * @see {@link Block#getRelative(BlockFace, int)}
     */
    Location getRelative(BlockFace face, int distance);
}
