package org.bukkit;

/**
 * An enum of particle effects the server is able to send to players.
 */
public enum Particle {
    /**
     * A very large explosion.
     */
    HUGE_EXPLOSION,
    /**
     * A large explosion.
     */
    LARGE_EXPLOSION,
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
    MAGIC_CRITICAL,
    /**
     * Small black smoke particles.
     */
    SMOKE,
    /**
     * Potion swirls.
     */
    POTION_SWIRL,
    /**
     * Semitransparent potion swirls.
     */
    POTION_SWIRL_TRANSPARENT,
    /**
     * A puff of white potion swirls.
     */
    SPELL,
    /**
     * A puff of white stars.
     */
    INSTANT_SPELL,
    /**
     * Purple particles from witches.
     */
    WITCH_MAGIC,
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
    FLYING_GLYPH,
    /**
     * Small gray smoke particles.
     */
    SMALL_SMOKE,
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
    SPLASH,
    /**
     * Large smoke particles.
     */
    LARGE_SMOKE,
    /**
     * White smoke particles.
     */
    CLOUD,
    /**
     * Colored dust used for redstone.
     */
    COLORED_DUST,
    /**
     * Snowball breaking particles.
     */
    SNOWBALL_BREAK,
    /**
     * Water dripping from the ceiling.
     */
    WATER_DRIP,
    /**
     * Lava dripping from the ceiling.
     */
    LAVA_DRIP,
    /**
     * Small white puffs.
     */
    SNOW_SHOVEL,
    /**
     * Slime jump particles.
     */
    SLIME,
    /**
     * Hearts from breeding animals.
     */
    HEART,
    /**
     * Thunderclouds indicating villager anger.
     */
    ANGRY_VILLAGER,
    /**
     * Green stars indicating villager happiness.
     */
    HAPPY_VILLAGER,
    /**
     * The wake created while fishing.
     */
    FISH_WAKE,
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
    BLOCK_DUST;

    /**
     * Whether this particle requires a material to be specified.
     */
    public boolean usesMaterial() {
        return this == ITEM_BREAK || this == BLOCK_BREAK || this == BLOCK_DUST;
    }

    /**
     * Whether this particle makes use of the data portion of the material specified.
     */
    public boolean usesData() {
        return this == BLOCK_BREAK || this == BLOCK_DUST;
    }
}
