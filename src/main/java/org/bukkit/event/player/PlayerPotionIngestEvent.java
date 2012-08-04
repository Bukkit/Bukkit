package org.bukkit.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * This event is called when a player ingests a potion.
 */
public class PlayerPotionIngestEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private final Player player;
    private final List effect;

    public PlayerPotionIngestEvent(final Player player, final List effect) {
        super(player);
        this.effect = effect;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Returns the PotionEffect of the potion ingested
     *
     * @return The PotionEffect of the potion ingested
     */
    public List getPotionEffects() {
        return effect;
    }
    public void setPotionEffect(List effects)
    {
    	this.effects = effects;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
