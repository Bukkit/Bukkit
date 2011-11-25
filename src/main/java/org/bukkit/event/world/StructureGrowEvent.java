package org.bukkit.event.world;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Event that is called when an organic structure attempts to grow (Sapling -> Tree), (Mushroom -> Huge Mushroom), naturally or using bonemeal.
 */
public class StructureGrowEvent extends WorldEvent implements Cancellable {

    private static final long serialVersionUID = 1L;
    private boolean cancelled = false;
    private Location location;
    private final TreeType species;
    private final boolean bonemeal;
    private Player player;
    private ArrayList<BlockState> blocks;

    public StructureGrowEvent(Location location, final TreeType species, final boolean bonemeal, Player player, ArrayList<BlockState> blocks) {
        super(Type.STRUCTURE_GROW, location.getWorld());
        this.location = location;
        this.species = species;
        this.bonemeal = bonemeal;
        this.player = player;
        this.blocks = blocks;
    }

    /**
     * Gets the location of the structure.
     *
     * @return Location of the structure
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets the species type (birch, normal, pine, red mushroom, brown mushroom)
     *
     * @return Structure species
     */
    public TreeType getSpecies() {
        return species;
    }

    /**
     * Checks if structure  was grown using bonemeal.
     *
     * @return True if the structure was grown using bonemeal.
     */
    public boolean getBonemealed() {
        return bonemeal;
    }

    /**
     * Gets the player that bonemealed the structure.
     *
     * @return Player that bonemealed the structure, null if was not bonemealed
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets an ArrayList of all blocks associated with the structure.
     *
     * @return ArrayList of all blocks associated with the structure.
     */
    public ArrayList<BlockState> getBlocks() {
        return blocks;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
