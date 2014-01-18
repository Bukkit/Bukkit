package org.bukkit.event.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Called when two or more blocks are placed by a player.
 * <p>
 * If a Block Multi Place event is cancelled, the blocks will not be placed.
 */
public class BlockMultiPlaceEvent extends BlockPlaceEvent {
    protected final List<BlockState> blockStates = new ArrayList<BlockState>();

    public BlockMultiPlaceEvent(final List<BlockState> blockStates, final Block placedAgainst, final ItemStack itemInHand, final Player thePlayer, final boolean canBuild) {
        super(blockStates.get(0).getBlock(), blockStates.get(0), placedAgainst, itemInHand, thePlayer, canBuild);
        this.blockStates.addAll(blockStates);
    }

    /**
     * Gets an immutable list of BlockStates for all blocks which were replaced. Material type air
     * mostly.
     *
     * @return Immutable list of BlockStates for all blocks which were replaced.
     */
    public List<BlockState> getBlockStates() {
        return Collections.unmodifiableList(this.blockStates);
    }
}
