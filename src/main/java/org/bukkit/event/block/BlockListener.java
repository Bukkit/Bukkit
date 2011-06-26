package org.bukkit.event.block;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Handles all events thrown in relation to Blocks
 */
public class BlockListener implements Listener {

    /**
     * Default Constructor
     */
    public BlockListener() {}

    public void onEvent(Event event) {}
    
    /**
     * Called when a block is damaged (or broken)
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.BLOCK_DAMAGE)
    public void onBlockDamage(BlockDamageEvent event) {}

    /**
     * Called when we try to place a block, to see if we can build it
     */
    @EventHandler(Type.BLOCK_CANBUILD)
    public void onBlockCanBuild(BlockCanBuildEvent event) {}

    /**
     * Called when a block flows (water/lava)
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.BLOCK_FROMTO)
    public void onBlockFromTo(BlockFromToEvent event) {}

    /**
     * Called when a block gets ignited
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.BLOCK_IGNITE)
    public void onBlockIgnite(BlockIgniteEvent event) {}

    /**
     * Called when block physics occurs
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.BLOCK_PHYSICS)
    public void onBlockPhysics(BlockPhysicsEvent event) {}

    /**
     * Called when a player places a block
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.BLOCK_PLACE)
    public void onBlockPlace(BlockPlaceEvent event) {}

    /**
     * Called when redstone changes
     * From: the source of the redstone change
     * To: The redstone dust that changed
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.REDSTONE_CHANGE)
    public void onBlockRedstoneChange(BlockRedstoneEvent event) {}

    /**
     * Called when leaves are decaying naturally
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.LEAVES_DECAY)
    public void onLeavesDecay(LeavesDecayEvent event) {}

    /**
     * Called when a sign is changed
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.SIGN_CHANGE)
    public void onSignChange(SignChangeEvent event) {}

    /**
     * Called when a block is destroyed from burning
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.BLOCK_BURN)
    public void onBlockBurn(BlockBurnEvent event) {}

    /**
     * Called when a block is destroyed by a player.
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.BLOCK_BREAK)
    public void onBlockBreak(BlockBreakEvent event) {}

    /**
     * Called when a world is attempting to place a block during a snowfall
     *
     * @param event Relevant event details
     * @deprecated be prepared to use onBlockForm instead as it will be replacing this event after the RB
     */
    @Deprecated
    @EventHandler(Type.SNOW_FORM)
    public void onSnowForm(SnowFormEvent event) {}

    /**
     * Called when a block is formed based on world conditions
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.BLOCK_FORM)
    public void onBlockForm(BlockFormEvent event) {}

    /**
     * Called when a block spreads based on world conditions
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.BLOCK_SPREAD)
    public void onBlockSpread(BlockSpreadEvent event) {}

    /**
     * Called when a block fades, melts or disappears based on world conditions
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.BLOCK_FADE)
    public void onBlockFade(BlockFadeEvent event) {}

    /**
     * Called when a block is dispensing an item
     *
     * @param event Relevant event details
     */
    @EventHandler(Type.BLOCK_DISPENSE)
    public void onBlockDispense(BlockDispenseEvent event) {}
}
