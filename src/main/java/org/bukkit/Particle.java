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
     * Crit particles
     */
    CRIT(),
    /**
     * Blue crit particles
     */
    MAGIC_CRIT(),
    /**
     * Smoke particles
     */
    SMOKE(),
    /**
     * Multicolored potion particles
     */
    MOB_SPELL(),
    /**
     * Multicolored potion particles that are slightly transparent
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
     * The item's icon breaking. This needs an item ID
     */
    ICON_CRACK(true),
    /**
     * The block breaking particles. This needs an block ID and data value.
     */
    TILE_CRACK(true, true);

    private final boolean hasMaterial;
    private final boolean hasMaterialData;

    Particle() {
        hasMaterial = false;
        hasMaterialData = false;
    }

    Particle(boolean hasID) {
        this.hasMaterial = hasID;
        hasMaterialData = false;
    }

    Particle(boolean hasID, boolean hasData) {
        this.hasMaterial = true;
        this.hasMaterialData = true;
    }

    /**
     * Returns whether the particle needs the extra ID information
     * 
     * @return Whether the particle needs extra ID information
     */
    public boolean hasMaterial() {
        return hasMaterial;
    }

    /**
     * Returns whether the particle needs the extra data information
     * 
     * @return Whether the particle needs extra data information
     */
    public boolean hasMaterialData() {
        return hasMaterialData;
    }
}
