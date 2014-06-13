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
    WITHER_SPAWN(1013, Type.SOUND),
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
    ENDERDRAGON_DIE(1018, Type.SOUND),
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
     * The green particles that appear when using bonemeal on a plant.
     */
    BONEMEAL_USE(2005, Type.VISUAL),
    /**
     * Visual effect of dust played when an entity lands on a block after
     * falling from a high altitude. Needs an integer which is the particle
     * speed.
     * <p>
     * When playing it, the location is the block the entity has fallen on,
     * and is used to choose the appropiate texture based on the block
     * material. The particles are rendered on top of that.
     */
    LAND_DUST(2006, Type.VISUAL, Integer.class),
    ;

    private final int id;
    private final Type type;
    private final Class<?> data;
    private static final Map<Integer, Effect> BY_ID = Maps.newHashMap();

    Effect(int id, Type type) {
        this(id,type,null);
    }

    Effect(int id, Type type, Class<?> data) {
        this.id = id;
        this.type = type;
        this.data = data;
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
