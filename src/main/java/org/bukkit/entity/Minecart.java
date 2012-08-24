package org.bukkit.entity;

import org.bukkit.util.Vector;

/**
 * Represents a minecart entity.
 */
public interface Minecart extends Vehicle {

    /**
     * Sets a minecart's damage.
     *
     * @param damage over 40 to "kill" a minecart
     */
    public void setDamage(int damage);

    /**
     * Gets a minecart's damage.
     *
     * @return The damage
     */
    public int getDamage();

    /**
     * Gets the maximum speed of a minecart. The speed is unrelated to the velocity.
     *
     * @return The max speed
     */
    public double getMaxSpeed();

    /**
     * Sets the maximum speed of a minecart. Must be nonnegative. Default is 0.4D.
     *
     * @param speed The max speed
     */
    public void setMaxSpeed(double speed);

    /**
     * Returns whether this minecart will slow down faster without a passenger occupying it
     *
     * @return Whether it decelerates faster
     */
    public boolean isSlowWhenEmpty();

    /**
     * Sets whether this minecart will slow down faster without a passenger occupying it
     *
     * @param slow Whether it will decelerate faster
     */
    public void setSlowWhenEmpty(boolean slow);

    /**
     * Gets the flying velocity modifier. Used for minecarts that are in mid-air.
     * A flying minecart's velocity is multiplied by this factor each tick.
     *
     * @return The vector factor
     */
    public Vector getFlyingVelocityMod();

    /**
     * Sets the flying velocity modifier. Used for minecarts that are in mid-air.
     * A flying minecart's velocity is multiplied by this factor each tick.
     *
     * @param flying velocity modifier vector
     */
    public void setFlyingVelocityMod(Vector flying);

    /**
     * Gets the derailed velocity modifier. Used for minecarts that are on the ground, but not on rails.
     * <p />
     * A derailed minecart's velocity is multiplied by this factor each tick.
     *
     * @return derailed visible speed
     */
    public Vector getDerailedVelocityMod();

    /**
     * Sets the derailed velocity modifier. Used for minecarts that are on the ground, but not on rails.
     * A derailed minecart's velocity is multiplied by this factor each tick.
     *
     * @param derailed visible speed
     */
    public void setDerailedVelocityMod(Vector derailed);

    /**
     * Changes the type of this minecart, returning an object of the new type to continue managing.
     * Do <b>not</b> continue to use the current object after this - use the returned one instead!
     *
     * @param type Type of minecart to change to
     * @return The instance of the new type (Minecart, {@link StorageMinecart} or {@link PoweredMinecart})
     *  that represents this minecart.
     */
    public Minecart setMinecartType(Type type);

    /**
     * Gets the type of this minecart.
     *
     * @return Type of the minecart.
     */
    public Type getMinecartType();

    /**
     * Represents the different types on minecart.
     */
    public enum Type {
        MINECART(0),
        STORAGE_MINECART(1),
        POWERED_MINECART(2);
        private final int id;
        private static final Type[] types = values();

        static {
            for (Type type : values()) {
                types[type.id] = type;
            }
        }

        private Type(int id) {
            this.id = id;
        }

        /**
         * Get Minecraft's ID number representing this minecart type.
         *
         * @return
         */
        public int getId() {
            return id;
        }

        /**
         * Gets the minecart type with this ID.
         *
         * @param id ID to get type of.
         * @return Minecart type with this ID.
         */
        public static Type getType(int id) {
            return types[id];
        }
    }
}
