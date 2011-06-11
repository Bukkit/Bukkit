package org.bukkit.location;

import org.bukkit.World;

public class FixedEntityDirectionalLocation extends FixedEntityWorldLocation implements DirectionalLocation {

    private final float yaw;
    private final float pitch;

    public FixedEntityDirectionalLocation(World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    public FixedEntityDirectionalLocation(World world, double x, double y, double z, float yaw, float pitch) {
        super(world, x, y, z);
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public FixedEntityDirectionalLocation(World world, Location location, float yaw, float pitch) {
        super(world, location);
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public FixedEntityDirectionalLocation(World world, DirectionalLocation location) {
        this(world, location, location.getYaw(), location.getPitch());
    }

    public FixedEntityDirectionalLocation(World world, Location location) {
        this(world, location, 0, 0);
    }

    public FixedEntityDirectionalLocation(WorldLocation location, float yaw, float pitch) {
        this(location.getWorld(), location, yaw, pitch);
    }

    public FixedEntityDirectionalLocation(WorldLocation location) {
        this(location.getWorld(), location);
    }

    public static DirectionalLocation expand(Location location, final World world) {
        final World inWorld;
        if (location instanceof WorldLocation) {
            inWorld = ((WorldLocation) location).getWorld();
        } else {
            inWorld = world;
        }
        float yaw = 0;
        float pitch = 0;
        if (location instanceof DirectionalLocation) {
            yaw = ((DirectionalLocation) location).getYaw();
            pitch = ((DirectionalLocation) location).getPitch();
        }
        return new FixedEntityDirectionalLocation(inWorld, location, yaw, pitch);
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public Location getDirection() {
        return LocationUtil.getDirection(this);
    }

    public Location getRelative(int modX, int modY, int modZ) {
        return new FixedEntityDirectionalLocation(this.getWorld(), this.x + modX, this.y + modY, this.z + modZ, this.yaw, this.pitch);
    }
}
