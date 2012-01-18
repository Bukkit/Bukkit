package org.bukkit.event.player;

import org.bukkit.event.Listener;

/**
 * Handles all events thrown in relation to a Player
 */
@Deprecated
public class PlayerListener implements Listener {
    public PlayerListener() {}

    /**
     * Called when a player joins a server
     *
     * @param event Relevant event details
     */
    public void onPlayerJoin(PlayerJoinEvent event) {}

    /**
     * Called when a player leaves a server
     *
     * @param event Relevant event details
     */
    public void onPlayerQuit(PlayerQuitEvent event) {}

    /**
     * Called when a player gets kicked from the server
     *
     * @param event Relevant event details
     */
    public void onPlayerKick(PlayerKickEvent event) {}

    /**
     * Called when a player sends a chat message
     *
     * @param event Relevant event details
     */
    public void onPlayerChat(PlayerChatEvent event) {}

    /**
     * Called early in the command handling process. This event is only
     * for very exceptional cases and you should not normally use it.
     *
     * @param event Relevant event details
     */
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {}

    /**
     * Called when a player attempts to move location in a world
     *
     * @param event Relevant event details
     */
    public void onPlayerMove(PlayerMoveEvent event) {}

    /**
     * Called before a player gets a velocity vector sent, which will "push"
     * the player in a certain direction
     *
     * @param event Relevant event details
     */
    public void onPlayerVelocity(PlayerVelocityEvent event) {}

    /**
     * Called when a player attempts to teleport to a new location in a world
     *
     * @param event Relevant event details
     */
    public void onPlayerTeleport(PlayerTeleportEvent event) {}

    /**
     * Called when a player respawns
     *
     * @param event Relevant event details
     */
    public void onPlayerRespawn(PlayerRespawnEvent event) {}

    /**
     * Called when a player interacts with an object or air.
     *
     * @param event Relevant event details
     */
    public void onPlayerInteract(PlayerInteractEvent event) {}

    /**
     * Called when a player right clicks an entity.
     *
     * @param event Relevant event details
     */
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {}

    /**
     * Called when a player attempts to log in to the server
     *
     * @param event Relevant event details
     */
    public void onPlayerLogin(PlayerLoginEvent event) {}

    /**
     * Called when a player has just been authenticated
     *
     * @param event Relevant event details
     */
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {}

    /**
     * Called when a player throws an egg and it might hatch
     *
     * @param event Relevant event details
     */
    public void onPlayerEggThrow(PlayerEggThrowEvent event) {}

    /**
     * Called when a player plays an animation, such as an arm swing
     *
     * @param event Relevant event details
     */
    public void onPlayerAnimation(PlayerAnimationEvent event) {}

    /**
     * Called when a player opens an inventory
     *
     * @param event Relevant event details
     */
    public void onInventoryOpen(PlayerInventoryEvent event) {}

    /**
     * Called when a player changes their held item
     *
     * @param event Relevant event details
     */
    public void onItemHeldChange(PlayerItemHeldEvent event) {}

    /**
     * Called when a player drops an item from their inventory
     *
     * @param event Relevant event details
     */
    public void onPlayerDropItem(PlayerDropItemEvent event) {}

    /**
     * Called when a player picks an item up off the ground
     *
     * @param event Relevant event details
     */
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {}

    /**
     * Called when a player toggles sneak mode
     *
     * @param event Relevant event details
     */
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {}

    /**
     * Called when a player toggles sprint mode
     *
     * @param event Relevant event details
     */
    public void onPlayerToggleSprint(PlayerToggleSprintEvent event) {}

    /**
     * Called when a player fills a bucket
     *
     * @param event Relevant event details
     */
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {}

    /**
     * Called when a player empties a bucket
     *
     * @param event Relevant event details
     */
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {}

    /**
     * Called when a player enters a bed
     *
     * @param event Relevant event details
     */
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {}

    /**
     * Called when a player leaves a bed
     *
     * @param event Relevant event details
     */
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {}

    /**
     * Called when a player is teleporting in a portal (after the animation)
     *
     * @param event Relevant event details
     */
    public void onPlayerPortal(PlayerPortalEvent event) {}

    /**
     * Called when a player is fishing
     *
     * @param event Relevant event details
     */
    public void onPlayerFish(PlayerFishEvent event) {}

    /**
     * Called when a player's game mode is changed
     *
     * @param event Relevant event details
     */
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {}

    /**
     * Called after a player changes to a new world
     *
     * @param event Relevant event details
     */
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {}

    /**
     * Called when a players level changes
     *
     * @param event Relevant event details
     */
    public void onPlayerLevelChange(PlayerLevelChangeEvent event) {}

    /**
     * Called when a players experience changes naturally
     *
     * @param event Relevant event details
     */
    public void onPlayerExpChange(PlayerExpChangeEvent event) {}
}
