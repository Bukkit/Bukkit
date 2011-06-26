package org.bukkit.event.entity;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.painting.PaintingPlaceEvent;
import org.bukkit.event.painting.PaintingBreakEvent;

/**
 * Handles all events fired in relation to entities
 */
public class EntityListener implements Listener {

    public EntityListener() {}

    public void onEvent(Event event) {}

    @EventHandler(Event.Type.CREATURE_SPAWN)
    public void onCreatureSpawn(CreatureSpawnEvent event) {}

    @EventHandler(Event.Type.ITEM_SPAWN)
    public void onItemSpawn(ItemSpawnEvent event) {}

    @EventHandler(Event.Type.ENTITY_COMBUST)
    public void onEntityCombust(EntityCombustEvent event) {}

    @EventHandler(Event.Type.ENTITY_DAMAGE)
    public void onEntityDamage(EntityDamageEvent event) {}

    @EventHandler(Event.Type.ENTITY_EXPLODE)
    public void onEntityExplode(EntityExplodeEvent event) {}

    @EventHandler(Event.Type.EXPLOSION_PRIME)
    public void onExplosionPrime(ExplosionPrimeEvent event) {}

    @EventHandler(Event.Type.ENTITY_DEATH)
    public void onEntityDeath(EntityDeathEvent event) {}

    @EventHandler(Event.Type.ENTITY_TARGET)
    public void onEntityTarget(EntityTargetEvent event) {}

    @EventHandler(Event.Type.ENTITY_INTERACT)
    public void onEntityInteract(EntityInteractEvent event) {}

    @EventHandler(Event.Type.ENTITY_PORTAL_ENTER)
    public void onEntityPortalEnter(EntityPortalEnterEvent event) {}

    @EventHandler(Event.Type.PAINTING_PLACE)
    public void onPaintingPlace(PaintingPlaceEvent event) {}

    @EventHandler(Event.Type.PAINTING_BREAK)
    public void onPaintingBreak(PaintingBreakEvent event) {}

    @EventHandler(Event.Type.PIG_ZAP)
    public void onPigZap(PigZapEvent event) {}

    @EventHandler(Event.Type.CREEPER_POWER)
    public void onCreeperPower(CreeperPowerEvent event) {}

    @EventHandler(Event.Type.ENTITY_TAME)
    public void onEntityTame(EntityTameEvent event) {}

    @EventHandler(Event.Type.ENTITY_REGAIN_HEALTH)
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {}

    @EventHandler(Event.Type.PROJECTILE_HIT)
    public void onProjectileHit(ProjectileHitEvent event) {}
}
