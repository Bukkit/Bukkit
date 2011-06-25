package org.bukkit.event.inventory;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
/**
* Handles all events thrown in relation to Blocks
 */
public class InventoryListener implements Listener {
    public InventoryListener() {}

    public void onEvent(Event event) {
       switch(event.getType()){
           case FURNACE_BURN:
               this.onFurnaceBurn((FurnaceBurnEvent)event);
               break;
           case FURNACE_SMELT:
               this.onFurnaceSmelt((FurnaceSmeltEvent)event);
               break;
       }
    }

    /**
     * Called when an ItemStack is successfully smelted in a furnace.
     *
     * @param event Relevant event details
     */
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {}

    /**
     * Called when an ItemStack is successfully burned as fuel in a furnace.
     *
     * @param event Relevant event details
     */
    public void onFurnaceBurn(FurnaceBurnEvent event) {}
}
