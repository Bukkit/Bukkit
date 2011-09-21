package org.bukkit;

import java.util.HashMap;
import java.util.Map;

/**
 * Effects that can be applied to LivingEntities. Not all of them are implemented yet.
 */
public enum EntityEffect {

    MOVE_SPEED(1),
    MOVE_SLOW(2),
    DIG_SPEED(3),
    DIG_SLOW(4),
    DAMAGE_BOOST(5),
    HEAL(6),
    HARM(7),
    JUMP(8),
    CONFUSION(9),
    REGENERATION(10),
    RESISTANCE(11),
    FIRE_RESISTANCE(12),
    WATER_BREATHING(13),
    INVISIBILITY(14),
    BLINDNESS(15),
    NIGHT_VISION(16),
    HUNGER(17),
    WEAKNESS(18),
    POISON(19);

    private final byte id;

    private static final Map<Integer, EntityEffect> lookup = new HashMap<Integer, EntityEffect>();

    static {
        for (EntityEffect effect : EntityEffect.values()) {
            lookup.put((int)effect.getId(), effect);
        }
    }

    public static EntityEffect getById(int id) {
        return lookup.get(id);
    }

    private EntityEffect(int id) {
        this.id = (byte) id;
    }

    public byte getId() {
        return id;
    }
}
