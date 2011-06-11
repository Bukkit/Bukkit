package org.bukkit.location;

import org.bukkit.block.BlockFace;

public class EntityLocation implements MutableLocation<EntityLocation, Double>, Location {

    private double x;
    private double y;
    private double z;
    
    public static final double EPSILON = 0.000001;
    
    public EntityLocation(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public EntityLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }
    
    public int getBlockX() {
        return LocationUtil.locToBlock(this.x);
    }

    public int getBlockY() {
        return LocationUtil.locToBlock(this.y);
    }

    public int getBlockZ() {
        return LocationUtil.locToBlock(this.z);
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

    /**
     * Gets the magnitude of the vector squared. Not world-aware and
     * orientation independent.
     *
     * @return the magnitude
     */
    public double lengthSquared() {
        return Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2);
    }

    /**
     * Gets the magnitude of the vector, defined as sqrt(x^2+y^2+z^2). The value
     * of this method is not cached and uses a costly square-root function, so
     * do not repeatedly call this method to get the vector's magnitude. NaN
     * will be returned if the inner result of the sqrt() function overflows,
     * which will be caused if the length is too long. Not world-aware and
     * orientation independent.
     *
     * @return the magnitude
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public double distance(Location location) {
        return Math.sqrt(this.distanceSquared(location));
    }

    public double distanceSquared(Location location) {
        return Math.pow(this.x - location.getX(), 2) + Math.pow(this.y - location.getY(), 2) + Math.pow(this.z - location.getZ(), 2);
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public EntityLocation add(Double x, Double y, Double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public EntityLocation add(Location loc) {
        return this.add(loc.getX(), loc.getY(), loc.getZ());
    }

    public EntityLocation subtract(Double x, Double y, Double z) {
        return this.add(-x, -y, -z);
    }

    public EntityLocation subtract(Location loc) {
        return this.add(-loc.getX(), -loc.getY(), -loc.getZ());
    }

    public EntityLocation multiply(Double factor) {
        this.x *= factor;
        this.y *= factor;
        this.z *= factor;
        return this;
    }
    
    /**
     * Multiplies the vector by another.
     *
     * @param vec
     * @return the same vector
     */
    public EntityLocation multiply(Location vec) {
        this.x *= vec.getX();
        this.y *= vec.getY();
        this.z *= vec.getZ();
        return this;
    }
    
    /**
     * Divides the vector by another.
     *
     * @param vec
     * @return the same vector
     */
    public EntityLocation divide(Location location) {
        this.x /= location.getX();
        this.y /= location.getY();
        this.z /= location.getZ();
        return this;
    }
    
    /**
     * Copies another vector
     *
     * @param vec
     * @return the same vector
     */
    public EntityLocation copy(Location vec) {
        this.x = vec.getX();
        this.y = vec.getY();
        this.z = vec.getZ();
        return this;
    }
    
    /**
     * Calculates the dot product of this vector with another. The dot product
     * is defined as x1*x2+y1*y2+z1*z2. The returned value is a scalar.
     *
     * @param other
     * @return dot product
     */
    public double dot(Location other) {
        return x * other.getX() + y * other.getY() + z * other.getZ();
    }

    /**
     * Calculates the cross product of this vector with another. The cross
     * product is defined as:
     *
     * x = y1 * z2 - y2 * z1<br/>
     * y = z1 * x2 - z2 * x1<br/>
     * z = x1 * y2 - x2 * y1
     *
     * @param o
     * @return the same vector
     */
    public EntityLocation crossProduct(Location o) {
        double newX = y * o.getZ() - o.getY() * z;
        double newY = z * o.getX() - o.getZ() * x;
        double newZ = x * o.getY() - o.getX() * y;

        x = newX;
        y = newY;
        z = newZ;
        return this;
    }

    /**
     * Converts this vector to a unit vector (a vector with length of 1).
     *
     * @return the same vector
     */
    public EntityLocation normalize() {
        double length = length();

        x /= length;
        y /= length;
        z /= length;

        return this;
    }
    
    /**
     * Sets this vector to the midpoint between this vector and another.
     *
     * @param other
     * @return this same vector (now a midpoint)
     */
    public EntityLocation midpoint(Location other) {
        x = (x + other.getX()) / 2;
        y = (y + other.getY()) / 2;
        z = (z + other.getZ()) / 2;
        return this;
    }

    /**
     * Gets a new midpoint vector between this vector and another.
     *
     * @param other
     * @return a new midpoint vector
     */
    public Location getMidpoint(Location other) {
        return LocationUtil.getEntityMidpoint(this, other);
    }

    public EntityLocation zero() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        return this;
    }
    
    @Override
    public EntityLocation clone() {
        try {
            EntityLocation location = (EntityLocation) super.clone();

            location.x = x;
            location.y = y;
            location.z = z;
            return location;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Checks to see if two objects are equal.
     *
     * Only two Vectors can ever return true. This method uses a fuzzy match
     * to account for floating point errors. The epsilon can be retrieved
     * with epsilon.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EntityLocation)) {
            return false;
        }

        EntityLocation other = (EntityLocation) obj;

        return Math.abs(getX() - other.getX()) < EPSILON && Math.abs(getY() - other.getY()) < EPSILON && Math.abs(getZ() - other.getZ()) < EPSILON;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 79 * hash + (int) (Double.doubleToLongBits(this.getX()) ^ (Double.doubleToLongBits(this.getX()) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.getY()) ^ (Double.doubleToLongBits(this.getY()) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.getZ()) ^ (Double.doubleToLongBits(this.getZ()) >>> 32));
        return hash;
    }

    /**
     * Returns this components as x,y,z.
     */
    @Override
    public String toString() {
        return "EntityLocation{" + "x=" + getX() + "y=" + getY() + "z=" + getZ() + '}';
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
