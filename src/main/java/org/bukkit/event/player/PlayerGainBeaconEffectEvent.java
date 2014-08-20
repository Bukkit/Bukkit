package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;

import java.util.List;

/**
 * Called when a player is given an effect from a nearby beacon. Contains a single effect that the beacon is trying to
 * apply to a player. If a beacon gives multiple effects than this event will be fired off multiple times for each effect
 * for each player it gives it to.
 */
public class PlayerGainBeaconEffectEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    private PotionEffect effect;
    private boolean cancel = false;
    private Location beaconLocation;

    public PlayerGainBeaconEffectEvent(Player player, PotionEffect effect, Location beaconLocation) {
        super(player);
        this.effect = effect;
        this.beaconLocation = beaconLocation;
    }

    /**
     * Gets the potion effect the player will gain from the nearby beacon
     *
     * @return Potion effect the beacon will give
     */
    public PotionEffect getEffect() {
        return effect;
    }

    /**
     * Sets the potion effect a player will gain from the nearby beacon
     *
     * @param effect Potion effect to give the player
     */
    public void setEffect(PotionEffect effect) {
        this.effect = effect;
    }

    /**
     * Location of the beacon giving the effect
     *
     * @return Location of the beacon giving the effect
     */
    public Location getBeaconLocation() {
        return beaconLocation;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
