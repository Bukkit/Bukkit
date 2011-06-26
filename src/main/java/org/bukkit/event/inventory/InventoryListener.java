package org.bukkit.event.inventory;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
/**
* Handles all events thrown in relation to Blocks
 */
public class InventoryListener implements Listener {
    public InventoryListener() {}

    public void onEvent(Event event) {}

    /**
     * Called when an ItemStack is successfully smelted in a furnace.
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.FURNACE_SMELT)
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {}

    /**
     * Called when an ItemStack is successfully burned as fuel in a furnace.
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.FURNACE_SMELT)
    public void onFurnaceBurn(FurnaceBurnEvent event) {}
}
