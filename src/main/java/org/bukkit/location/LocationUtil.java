package org.bukkit.location;

import org.bukkit.block.BlockFace;

public final class LocationUtil {

    public final static Location ZERO_LOCATION = new Location() {

        public double lengthSquared() {
            return 0;
        }

        public double length() {
            return 0;
        }

        public double getZ() {
            return 0;
        }

        public double getY() {
            return 0;
        }

        public double getX() {
            return 0;
        }

        public int getBlockZ() {
            return 0;
        }

        public int getBlockY() {
            return 0;
        }

        public int getBlockX() {
            return 0;
        }

        public double distanceSquared(Location location) {
            return 0;
        }

        public double distance(Location location) {
            return 0;
        }

        public double dot(Location other) {
            return 0;
        }

        public Location getMidpoint(Location other) {
            return LocationUtil.getEntityMidpoint(this, other);
        }

        public Location getRelative(int modX, int modY, int modZ) {
            return new FixedBlockLocation(modX, modY, modZ);
        }

        public Location getRelative(BlockFace face) {
            return this.getRelative(face, 1);
        }

        public Location getRelative(BlockFace face, int distance) {
            return this.getRelative(face.getModX(), face.getModY(), face.getModZ());
        }
    };

    private LocationUtil() {
    }

    /**
     * Safely converts a double (location coordinate) to an int (block
     * coordinate)
     * 
     * @param loc
     *            Precise coordinate
     * @return Block coordinate
     */
    public static int locToBlock(double loc) {
        return (int) Math.floor(loc);
    }

    /**
     * Returns whether the location is within a sphere.
     * 
     * @param location
     *            Tested location.
     * @param origin
     *            The origin of the sphere.
     * @param radius
     *            The radius of the sphere.
     * @return whether the location is in the sphere
     */
    public static boolean isInSphere(Location location, Location origin, double radius) {
        return (Math.pow(origin.getX() - location.getX(), 2) + Math.pow(origin.getY() - location.getY(), 2) + Math.pow(origin.getZ() - location.getZ(), 2)) <= Math.pow(radius, 2);
    }

    /**
     * Returns whether the location is in an axis-aligned bounding box. The
     * minimum and maximum locations given must be truly the minimum and maximum
     * X, Y and Z components.
     * 
     * @param location
     * @param min
     * @param max
     * @return whether the location is in the AABB
     */
    public static boolean isInAABB(Location location, Location min, Location max) {
        return location.getX() >= min.getX() && location.getX() <= max.getX() && location.getY() >= min.getY() && location.getY() <= max.getY() && location.getZ() >= min.getZ() && location.getZ() <= max.getZ();
    }

    /**
     * Gets the angle between the two locations in radians.
     * 
     * @param a
     *            First location.
     * @param b
     *            Second location.
     * @return angle between a and b in radians.
     */
    public static float angle(Location a, Location b) {
        double dot = a.dot(b) / (a.length() * b.length());

        return (float) Math.acos(dot);
    }

    /**
     * Gets a Vector pointing in the direction that this Location is facing
     * 
     * @return Vector
     */
    public static FixedEntityLocation getDirection(DirectionalLocation location) {
        double rotX = Math.toRadians(location.getYaw());
        double rotY = Math.toRadians(location.getPitch());

        double h = Math.cos(rotY);

        double y = -Math.sin(rotY);
        double x = -h * Math.sin(rotX);
        double z = h * Math.cos(rotX);

        return new FixedEntityLocation(x, y, z);
    }

    public static Location getBlockMidpoint(Location a, Location b) {
        int x = (a.getBlockX() + b.getBlockX()) / 2;
        int y = (a.getBlockY() + b.getBlockY()) / 2;
        int z = (a.getBlockZ() + b.getBlockZ()) / 2;
        return new FixedBlockLocation(x, y, z);
    }

    public static Location getEntityMidpoint(Location a, Location b) {
        double x = (a.getX() + b.getX()) / 2;
        double y = (a.getY() + b.getY()) / 2;
        double z = (a.getZ() + b.getZ()) / 2;
        return new FixedEntityLocation(x, y, z);
    }
}
