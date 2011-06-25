package org.bukkit.event.entity;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.painting.PaintingPlaceEvent;
import org.bukkit.event.painting.PaintingBreakEvent;

/**
 * Handles all events fired in relation to entities
 */
public class EntityListener implements Listener {

    public EntityListener() {
    }

    public void onEvent(Event event) {
        switch (event.getType()) {
            case PAINTING_PLACE:
                this.onPaintingPlace((PaintingPlaceEvent) event);
                break;
                
            case PAINTING_BREAK:
                this.onPaintingBreak((PaintingBreakEvent) event);
                break;
                
            // Entity Events
            case ENTITY_DAMAGE:
                this.onEntityDamage((EntityDamageEvent) event);
                break;
                
            case ENTITY_DEATH:
                this.onEntityDeath((EntityDeathEvent) event);
                break;
                
            case ENTITY_COMBUST:
                this.onEntityCombust((EntityCombustEvent) event);
                break;
                
            case ENTITY_EXPLODE:
                this.onEntityExplode((EntityExplodeEvent) event);
                break;
                
            case EXPLOSION_PRIME:
                this.onExplosionPrime((ExplosionPrimeEvent) event);
                break;
                
            case ENTITY_TARGET:
                this.onEntityTarget((EntityTargetEvent) event);
                break;
                
            case ENTITY_INTERACT:
                this.onEntityInteract((EntityInteractEvent) event);
                break;
                
            case ENTITY_PORTAL_ENTER:
                this.onEntityPortalEnter((EntityPortalEnterEvent) event);
                break;
                
            case CREATURE_SPAWN:
                this.onCreatureSpawn((CreatureSpawnEvent) event);
                break;
                
            case ITEM_SPAWN:
                this.onItemSpawn((ItemSpawnEvent) event);
                break;
                
            case PIG_ZAP:
                this.onPigZap((PigZapEvent) event);
                break;
                
            case CREEPER_POWER:
                this.onCreeperPower((CreeperPowerEvent) event);
                break;
                
            case ENTITY_TAME:
                this.onEntityTame((EntityTameEvent) event);
                break;
                
            case ENTITY_REGAIN_HEALTH:
                this.onEntityRegainHealth((EntityRegainHealthEvent) event);
                break;
                
            case PROJECTILE_HIT:
                this.onProjectileHit((ProjectileHitEvent) event);
                break;
        }
    }

    public void onCreatureSpawn(CreatureSpawnEvent event) {
    }

    public void onItemSpawn(ItemSpawnEvent event) {
    }

    public void onEntityCombust(EntityCombustEvent event) {
    }

    public void onEntityDamage(EntityDamageEvent event) {
    }

    public void onEntityExplode(EntityExplodeEvent event) {
    }

    public void onExplosionPrime(ExplosionPrimeEvent event) {
    }

    public void onEntityDeath(EntityDeathEvent event) {
    }

    public void onEntityTarget(EntityTargetEvent event) {
    }

    public void onEntityInteract(EntityInteractEvent event) {
    }

    public void onEntityPortalEnter(EntityPortalEnterEvent event) {
    }

    public void onPaintingPlace(PaintingPlaceEvent event) {
    }

    public void onPaintingBreak(PaintingBreakEvent event) {
    }

    public void onPigZap(PigZapEvent event) {
    }

    public void onCreeperPower(CreeperPowerEvent event) {
    }

    public void onEntityTame(EntityTameEvent event) {
    }

    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
    }

    public void onProjectileHit(ProjectileHitEvent event) {
    }
}
