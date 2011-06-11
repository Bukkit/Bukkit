package org.bukkit.location;

import org.bukkit.World;
import org.bukkit.block.Block;

public class EntityWorldLocation extends EntityLocation implements MutableWorldLocation, WorldLocation {

    private World world;

    public EntityWorldLocation(final World world, final double x, final double y, final double z) {
        super(x, y, z);
        this.world = world;
    }

    public EntityWorldLocation(WorldLocation location) {
        super(location);
        this.world = location.getWorld();
    }
    
    public EntityWorldLocation(final World world, final Location location) {
        super(location);
        this.world = world;
    }

    public World getWorld() {
        return this.world;
    }

    public double distance(WorldLocation location) {
        if (location == null || location.getWorld() != this.getWorld()) {
            throw new IllegalArgumentException("Cannot measure distance between worlds or to null");
        }

        return super.distance(location);
    }

    public double distanceSquared(WorldLocation location) {
        if (location == null || location.getWorld() != this.getWorld()) {
            throw new IllegalArgumentException("Cannot measure distance between worlds or to null");
        }

        return super.distanceSquared((Location) location);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Adds the location by another.
     *
     * @see Vector
     * @param vec
     * @return the same location
     * @throws IllegalArgumentException for differing worlds
     */
    public EntityWorldLocation add(WorldLocation location) {
        if (location == null || location.getWorld() != this.getWorld()) {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }

        return (EntityWorldLocation) super.add(location);
    }

    public EntityWorldLocation subtract(WorldLocation location) {
        if (location == null || location.getWorld() != this.getWorld()) {
            throw new IllegalArgumentException("Cannot subtract Locations of differing worlds");
        }

        return (EntityWorldLocation) super.subtract(location);
    }

    @Override
    public EntityWorldLocation clone() {
        EntityWorldLocation location = (EntityWorldLocation) super.clone();

        location.world = world;
        return location;
    }

    public Block getBlock() {
        return this.world.getBlockAt(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((world == null) ? 0 : world.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        EntityWorldLocation other = (EntityWorldLocation) obj;
        if (world == null) {
            if (other.world != null)
                return false;
        } else if (!world.equals(other.world))
            return false;
        return true;
    }
}
