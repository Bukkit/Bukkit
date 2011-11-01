package org.bukkit.event.world;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Event that is called when a tree grows (Sapling -> tree), naturally or using bonemeal.
 */
public class TreeGrowEvent extends WorldEvent implements Cancellable {
    private static final long serialVersionUID = 1L;
    private boolean cancelled = false;
    private Location location;
    private final TreeType species;
    private final boolean bonemeal;
    private Player player;
    private ArrayList<BlockState> blocks;

    public TreeGrowEvent(Location location, final TreeType species, final boolean bonemeal, Player player, ArrayList<BlockState> blocks) {
        super(Type.TREE_GROW, location.getWorld());
        this.location = location;
        this.species = species;
        this.bonemeal = bonemeal;
        this.player = player;
        this.blocks = blocks;
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

    /**
     * Checks if sapling was bonemealed.
     * 
     * @return True if sapling was bonemealed
     */
    public boolean getBonemealed() {
        return bonemeal;
    }

    /**
     * Gets the player that bonemealed the sapling.
     * 
     * @return Player that bonemealed the sapling, null if was not bonemealed
     */
    public Player getPlayer() {
        return player;
    }
    public ArrayList<BlockState> getTreeBlocks() {
        return blocks;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
