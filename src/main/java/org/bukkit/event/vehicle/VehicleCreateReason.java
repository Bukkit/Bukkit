package org.bukkit.event.vehicle;

/**
 * An enum that describes how a vehicle was created
 */

public enum VehicleCreateReason {
    /*
     * This vehicle was created from a player placing a vehicle in the world 
     */
    PLAYER_PLACED,
    
    /*
     * This vehicle was created by a dispenser
     */
    DISPENSED,
    
    /*
     * This vehicle was created by being spawned
     */
    SPAWNED;
}