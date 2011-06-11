package org.bukkit.location;

import org.bukkit.block.BlockFace;

public class FixedBlockLocation implements Location {

    public final int x;
    public final int y;
    public final int z;

    private int lenSqr;
    private Double len;

    public FixedBlockLocation(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public FixedBlockLocation(Location location) {
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
        if (this.lenSqr < 0) {
            this.lenSqr = this.x * this.x + this.y * this.y + this.z * this.z;
        }
        return this.lenSqr;
    }

    public double length() {
        if (this.len == null) {
            this.len = Math.sqrt(this.lengthSquared());
        }
        return this.len;
    }

    public double distance(Location location) {
        return Math.sqrt(this.distanceSquared(location));
    }

    public double distanceSquared(Location location) {
        return Math.pow(this.x - location.getX(), 2) + Math.pow(this.y - location.getY(), 2) + Math.pow(this.z - location.getZ(), 2);
    }

    public double dot(Location other) {
        return this.x * other.getX() + this.y * other.getY() + this.z * other.getZ();
    }

    public Location getMidpoint(Location other) {
        return LocationUtil.getBlockMidpoint(this, other);
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
        FixedBlockLocation other = (FixedBlockLocation) obj;
        return (x == other.x) && (y == other.y) && (z == other.z);
    }

    public Location getRelative(int modX, int modY, int modZ) {
        return new FixedBlockLocation(this.x + modX, this.y + modY, this.z + modZ);
    }

    public Location getRelative(BlockFace face) {
        return this.getRelative(face, 1);
    }

    public Location getRelative(BlockFace face, int distance) {
        return this.getRelative(face.getModX(), face.getModY(), face.getModZ());
    }
}
