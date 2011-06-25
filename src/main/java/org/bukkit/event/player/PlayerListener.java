package org.bukkit.event.player;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.event.Listener;

/**
 * Handles all events thrown in relation to a Player
 */
public class PlayerListener implements Listener {

    public PlayerListener() {
    }

    public void onEvent(Type type, Event event) {
        switch (type) {
            case PLAYER_JOIN:
                this.onPlayerJoin((PlayerJoinEvent) event);
                break;
            case PLAYER_QUIT:
                this.onPlayerQuit((PlayerQuitEvent) event);
                break;
            case PLAYER_RESPAWN:
                this.onPlayerRespawn((PlayerRespawnEvent) event);
                break;
            case PLAYER_KICK:
                this.onPlayerKick((PlayerKickEvent) event);
                break;
            case PLAYER_COMMAND_PREPROCESS:
                this.onPlayerCommandPreprocess((PlayerCommandPreprocessEvent) event);
                break;
            case PLAYER_CHAT:
                this.onPlayerChat((PlayerChatEvent) event);
                break;
            case PLAYER_MOVE:
                this.onPlayerMove((PlayerMoveEvent) event);
                break;
            case PLAYER_TELEPORT:
                this.onPlayerTeleport((PlayerTeleportEvent) event);
                break;
            case PLAYER_PORTAL:
                this.onPlayerPortal((PlayerPortalEvent) event);
                break;
            case PLAYER_INTERACT:
                this.onPlayerInteract((PlayerInteractEvent) event);
                break;
            case PLAYER_INTERACT_ENTITY:
                this.onPlayerInteractEntity((PlayerInteractEntityEvent) event);
                break;
            case PLAYER_LOGIN:
                this.onPlayerLogin((PlayerLoginEvent) event);
                break;
            case PLAYER_PRELOGIN:
                this.onPlayerPreLogin((PlayerPreLoginEvent) event);
                break;
            case PLAYER_EGG_THROW:
                this.onPlayerEggThrow((PlayerEggThrowEvent) event);
                break;
            case PLAYER_ANIMATION:
                this.onPlayerAnimation((PlayerAnimationEvent) event);
                break;
            case INVENTORY_OPEN:
                this.onInventoryOpen((PlayerInventoryEvent) event);
                break;
            case PLAYER_ITEM_HELD:
                this.onItemHeldChange((PlayerItemHeldEvent) event);
                break;
            case PLAYER_DROP_ITEM:
                this.onPlayerDropItem((PlayerDropItemEvent) event);
                break;
            case PLAYER_PICKUP_ITEM:
                this.onPlayerPickupItem((PlayerPickupItemEvent) event);
                break;
            case PLAYER_TOGGLE_SNEAK:
                this.onPlayerToggleSneak((PlayerToggleSneakEvent) event);
                break;
            case PLAYER_BUCKET_EMPTY:
                this.onPlayerBucketEmpty((PlayerBucketEmptyEvent) event);
                break;
            case PLAYER_BUCKET_FILL:
                this.onPlayerBucketFill((PlayerBucketFillEvent) event);
                break;
            case PLAYER_BED_ENTER:
                this.onPlayerBedEnter((PlayerBedEnterEvent) event);
                break;
            case PLAYER_BED_LEAVE:
                this.onPlayerBedLeave((PlayerBedLeaveEvent) event);
                break;
        }
    }

    /**
     * Called when a player joins a server
     *
     * @param event Relevant event details
     */
    public void onPlayerJoin(PlayerJoinEvent event) {
    }

    /**
     * Called when a player leaves a server
     *
     * @param event Relevant event details
     */
    public void onPlayerQuit(PlayerQuitEvent event) {
    }

    /**
     * Called when a player gets kicked from the server
     *
     * @param event Relevant event details
     */
    public void onPlayerKick(PlayerKickEvent event) {
    }

    /**
     * Called when a player sends a chat message
     *
     * @param event Relevant event details
     */
    public void onPlayerChat(PlayerChatEvent event) {
    }

    /**
     * Called early in the command handling process. This event is only
     * for very exceptional cases and you should not normally use it.
     *
     * @param event Relevant event details
     */
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    }

    /**
     * Called when a player attempts to move location in a world
     *
     * @param event Relevant event details
     */
    public void onPlayerMove(PlayerMoveEvent event) {
    }

    /**
     * Called when a player attempts to teleport to a new location in a world
     *
     * @param event Relevant event details
     */
    public void onPlayerTeleport(PlayerTeleportEvent event) {
    }

    /**
     * Called when a player respawns
     *
     * @param event Relevant event details
     */
    public void onPlayerRespawn(PlayerRespawnEvent event) {
    }

    /**
     * Called when a player interacts
     *
     * @param event Relevant event details
     */
    public void onPlayerInteract(PlayerInteractEvent event) {
    }

    /**
     * Called when a player right clicks an entity.
     *
     * @param event Relevant event details
     */
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
    }

    /**
     * Called when a player attempts to log in to the server
     *
     * @param event Relevant event details
     */
    public void onPlayerLogin(PlayerLoginEvent event) {
    }

    /**
     * Called when a player has just been authenticated
     *
     * @param event Relevant event details
     */
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {
    }

    /**
     * Called when a player throws an egg and it might hatch
     *
     * @param event Relevant event details
     */
    public void onPlayerEggThrow(PlayerEggThrowEvent event) {
    }

    /**
     * Called when a player plays an animation, such as an arm swing
     *
     * @param event Relevant event details
     */
    public void onPlayerAnimation(PlayerAnimationEvent event) {
    }

    /**
     * Called when a player opens an inventory
     *
     * @param event Relevant event details
     */
    public void onInventoryOpen(PlayerInventoryEvent event) {
    }

    /**
     * Called when a player changes their held item
     *
     * @param event Relevant event details
     */
    public void onItemHeldChange(PlayerItemHeldEvent event) {
    }

    /**
     * Called when a player drops an item from their inventory
     *
     * @param event Relevant event details
     */
    public void onPlayerDropItem(PlayerDropItemEvent event) {
    }

    /**
     * Called when a player picks an item up off the ground
     *
     * @param event Relevant event details
     */
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
    }

    /**
     * Called when a player toggles sneak mode
     *
     * @param event Relevant event details
     */
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
    }

    /**
     * Called when a player fills a bucket
     *
     * @param event Relevant event details
     */
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
    }

    /**
     * Called when a player empties a bucket
     *
     * @param event Relevant event details
     */
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
    }

    /**
     * Called when a player enters a bed
     *
     * @param event Relevant event details
     */
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
    }

    /**
     * Called when a player leaves a bed
     *
     * @param event Relevant event details
     */
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
    }

    /**
     * Called when a player is teleporting in a portal (after the animation)
     *
     * @param event Relevant event details
     */
    public void onPlayerPortal(PlayerPortalEvent event) {
    }
}
