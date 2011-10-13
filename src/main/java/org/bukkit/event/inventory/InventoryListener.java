package org.bukkit.event.inventory;

import org.bukkit.event.Listener;
/**
* Handles all events thrown in relation to Blocks
 */
public class InventoryListener implements Listener {
    public InventoryListener() {}

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
    
    
    /**
     * Called when a player tries to open a container.
     * 
     * @param event Relevant event details
     */
    public void onContainerOpen(PlayerOpenContainerEvent event) {}
    
    /**
     * Called when a container is closed.
     * 
     * @param event Relevant event details
     */
    public void onContainerClose(PlayerCloseContainerEvent event) {}
    
    /**
     * Called when a player tries to click on a container slot
     * 
     * @param event Relevant event details
     */
    public void onContainerClick(PlayerClickContainerEvent event) {}
}
