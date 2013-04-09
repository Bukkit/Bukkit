package org.bukkit;

public enum Particle {
    /**
     * The biggest explosion particle effect
     */
    HUGE_EXPLOSION(),
    /**
     * A larger version of the explode particle
     */
    LARGE_EXPLODE(),
    /**
     * The spark that comes off a fireworks
     */
    FIREWORKS_SPARK(),
    /**
     * Currently shows nothing
     */
    BUBBLE(),
    /**
     * Currently shows nothing
     */
    SUSPENDED(),
    /**
     * Small gray particles
     */
    DEPTH_SUSPEND(),
    /**
     * Small gray particles
     */
    TOWNAURA(),
    /**
     * Critical hit particles
     */
    CRIT(),
    /**
     * Blue critical hit particles
     */
    MAGIC_CRIT(),
    /**
     * Smoke particles
     */
    SMOKE(),
    /**
     * Multicolored potion effect particles
     */
    MOB_SPELL(),
    /**
     * Multicolored potion effect particles that are slightly transparent
     */
    MOB_SPELL_AMBIENT(),
    /**
     * A puff of white particles
     */
    SPELL(),
    /**
     * A puff of white starts
     */
    INSTANT_SPELL(),
    /**
     * A puff of purple particles
     */
    WITCH_MAGIC(),
    /**
     * The note that appears above note blocks
     */
    NOTE(),
    /**
     * The particles shown at nether portals
     */
    PORTAL(),
    /**
     * The symbols that fly towards the enchantment table
     */
    ENCHANTMENT_TABLE(),
    /**
     * Explosion particles
     */
    EXPLODE(),
    /**
     * Fire particles
     */
    FLAME(),
    /**
     * The particles that pop out of lava
     */
    LAVA(),
    /**
     * A small gray square
     */
    FOOTSTEP(),
    /**
     * Water particles
     */
    SPLASH(),
    /**
     * Currently shows nothing
     */
    LARGE_SMOKE(),
    /**
     * A puff of smoke
     */
    CLOUD(),
    /**
     * Multicolored dust particles
     */
    REDDUST(),
    /**
     * Snowball breaking
     */
    SNOWBALL_POOF(),
    /**
     * The water drip particle that appears on blocks under water
     */
    DRIP_WATER(),
    /**
     * The lava drip particle that appears on blocks under lava
     */
    DRIP_LAVA(),
    /**
     * White particles
     */
    SNOW_SHOVEL(),
    /**
     * The particle shown when a slime jumps
     */
    SLIME(),
    /**
     * The particle that appears when breading animals
     */
    HEART(),
    /**
     * The particle that appears when hitting a villager
     */
    ANGRY_VILLAGER(),
    /**
     * The particle that appears when trading with a villager
     */
    HAPPY_VILLAGER(),
    /**
     * The item's icon breaking. This needs a material
     */
    ICON_CRACK(true),
    /**
     * The block breaking particles. This needs a material and a material data value.
     */
    TILE_CRACK(true, true);

    private final boolean needsMaterial;
    private final boolean needsMaterialData;

    private Particle() {
        needsMaterial = false;
        needsMaterialData = false;
    }

    private Particle(boolean needsMaterial) {
        this.needsMaterial = needsMaterial;
        needsMaterialData = false;
    }

    private Particle(boolean hasMaterial, boolean needsMaterialData) {
        this.needsMaterial = hasMaterial;
        this.needsMaterialData = needsMaterialData;
    }

    /**
     * Returns whether the particle requires a Material to be created
     * 
     * @return Whether the particle requires a Material to be created
     */
    public boolean needsMaterial() {
        return needsMaterial;
    }

    /**
     * Returns whether the particle requires a data value in order to be created
     * 
     * @return Whether the particle requires a data value in order to be created
     */
    public boolean needsMaterialData() {
        return needsMaterialData;
    }
}
