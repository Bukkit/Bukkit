package org.bukkit.location;

import org.bukkit.block.BlockFace;

/**
 * A mutable block orientated location. Note that this location is mutable, and
 * it is recommended to use the FixedBlockLocation if you don't need the
 * mutability.
 */
public class BlockLocation implements MutableLocation<BlockLocation, Integer>, Location {

    private int x;
    private int y;
    private int z;

    public BlockLocation(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BlockLocation(Location location) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }

    public int getBlockX() {
        return this.x;
    }

    public int getBlockY() {
        return this.y;
    }

    public int getBlockZ() {
        return this.z;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public double distance(Location location) {
        return Math.sqrt(this.distanceSquared(location));
    }

    public double distanceSquared(Location location) {
        return Math.pow(this.x - location.getX(), 2) + Math.pow(this.y - location.getY(), 2) + Math.pow(this.z - location.getZ(), 2);
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public BlockLocation add(Integer x, Integer y, Integer z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public BlockLocation add(Location loc) {
        return this.add(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    public BlockLocation subtract(Integer x, Integer y, Integer z) {
        return this.add(-x, -y, -z);
    }

    public BlockLocation subtract(Location loc) {
        return this.add(-loc.getBlockX(), -loc.getBlockY(), -loc.getBlockZ());
    }

    public BlockLocation multiply(Integer factor) {
        this.x *= factor;
        this.y *= factor;
        this.z *= factor;
        return this;
    }

    public BlockLocation zero() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        return this;
    }

    public double dot(Location other) {
        return this.x * other.getX() + this.y * other.getY() + this.z * other.getZ();
    }

    public Location getMidpoint(Location other) {
        return LocationUtil.getBlockMidpoint(this, other);
    }

    public BlockLocation multiply(Location location) {
        this.x *= location.getX();
        this.y *= location.getY();
        this.z *= location.getZ();
        return this;
    }

    public BlockLocation divide(Location location) {
        this.x /= location.getX();
        this.y /= location.getY();
        this.z /= location.getZ();
        return this;
    }

    public BlockLocation copy(Location location) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        return this;
    }

    // TODO: Very inaccurate!
    /**
     * To normalize a block orientated location is very inaccurate, as there only
     * -1, 0 and +1 as possible results for a coordinate. If an accurate normalized location/vector needed use the EntityLocation instead:
     * <blockquote>
     * <code>
     * BlockLocation blockLoc = …;<br>
     * EntityLocation entityLoc = new EntityLocation(blockLoc);<br>
     * entityLoc.normalize();<br>
     * </code>
     * </blockquote>
     */
    public BlockLocation normalize() {
        double length = length();

        this.x /= length;
        this.y /= length;
        this.z /= length;

        return this;
    }

    public BlockLocation crossProduct(Location o) {
        int newX = this.y * o.getBlockZ() - o.getBlockY() * this.z;
        int newY = this.z * o.getBlockX() - o.getBlockZ() * this.x;
        int newZ = this.x * o.getBlockY() - o.getBlockX() * this.y;

        this.x = newX;
        this.y = newY;
        this.z = newZ;
        return this;
    }

    public BlockLocation midpoint(Location other) {
        return this.copy(this.getMidpoint(other));
    }

    @Override
    public BlockLocation clone() {
        try {
            BlockLocation location = (BlockLocation) super.clone();

            location.x = x;
            location.y = y;
            location.z = z;
            return location;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BlockLocation other = (BlockLocation) obj;
        return (x == other.x) && (y == other.y) && (z == other.z);
    }

    public Location getRelative(int modX, int modY, int modZ) {
        this.setX(this.x + modX);
        this.setY(this.y + modY);
        this.setZ(this.z + modZ);
        return this;
    }

    public Location getRelative(BlockFace face) {
        return this.getRelative(face, 1);
    }

    public Location getRelative(BlockFace face, int distance) {
        return this.getRelative(face.getModX(), face.getModY(), face.getModZ());
    }

}
