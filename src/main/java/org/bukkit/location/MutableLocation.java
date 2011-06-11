package org.bukkit.location;

public interface MutableLocation<T, B> extends Cloneable {

    /**
     * Sets the x-coordinate of this location. Only not null values are
     * permitted.
     * 
     * @param x
     *            new x-coordinate.
     */
    void setX(B x);
    /**
     * Sets the y-coordinate of this location. Only not null values are
     * permitted.
     * 
     * @param y
     *            new y-coordinate.
     */
    void setY(B y);
    /**
     * Sets the z-coordinate of this location. Only not null values are
     * permitted.
     * 
     * @param z
     *            new z-coordinate.
     */
    void setZ(B z);

    /**
     * Adds the location by another. Not world-aware.
     * 
     * @see Vector
     * @param x
     * @param y
     * @param z
     * @return the same location
     */
    T add(B x, B y, B z);

    T add(Location location);

    T subtract(B x, B y, B z);

    T subtract(Location location);

    T multiply(B factor);

    T multiply(Location location);

    T divide(Location location);

    T copy(Location location);

    T zero();

    T normalize();

    T crossProduct(Location location);

    T midpoint(Location location);

}
