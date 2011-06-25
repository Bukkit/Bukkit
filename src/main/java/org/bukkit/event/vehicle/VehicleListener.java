package org.bukkit.event.vehicle;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.event.Listener;

/**
 * Listener for vehicle events.
 *
 * @author sk89q
 */
public class VehicleListener implements Listener {

    public VehicleListener() {
    }

    public void onEvent(Type type, Event event) {
        switch (type) {
            case VEHICLE_CREATE:
                this.onVehicleCreate((VehicleCreateEvent) event);
                break;

            case VEHICLE_DAMAGE:
                this.onVehicleDamage((VehicleDamageEvent) event);
                break;

            case VEHICLE_DESTROY:
                this.onVehicleDestroy((VehicleDestroyEvent) event);
                break;

            case VEHICLE_COLLISION_BLOCK:
                this.onVehicleBlockCollision((VehicleBlockCollisionEvent) event);
                break;

            case VEHICLE_COLLISION_ENTITY:
                this.onVehicleEntityCollision((VehicleEntityCollisionEvent) event);
                break;

            case VEHICLE_ENTER:
                this.onVehicleEnter((VehicleEnterEvent) event);
                break;

            case VEHICLE_EXIT:
                this.onVehicleExit((VehicleExitEvent) event);
                break;

            case VEHICLE_MOVE:
                this.onVehicleMove((VehicleMoveEvent) event);
                break;

            case VEHICLE_UPDATE:
                this.onVehicleUpdate((VehicleUpdateEvent) event);
                break;
        }
    }

    /**
     * Called when a vehicle is created by a player. This hook will be called
     * for all vehicles created.
     *
     * @param event
     */
    public void onVehicleCreate(VehicleCreateEvent event) {
    }

    /**
     * Called when a vehicle is damaged by the player.
     *
     * @param event
     */
    public void onVehicleDamage(VehicleDamageEvent event) {
    }

    /**
     * Called when a vehicle collides with a block.
     *
     * @param event
     */
    public void onVehicleBlockCollision(VehicleBlockCollisionEvent event) {
    }

    /**
     * Called when a vehicle collides with an entity.
     *
     * @param event
     */
    public void onVehicleEntityCollision(VehicleEntityCollisionEvent event) {
    }

    /**
     * Called when an entity enters a vehicle.
     *
     * @param event
     */
    public void onVehicleEnter(VehicleEnterEvent event) {
    }

    /**
     * Called when an entity exits a vehicle.
     *
     * @param event
     */
    public void onVehicleExit(VehicleExitEvent event) {
    }

    /**
     * Called when an vehicle moves.
     *
     * @param event
     */
    public void onVehicleMove(VehicleMoveEvent event) {
    }

    /**
     * Called when a vehicle is destroyed.
     *
     * @param event
     */
    public void onVehicleDestroy(VehicleDestroyEvent event) {
    }

    /**
     * Called when a vehicle goes through an update cycle
     *
     * @param event
     */
    public void onVehicleUpdate(VehicleUpdateEvent event) {
    }
}
