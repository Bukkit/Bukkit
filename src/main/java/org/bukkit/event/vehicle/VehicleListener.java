package org.bukkit.event.vehicle;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Listener for vehicle events.
 *
 * @author sk89q
 */
public class VehicleListener implements Listener {

    public VehicleListener() {}

    public void onEvent(Event event) {}

    /**
     * Called when a vehicle is created by a player. This hook will be called
     * for all vehicles created.
     *
     * @param event
     */
    @EventHandler(Event.Type.VEHICLE_CREATE)
    public void onVehicleCreate(VehicleCreateEvent event) {}

    /**
     * Called when a vehicle is damaged by the player.
     *
     * @param event
     */
    @EventHandler(Event.Type.VEHICLE_DAMAGE)
    public void onVehicleDamage(VehicleDamageEvent event) {}

    /**
     * Called when a vehicle collides with a block.
     *
     * @param event
     */
    @EventHandler(Event.Type.VEHICLE_COLLISION_BLOCK)
    public void onVehicleBlockCollision(VehicleBlockCollisionEvent event) {}

    /**
     * Called when a vehicle collides with an entity.
     *
     * @param event
     */
    @EventHandler(Event.Type.VEHICLE_COLLISION_ENTITY)
    public void onVehicleEntityCollision(VehicleEntityCollisionEvent event) {}

    /**
     * Called when an entity enters a vehicle.
     *
     * @param event
     */
    @EventHandler(Event.Type.VEHICLE_ENTER)
    public void onVehicleEnter(VehicleEnterEvent event) {}

    /**
     * Called when an entity exits a vehicle.
     *
     * @param event
     */
    @EventHandler(Event.Type.VEHICLE_EXIT)
    public void onVehicleExit(VehicleExitEvent event) {}

    /**
     * Called when an vehicle moves.
     *
     * @param event
     */
    @EventHandler(Event.Type.VEHICLE_MOVE)
    public void onVehicleMove(VehicleMoveEvent event) {}

    /**
     * Called when a vehicle is destroyed.
     *
     * @param event
     */
    @EventHandler(Event.Type.VEHICLE_DESTROY)
    public void onVehicleDestroy(VehicleDestroyEvent event) {}

    /**
     * Called when a vehicle goes through an update cycle
     *
     * @param event
     */
    @EventHandler(Event.Type.VEHICLE_UPDATE)
    public void onVehicleUpdate(VehicleUpdateEvent event) {}
}
