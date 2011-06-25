package org.bukkit.event.block;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

/**
 * Handles all events thrown in relation to Blocks
 */
public class BlockListener implements Listener {

    /**
     * Default Constructor
     */
    public BlockListener() {
    }

    public void onEvent(Event event) {
        switch (event.getType()) {
            case BLOCK_PHYSICS:
                this.onBlockPhysics((BlockPhysicsEvent) event);
                break;
            case BLOCK_CANBUILD:
                this.onBlockCanBuild((BlockCanBuildEvent) event);
                break;
            case BLOCK_PLACE:
                this.onBlockPlace((BlockPlaceEvent) event);
                break;
            case BLOCK_DAMAGE:
                this.onBlockDamage((BlockDamageEvent) event);
                break;
            case BLOCK_FROMTO:
                this.onBlockFromTo((BlockFromToEvent) event);
                break;
            case LEAVES_DECAY:
                this.onLeavesDecay((LeavesDecayEvent) event);
                break;
            case SIGN_CHANGE:
                this.onSignChange((SignChangeEvent) event);
                break;
            case BLOCK_IGNITE:
                this.onBlockIgnite((BlockIgniteEvent) event);
                break;
            case REDSTONE_CHANGE:
                this.onBlockRedstoneChange((BlockRedstoneEvent) event);
                break;
            case BLOCK_BURN:
                this.onBlockBurn((BlockBurnEvent) event);
                break;
            case BLOCK_BREAK:
                this.onBlockBreak((BlockBreakEvent) event);
                break;
            case SNOW_FORM:
                this.onSnowForm((SnowFormEvent) event);
                break;
            case BLOCK_FORM:
                this.onBlockForm((BlockFormEvent) event);
                break;
            case BLOCK_SPREAD:
                this.onBlockSpread((BlockSpreadEvent) event);
                break;
            case BLOCK_FADE:
                this.onBlockFade((BlockFadeEvent) event);
                break;
            case BLOCK_DISPENSE:
                this.onBlockDispense((BlockDispenseEvent) event);
                break;
        }
    }

    /**
     * Called when a block is damaged (or broken)
     *
     * @param event Relevant event details
     */
    public void onBlockDamage(BlockDamageEvent event) {
    }

    /**
     * Called when we try to place a block, to see if we can build it
     */
    public void onBlockCanBuild(BlockCanBuildEvent event) {
    }

    /**
     * Called when a block flows (water/lava)
     *
     * @param event Relevant event details
     */
    public void onBlockFromTo(BlockFromToEvent event) {
    }

    /**
     * Called when a block gets ignited
     *
     * @param event Relevant event details
     */
    public void onBlockIgnite(BlockIgniteEvent event) {
    }

    /**
     * Called when block physics occurs
     *
     * @param event Relevant event details
     */
    public void onBlockPhysics(BlockPhysicsEvent event) {
    }

    /**
     * Called when a player places a block
     *
     * @param event Relevant event details
     */
    public void onBlockPlace(BlockPlaceEvent event) {
    }

    /**
     * Called when redstone changes
     * From: the source of the redstone change
     * To: The redstone dust that changed
     *
     * @param event Relevant event details
     */
    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
    }

    /**
     * Called when leaves are decaying naturally
     *
     * @param event Relevant event details
     */
    public void onLeavesDecay(LeavesDecayEvent event) {
    }

    /**
     * Called when a sign is changed
     *
     * @param event Relevant event details
     */
    public void onSignChange(SignChangeEvent event) {
    }

    /**
     * Called when a block is destroyed from burning
     *
     * @param event Relevant event details
     */
    public void onBlockBurn(BlockBurnEvent event) {
    }

    /**
     * Called when a block is destroyed by a player.
     *
     * @param event Relevant event details
     */
    public void onBlockBreak(BlockBreakEvent event) {
    }

    /**
     * Called when a world is attempting to place a block during a snowfall
     *
     * @param event Relevant event details
     * @deprecated be prepared to use onBlockForm instead as it will be replacing this event after the RB
     */
    @Deprecated
    public void onSnowForm(SnowFormEvent event) {
    }

    /**
     * Called when a block is formed based on world conditions
     *
     * @param event Relevant event details
     */
    public void onBlockForm(BlockFormEvent event) {
    }

    /**
     * Called when a block spreads based on world conditions
     *
     * @param event Relevant event details
     */
    public void onBlockSpread(BlockSpreadEvent event) {
    }

    /**
     * Called when a block fades, melts or disappears based on world conditions
     *
     * @param event Relevant event details
     */
    public void onBlockFade(BlockFadeEvent event) {
    }

    /**
     * Called when a block is dispensing an item
     *
     * @param event Relevant event details
     */
    public void onBlockDispense(BlockDispenseEvent event) {
    }
}
