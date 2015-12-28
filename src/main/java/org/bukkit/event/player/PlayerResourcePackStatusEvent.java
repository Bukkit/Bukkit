package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.HashMap;
import java.util.Map;

/**
 * Called when a client reports the status of a resource pack sent to it
 */
public class PlayerResourcePackStatusEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Status status;
    private final String hash;

    public PlayerResourcePackStatusEvent(final Player player, final Status status, final String hash) {
        super(player);
        this.status = status;
        this.hash = hash;
    }

    /**
     * Gets the status of the resource pack sent to the client.
     *
     * @return status A {@link Status} describing the status of the resource pack.
     */
    public Status getStatus() { return status; }

    /**
     * Gets the specified hash of the resource pack sent to the client.
     *
     * This can be used to identify different resource packs sent to the client.
     * 
     * @return hash The hash of the resource pack
     */
    public String getHash() { return hash; }

    public enum Status {
        /*
         * The resource pack download has succeeded, and the client is now using the resource pack sent to it.
         * This occurs after the resource pack has been downloaded and applied.
         */
        SUCCESS,

        /*
         * The client declined the download.
         * This occurs when the user clicks 'No' at the download prompt.
         */
        DECLINED,

        /*
         * The client encountered an error when downloading the specified resource pack.
         * This occurs when the user clicks 'Yes' at the download prompt, but an error occurs.
         */
        FAILED_DOWNLOAD,

        /*
         * The client accepted the resource pack, but it has not been downloaded yet.
         * This occurs when the user clicks 'Yes' at the download prompt, but an error occurs.
         */
        ACCEPTED;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
