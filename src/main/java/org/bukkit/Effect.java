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
     * Sound of a Wither spawning.
     */
    WITHER_SPAWN(1013, Type.SOUND, true),
    /**
     * Sound of a Wither firing.
     */
    WITHER_SHOOT(1014, Type.SOUND),
    /**
     * Sound of a bat taking off.
     */
    BAT_TAKEOFF(1015, Type.SOUND),
    /**
     * Sound of villager getting infected.
     */
    VILLAGER_INFECT(1016, Type.SOUND),
    /**
     * Sound of villager being cured.
     */
    VILLAGER_CURE(1017, Type.SOUND),
    /**
     * Sound of an Enderdragon dying.
     */
    ENDERDRAGON_DIE(1018, Type.SOUND, true),
    /**
     * Sound of an anvil breaking.
     */
    ANVIL_BREAK(1020, Type.SOUND),
    /**
     * Sound of an anvil being used.
     */
    ANVIL_USE(1021, Type.SOUND),
    /**
     * Sound of an anvil landing.
     */
    ANVIL_LAND(1022, Type.SOUND),
    /**
     * A visual smoke effect. Needs direction as additional info.
     */
    SMOKE(2000, Type.VISUAL, BlockFace.class),
    /**
     * Sound of a block breaking. Needs block ID as additional info.
     */
    STEP_SOUND(2001, Type.SOUND, Material.class),
    /**
     * Visual effect of a splash potion breaking. Needs potion data value as
     * additional info.
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
     * The green particles that appear when using bonemeal on a plant; a visual
     * effect. Needs a non-air block as additional info. An optional integer
     * may be supplied to represent the number of particles to show, by default
     * 15 are shown.
     */
    BONEMEAL_USE(2005, Type.VISUAL),
    ;

    private final int id;
    private final Type type;
    private final Class<?> data;
    private final boolean distanceIgnored;
    private static final Map<Integer, Effect> BY_ID = Maps.newHashMap();

    Effect(int id, Type type) {
        this(id,type,null);
    }

    Effect(int id, Type type, boolean distanceIgnored) {
        this(id, type, null, distanceIgnored);
    }

    Effect(int id, Type type, Class<?> data) {
        this(id, type, data, false);
    }

    Effect(int id, Type type, Class<?> data, boolean distanceIgnored) {
        this.id = id;
        this.type = type;
        this.data = data;
        this.distanceIgnored = distanceIgnored;
    }

    /**
     * Gets the ID for this effect.
     *
     * @return ID of this effect
     * @deprecated Magic value
     */
    @Deprecated
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
     * @return The class which represents data for this effect, or null if
     *     none
     */
    public Class<?> getData() {
        return this.data;
    }

    /**
     * Gets whether or not the distance to the player is ignored by this
     * effect. If distance is ignored, the player will hear/see the effect
     * regardless of how far away the effect is played from them.
     *
     * @return True if effect ignores distance
     */
    public boolean isDistanceIgnored() {
        return this.distanceIgnored;
    }

    /**
     * Gets the Effect associated with the given ID.
     *
     * @param id ID of the Effect to return
     * @return Effect with the given ID
     * @deprecated Magic value
     */
    @Deprecated
    public static Effect getById(int id) {
        return BY_ID.get(id);
    }

    static {
        for (Effect effect : values()) {
            BY_ID.put(effect.id, effect);
        }
    }

    /**
     * Represents the type of an effect.
     */
    public enum Type {SOUND, VISUAL}
}
