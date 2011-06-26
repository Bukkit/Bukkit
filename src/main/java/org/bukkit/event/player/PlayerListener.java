package org.bukkit.event.player;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Handles all events thrown in relation to a Player
 */
public class PlayerListener implements Listener {

    public PlayerListener() {}

    public void onEvent(Event event) {}

    /**
     * Called when a player joins a server
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_JOIN)
    public void onPlayerJoin(PlayerJoinEvent event) {}

    /**
     * Called when a player leaves a server
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_QUIT)
    public void onPlayerQuit(PlayerQuitEvent event) {}

    /**
     * Called when a player gets kicked from the server
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_KICK)
    public void onPlayerKick(PlayerKickEvent event) {}

    /**
     * Called when a player sends a chat message
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_CHAT)
    public void onPlayerChat(PlayerChatEvent event) {}

    /**
     * Called early in the command handling process. This event is only
     * for very exceptional cases and you should not normally use it.
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_COMMAND_PREPROCESS)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {}

    /**
     * Called when a player attempts to move location in a world
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_MOVE)
    public void onPlayerMove(PlayerMoveEvent event) {}

    /**
     * Called when a player attempts to teleport to a new location in a world
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_TELEPORT)
    public void onPlayerTeleport(PlayerTeleportEvent event) {}

    /**
     * Called when a player respawns
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_RESPAWN)
    public void onPlayerRespawn(PlayerRespawnEvent event) {}

    /**
     * Called when a player interacts
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_INTERACT)
    public void onPlayerInteract(PlayerInteractEvent event) {}

    /**
     * Called when a player right clicks an entity.
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_INTERACT_ENTITY)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {}

    /**
     * Called when a player attempts to log in to the server
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_LOGIN)
    public void onPlayerLogin(PlayerLoginEvent event) {}

    /**
     * Called when a player has just been authenticated
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_PRELOGIN)
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {}

    /**
     * Called when a player throws an egg and it might hatch
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_EGG_THROW)
    public void onPlayerEggThrow(PlayerEggThrowEvent event) {}

    /**
     * Called when a player plays an animation, such as an arm swing
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_ANIMATION)
    public void onPlayerAnimation(PlayerAnimationEvent event) {}

    /**
     * Called when a player opens an inventory
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_INVENTORY)
    public void onInventoryOpen(PlayerInventoryEvent event) {}

    /**
     * Called when a player changes their held item
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_ITEM_HELD)
    public void onItemHeldChange(PlayerItemHeldEvent event) {}

    /**
     * Called when a player drops an item from their inventory
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_DROP_ITEM)
    public void onPlayerDropItem(PlayerDropItemEvent event) {}

    /**
     * Called when a player picks an item up off the ground
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_PICKUP_ITEM)
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {}

    /**
     * Called when a player toggles sneak mode
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_TOGGLE_SNEAK)
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {}

    /**
     * Called when a player fills a bucket
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_BUCKET_FILL)
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {}

    /**
     * Called when a player empties a bucket
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_BUCKET_EMPTY)
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {}

    /**
     * Called when a player enters a bed
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_BED_ENTER)
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {}

    /**
     * Called when a player leaves a bed
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_BED_LEAVE)
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {}

    /**
     * Called when a player is teleporting in a portal (after the animation)
     *
     * @param event Relevant event details
     */
    @EventHandler(Event.Type.PLAYER_PORTAL)
    public void onPlayerPortal(PlayerPortalEvent event) {}
}
