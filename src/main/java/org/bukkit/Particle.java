package org.bukkit;

/**
 * An enum of particle effects the server is able to send to players.
 */
public enum Particle {
    /**
     * A very large explosion.
     */
    EXPLOSION_HUGE,
    /**
     * A large explosion.
     */
    EXPLOSION_LARGE,
    /**
     * The sparks from fireworks.
     */
    FIREWORKS_SPARK,
    /**
     * Bubbles. Only displays underwater.
     */
    BUBBLES,
    /**
     * Underwater ambiance. Only displays underwater.
     */
    UNDERWATER,
    /**
     * The fog shown in the void.
     */
    VOID_FOG,
    /**
     * Tiny gray particles.
     */
    TOWN_AURA,
    /**
     * The critical hit particles.
     */
    CRITICAL,
    /**
     * Magical critical hit particles.
     */
    CRITICAL_MAGIC,
    /**
     * Small black smoke particles.
     */
    SMOKE,
    /**
     * Potion swirls.
     */
    SPELL,
    /**
     * Semitransparent potion swirls.
     */
    SPELL_AMBIENT,
    /**
     * A puff of white potion swirls.
     */
    SPELL_MOB,
    /**
     * A puff of white stars.
     */
    SPELL_INSTANT,
    /**
     * Purple particles from witches.
     */
    SPELL_WITCH,
    /**
     * The musical note from note blocks.
     */
    NOTE,
    /**
     * Nether portal particles.
     */
    PORTAL,
    /**
     * Enchantment table glyphs.
     */
    ENCHANTMENT_TABLE,
    /**
     * Small gray smoke particles.
     */
    SMOKE_SMALL,
    /**
     * Flame particles from mob spawners.
     */
    FLAME,
    /**
     * The particles that pop out of lava.
     */
    LAVA_POP,
    /**
     * Footsteps which fade after several seconds.
     */
    FOOTSTEP,
    /**
     * Water splash particles.
     */
    WATER_SPLASH,
    /**
     * Large smoke particles.
     */
    SMOKE_LARGE,
    /**
     * White smoke particles.
     */
    CLOUD,
    /**
     * Colored dust used for redstone.
     */
    REDSTONE,
    /**
     * Snowball breaking particles.
     */
    SNOWBALL,
    /**
     * Water dripping from the ceiling.
     */
    DRIP_WATER,
    /**
     * Lava dripping from the ceiling.
     */
    DRIP_LAVA,
    /**
     * Small white puffs.
     */
    SNOW_SHOVEL,
    /**
     * Slime ball break particles.
     */
    SLIME,
    /**
     * Hearts from breeding animals.
     */
    HEART,
    /**
     * Thunderclouds indicating villager anger.
     */
    VILLAGER_ANGRY,
    /**
     * Green stars indicating villager happiness.
     */
    VILLAGER_HAPPY,
    /**
     * The wake created while fishing.
     */
    WATER_WAKE,
    /**
     * The particles generated when a tool breaks.
     * Requires an item material to be specified.
     */
    ITEM_BREAK,
    /**
     * The particles generated when a block is broken.
     * Requires a block material to be specified.
     */
    BLOCK_BREAK,
    /**
     * Dust textured like a specific block.
     * Requires a block material to be specified.
     */
    BLOCK_DUST,
    /**
     * Sprite of the barrier block.
     */
    BARRIER,
    /**
     * A droplet of water, similar to a splash but not at speed.
     */
    WATER_DROPLET,
    /**
     * Unknown.
     */
    ITEM_TAKE,
    /**
     * Shows a vision of a Guardian mob centered on the player's screen,
     * regardless of position.
     */
    MOB_APPEARANCE;

    /**
     * Whether this particle requires a material to be specified.
     */
    public boolean usesMaterial() {
        return this == ITEM_BREAK || this == BLOCK_BREAK || this == BLOCK_DUST;
    }

}
