package org.bukkit;

public enum Particle {
	/**
	 * The biggest explosion particle effect
	 */
	HUGE_EXPLOSION("hugeexplosion"),
	/**
	 * A larger version of the explode particle
	 */
	LARGE_EXPLODE("largeexplode"),
	/**
	 * The spark that comes off a fireworks
	 */
	FIREWORKS_SPARK("fireworksSpark"),
	/**
	 * Currently shows nothing
	 */
	BUBBLE("bubble"),
	/**
	 * Currently shows nothing
	 */
	SUSPENDED("suspended"),
	/**
	 * Small gray particles
	 */
	DEPTH_SUSPEND("depthsuspend"),
	/**
	 * Small gray particles
	 */
	TOWNAURA("townaura"),
	/**
	 * Currently shows nothing
	 */
	CRIT("CRIT"),
	/**
	 * Blue crit particles
	 */
	MAGIC_CRIT("magicCrit"),
	/**
	 * Smoke particles
	 */
	SMOKE("smoke"),
	/**
	 * Multicolored potion particles
	 */
	MOB_SPELL("mobSpell"),
	/**
	 * Multicolored potion particles that are slightly transparent
	 */
	MOB_SPELL_AMBIENT("mobSpellAmbient"),
	/**
	 * A puff of white particles
	 */
	SPELL("spell"),
	/**
	 * A puff of white starts
	 */
	INSTANT_SPELL("instantSpell"),
	/**
	 * A puff of purple particles
	 */
	WITCH_MAGIC("witchMagic"),
	/**
	 * The note that appears above note blocks
	 */
	NOTE("note"),
	/**
	 * The particles shown at nether portals
	 */
	PORTAL("portal"),
	/**
	 * The symbols that fly towards the enchantment table
	 */
	ENCHANTMENT_TABLE("enchantmenttable"),
	/**
	 * Explosion particles
	 */
	EXPLODE("explode"),
	/**
	 * Fire particles
	 */
	FLAME("flame"),
	/**
	 * The particles that pop out of lava
	 */
	LAVA("lava"),
	/**
	 * A small gray square
	 */
	FOOTSTEP("footstep"),
	/**
	 * Water particles
	 */
	SPLASH("splash"),
	/**
	 * Currently shows nothing
	 */
	LARGE_SMOKE("largeSmoke"),
	/**
	 * A puff of smoke
	 */
	CLOUD("cloud"),
	/**
	 * Multicolored dust particles
	 */
	REDDUST("reddust"),
	/**
	 * Snowball breaking
	 */
	SNOWBALL_POOF("snowballpoof"),
	/**
	 * The water drip particle that appears on blocks under water
	 */
	DRIP_WATER("dripWater"),
	/**
	 * The lava drip particle that appears on blocks under lava
	 */
	DRIP_LAVA("dripLava"),
	/**
	 * White particles
	 */
	SNOW_SHOVEL("snowshovel"),
	/**
	 * The particle shown when a slime jumps
	 */
	SLIME("slime"),
	/**
	 * The particle that appears when breading animals
	 */
	HEART("heart"),
	/**
	 * The particle that appears when hitting a villager
	 */
	ANGRY_VILLAGER("angryVillager"),
	/**
	 * The particle that appears when trading with a villager
	 */
	HAPPY_VILLAGER("happyVillager"),
	/**
	 * The item's icon breaking.
	 * This needs an ID
	 */
	ICON_CRACK("iconcrack", true),
	/**
	 * The block breaking particles.
	 * This needs an ID and data value.
	 */
	TILE_CRACK("tilecrack", true, true);

	private final String particleName;
	private final boolean hasID;
	private final boolean hasData;
	
	Particle(String particleName) {
		this.particleName = particleName;
		hasID = false;
		hasData = false;
	}
	
	Particle(String particleName, boolean hasID) {
		this.particleName = particleName;
		this.hasID = hasID;
		hasData = false;
	}
	
	Particle(String particleName, boolean hasID, boolean hasData) {
		this.particleName = particleName;
		this.hasID = true;
		this.hasData = true;
	}
	
	/**
	 * Gets the name of the particle as used internally by minecraft
	 * @return The particle's name
	 */
	public String getParticleName() {
		return particleName;
	}

	/**
	 * Returns whether the particle needs the extra ID information
	 * @return Whether the particle needs extra ID information 
	 */
	public boolean hasID() {
		return hasID;
	}

	/**
	 * Returns whether the particle needs the extra data information
	 * @return Whether the particle needs extra data information 
	 */
	public boolean hasData() {
		return hasData;
	}	
}
