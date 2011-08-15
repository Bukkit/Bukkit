package org.bukkit.event.vehicle;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

/**
 * Raised when a vehicle is destroyed
 */
public class VehicleDestroyEvent extends VehicleEvent implements Cancellable {
    private Entity attacker;
    private boolean cancelled;
    private List<ItemStack> drops;

    public VehicleDestroyEvent(Vehicle vehicle, Entity attacker, List<ItemStack> drops) {
        super(Type.VEHICLE_DESTROY, vehicle);
        this.attacker = attacker;
        this.drops = drops;
    }

    /**
     * Gets the Entity that has destroyed the vehicle
     *
     * @return the Entity that has destroyed the vehicle
     */
    public Entity getAttacker() {
        return attacker;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public List<ItemStack> getDrops() {
        return drops;
    }
}
