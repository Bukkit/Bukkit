package org.bukkit;

import java.util.Map;

import com.google.common.collect.Maps;

import org.bukkit.block.BlockFace;
import org.bukkit.potion.Potion;

/**
 * A list of effects that the server is able to send to players.
 */
public enum Effect {
    /**
     * An alternate click sound.
     */
    CLICK2(1000, Type.SOUND),
    /**
     * A click sound.
     */
    CLICK1(1001, Type.SOUND),
    /**
     * Sound of a bow firing.
     */
    BOW_FIRE(1002, Type.SOUND),
    /**
     * Sound of a door opening/closing.
     */
    DOOR_TOGGLE(1003, Type.SOUND),
    /**
     * Sound of fire being extinguished.
     */
    EXTINGUISH(1004, Type.SOUND),
    /**
     * A song from a record. Needs the record item ID as additional info
     */
    RECORD_PLAY(1005, Type.SOUND, Material.class),
    /**
     * Sound of ghast shrieking.
     */
    GHAST_SHRIEK(1007, Type.SOUND),
    /**
     * Sound of ghast firing.
     */
    GHAST_SHOOT(1008, Type.SOUND),
    /**
     * Sound of blaze firing.
     */
    BLAZE_SHOOT(1009, Type.SOUND),
    /**
     * Sound of zombies chewing on wooden doors.
     */
    ZOMBIE_CHEW_WOODEN_DOOR(1010, Type.SOUND),
    /**
     * Sound of zombies chewing on iron doors.
     */
    ZOMBIE_CHEW_IRON_DOOR(1011, Type.SOUND),
    /**
     * Sound of zombies destroying a door.
     */
    ZOMBIE_DESTROY_DOOR(1012, Type.SOUND),
    /**
     * A visual smoke effect. Needs direction as additional info.
     */
    SMOKE(2000, Type.VISUAL, BlockFace.class),
    /**
     * Sound of a block breaking. Needs block ID as additional info.
     */
    STEP_SOUND(2001, Type.SOUND, Material.class),
    /**
     * Visual effect of a splash potion breaking. Needs potion data value as additional info.
     */
    POTION_BREAK(2002, Type.VISUAL, Potion.class),
    /**
     * An ender eye signal; a visual effect.
     */
    ENDER_SIGNAL(2003, Type.VISUAL),
    /**
     * The flames seen on a mobspawner; a visual effect.
     */
    MOBSPAWNER_FLAMES(2004, Type.VISUAL),
    /**
     * The biggest explosion particle effect
     */
    HUGE_EXPLOSION("hugeexplosion", Type.PARTICLE),
    /**
     * A larger version of the explode particle
     */
    LARGE_EXPLODE("largeexplode", Type.PARTICLE),
    /**
     * The spark that comes off a fireworks
     */
    FIREWORKS_SPARK("fireworksSpark", Type.PARTICLE),
    /**
     * Currently shows nothing
     */
    BUBBLE("bubble", Type.PARTICLE),
    /**
     * Currently shows nothing
     */
    SUSPENDED("suspended", Type.PARTICLE),
    /**
     * Small gray particles
     */
    DEPTH_SUSPEND("depthsuspend", Type.PARTICLE),
    /**
     * Small gray particles
     */
    TOWNAURA("townaura", Type.PARTICLE),
    /**
     * Critical hit particles
     */
    CRIT("crit", Type.PARTICLE),
    /**
     * Blue critical hit particles
     */
    MAGIC_CRIT("magicCrit", Type.PARTICLE),
    /**
     * Smoke particles
     */
    PARTICLE_SMOKE("smoke", Type.PARTICLE),
    /**
     * Multicolored potion effect particles
     */
    MOB_SPELL("mobSpell", Type.PARTICLE),
    /**
     * Multicolored potion effect particles that are slightly transparent
     */
    MOB_SPELL_AMBIENT("mobSpellAmbient", Type.PARTICLE),
    /**
     * A puff of white particles
     */
    SPELL("spell", Type.PARTICLE),
    /**
     * A puff of white starts
     */
    INSTANT_SPELL("instantSpell", Type.PARTICLE),
    /**
     * A puff of purple particles
     */
    WITCH_MAGIC("witchMagic", Type.PARTICLE),
    /**
     * The note that appears above note blocks
     */
    NOTE("note", Type.PARTICLE),
    /**
     * The particles shown at nether portals
     */
    PORTAL("portal", Type.PARTICLE),
    /**
     * The symbols that fly towards the enchantment table
     */
    ENCHANTMENT_TABLE("enchantmenttable", Type.PARTICLE),
    /**
     * Explosion particles
     */
    EXPLODE("explode", Type.PARTICLE),
    /**
     * Fire particles
     */
    FLAME("flame", Type.PARTICLE),
    /**
     * The particles that pop out of lava
     */
    LAVA("lava", Type.PARTICLE),
    /**
     * A small gray square
     */
    FOOTSTEP("footstep", Type.PARTICLE),
    /**
     * Water particles
     */
    SPLASH("splash", Type.PARTICLE),
    /**
     * Currently shows nothing
     */
    LARGE_SMOKE("largeSmoke", Type.PARTICLE),
    /**
     * A puff of smoke
     */
    CLOUD("cloud", Type.PARTICLE),
    /**
     * Multicolored dust particles
     */
    REDDUST("reddust", Type.PARTICLE),
    /**
     * Snowball breaking
     */
    SNOWBALL_POOF("snowballpoof", Type.PARTICLE),
    /**
     * The water drip particle that appears on blocks under water
     */
    DRIP_WATER("dripWater", Type.PARTICLE),
    /**
     * The lava drip particle that appears on blocks under lava
     */
    DRIP_LAVA("dripLava", Type.PARTICLE),
    /**
     * White particles
     */
    SNOW_SHOVEL("snowshovel", Type.PARTICLE),
    /**
     * The particle shown when a slime jumps
     */
    SLIME("slime", Type.PARTICLE),
    /**
     * The particle that appears when breading animals
     */
    HEART("heart", Type.PARTICLE),
    /**
     * The particle that appears when hitting a villager
     */
    ANGRY_VILLAGER("angryVillager", Type.PARTICLE),
    /**
     * The particle that appears when trading with a villager
     */
    HAPPY_VILLAGER("happyVillager", Type.PARTICLE),
    /**
     * The item's icon breaking. This needs a material
     */
    ICON_CRACK("iconcrack", true, false, Type.PARTICLE),
    /**
     * The block breaking particles. This needs a material and a material data value.
     */
    TILE_CRACK("tilecrack", true, true, Type.PARTICLE);

    private final int id;
    private final Type type;
    private final Class<?> data;
    private static final Map<Integer, Effect> BY_ID = Maps.newHashMap();
    private final String particleName;
    private final boolean needsMaterial;
    private final boolean needsMaterialData;

    private Effect(int id, Type type) {
        this(id,type,null);
    }

    private Effect(int id, Type type, Class<?> data) {
        this.id = id;
        this.type = type;
        this.data = data;
        particleName = null;
        needsMaterial = false;
        needsMaterialData = false;
    }

    private Effect(String particleName, Type type) {
        this(particleName, false, false, type);
    }

    private Effect(String particleName, boolean needsMaterial, boolean needsMaterialData, Type type) {
        this.particleName = particleName;
        this.needsMaterial = needsMaterial;
        this.needsMaterialData = needsMaterialData;
        this.type = type;
        id = 0;
        data = null;
    }

    /**
     * Gets the ID for this effect.
     *
     * @return ID of this effect
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return The type of the effect.
     */
    public Type getType() {
        return this.type;
    }

    /**
     * @return The class which represents data for this effect, or null if none
     */
    public Class<?> getData() {
        return this.data;
    }

    /**
     * Returns the particle's name. This returns null if the effect is not a particle
     *
     * @return The particle's name
     */
    public String getParticleName() {
        return particleName;
    }

    /**
     * Returns whether the effect requires a Material to be created
     *
     * @return Whether the effect requires a Material to be created
     */
    public boolean needsMaterial() {
        return needsMaterial;
    }

    /**
     * Returns whether the effect requires a data value in order to be created
     *
     * @return Whether the effect requires a data value in order to be created
     */
    public boolean needsMaterialData() {
        return needsMaterialData;
    }

    /**
     * Gets the Effect associated with the given ID.
     *
     * @param id ID of the Effect to return
     * @return Effect with the given ID
     */
    public static Effect getById(int id) {
        return BY_ID.get(id);
    }

    static {
        for (Effect effect : values()) {
            if (effect.type != Type.PARTICLE) {
                BY_ID.put(effect.id, effect);
            }
        }
    }

    /**
     * Represents the type of an effect.
     */
    public enum Type {SOUND, VISUAL, PARTICLE}
}
