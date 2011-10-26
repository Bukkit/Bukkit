package org.bukkit.event.world;

import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.event.Cancellable;

/**
 * Event that is called when a tree grows (Sapling -> tree), naturally or using bonemeal.
 */
public class TreeGrowEvent extends WorldEvent implements Cancellable {
    private static final long serialVersionUID = 1L;
    private boolean cancelled = false;
    private Location location;
    private final TreeType species;

    public TreeGrowEvent(Location location, final TreeType species) {
        super(Type.TREE_GROW, location.getWorld());
        this.location = location;
        this.species = species;
    }
    
    /**
     * Gets the location of the tree.
     * 
     * @return Location of the tree
     */
    public Location getLocation() {
        return location;
    }
    
    /**
     * Gets the sapling type (birch, normal, pine)
     * 
     * @return Sapling species
     */
    public TreeType getSapling() {
        return species;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
