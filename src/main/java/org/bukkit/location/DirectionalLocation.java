package org.bukkit.location;

public interface DirectionalLocation extends WorldLocation {

    /**
     * Gets the yaw of this location
     * 
     * @return Yaw
     */
    float getYaw();

    /**
     * Gets the pitch of this location
     * 
     * @return Pitch
     */
    float getPitch();

    Location getDirection();

}
